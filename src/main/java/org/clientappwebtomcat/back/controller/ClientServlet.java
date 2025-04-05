package org.clientappwebtomcat.back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.clientappwebtomcat.back.dto.RequestClientDto;
import org.clientappwebtomcat.back.dto.ResponseClientDto;
import org.clientappwebtomcat.back.repository.ClientRepository;
import org.clientappwebtomcat.back.repository.ClientRepositoryImpl;
import org.clientappwebtomcat.back.service.AddClientService;
import org.clientappwebtomcat.back.service.DeleteClientService;
import org.clientappwebtomcat.back.service.FindClientService;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Optional;

//Это будет сервлет. Сервлет - это такой класс, задача которого принимать
// запросы, обрабатывать их и отдавать клиенту ответы
public class ClientServlet extends HttpServlet {

    private final AddClientService addClientService;
    private final FindClientService findClientService;
    private final DeleteClientService deleteClientService;

    public ClientServlet() {
        ClientRepository repository = new ClientRepositoryImpl();

        addClientService = new AddClientService(repository);
        findClientService = new FindClientService(repository);
        deleteClientService = new DeleteClientService(repository);

        // сохраним новых пользователей
        addClientService.addClient(new RequestClientDto("user1","user1Email","user1pass"));
        addClientService.addClient(new RequestClientDto("user2","user2Email","user2pass"));
        addClientService.addClient(new RequestClientDto("user3","user3Email","user3pass"));
        addClientService.addClient(new RequestClientDto("user4","user4Email","user4pass"));
        addClientService.addClient(new RequestClientDto("user5","user5Email","user5pass"));

    }

    /*
    Это метод doGet. Для чего он нужен?
    Когда на наше приложение будет приходить GET-запрос,
    Tomcat будет создавать java-объекты запроса и ответа.
    Затем будет вызывать наш метод doGet и передавать эти объекты в метод аргументами.
    Наша задача - взять информацию из объекта req (request) и по итогу работы нужных методов
    нашего приложения записать всю информацию для клиента в объект resp (response).
    После того, как метод отработает, Tomcat получит обратно объект resp, прочитает из него
    всю информацию, упакует ее в http-ответ и отправит обратно клиенту.
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*
        Мы можем получить два "разных" http-запроса типа GET:
        1) /clients - дайте информацию о всех клиентах
        2) /clients?idClient=... - дайте информацию о конкретном клиенте
         */

        // мы пытаемся из объекта request получить содержимое поля idClient
        String idClientFromRequest = req.getParameter("idClient");

        // настраиваем ответ пользователю
        ObjectMapper mapper = new ObjectMapper();
        Writer writer = resp.getWriter();
        resp.setContentType("Application/JSON"); // говорим браузеру "ответ будет в формате JSON"


        // если в объекте request НЕТ поля idClient - то это значит, что мы получили запрос номер 1 "дайте всех"
        if (idClientFromRequest == null) {
            // здесь получаем информацию о всех клиентах
            List<ResponseClientDto> responseClients = findClientService.findAll();
            mapper.writeValue(writer, responseClients);
        } else {
            // здесь получаем информацию о конкретном клиенте
            Integer idClient = Integer.parseInt(idClientFromRequest);
            Optional<ResponseClientDto> responseClientOptional = findClientService.findById(idClient);

            if (responseClientOptional.isPresent()) {
                mapper.writeValue(writer,responseClientOptional.get());
            } else {
                mapper.writeValue(writer,"Клиент с id = " + idClient + " не найден");
            }

        }

    }

}
