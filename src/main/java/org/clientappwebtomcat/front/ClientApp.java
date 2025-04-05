package org.clientappwebtomcat.front;

import org.clientappwebtomcat.back.dto.RequestClientDto;
import org.clientappwebtomcat.back.dto.ResponseClientDto;
import org.clientappwebtomcat.back.repository.ClientRepository;
import org.clientappwebtomcat.back.repository.ClientRepositoryImpl;
import org.clientappwebtomcat.back.service.AddClientService;
import org.clientappwebtomcat.back.service.DeleteClientService;
import org.clientappwebtomcat.back.service.FindClientService;

import java.util.List;

public class ClientApp {
    public static void main(String[] args) {

        ClientRepository repository = new ClientRepositoryImpl();

        AddClientService addClientService = new AddClientService(repository);
        FindClientService findClientService = new FindClientService(repository);
        DeleteClientService deleteClientService = new DeleteClientService(repository);

        // сохраним новых пользователей
        addClientService.addClient(new RequestClientDto("user1","user1Email","user1pass"));
        addClientService.addClient(new RequestClientDto("user2","user2Email","user2pass"));
        addClientService.addClient(new RequestClientDto("user3","user3Email","user3pass"));
        addClientService.addClient(new RequestClientDto("user4","user4Email","user4pass"));
        addClientService.addClient(new RequestClientDto("user5","user5Email","user5pass"));

        printInfo(findClientService.findAll());


        System.out.println("Пользователь с Id = 2 : " + findClientService.findById(2).get());
    }

    private static void printInfo(List<ResponseClientDto> resp){
        for (ResponseClientDto response : resp){
            System.out.println(response);
        }
    }
}
