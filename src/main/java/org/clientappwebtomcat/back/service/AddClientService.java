package org.clientappwebtomcat.back.service;

import org.clientappwebtomcat.back.dto.RequestClientDto;
import org.clientappwebtomcat.back.dto.ResponseClientDto;
import org.clientappwebtomcat.back.entity.Client;
import org.clientappwebtomcat.back.repository.ClientRepository;

public class AddClientService {

    private ClientRepository repository;

    public AddClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public ResponseClientDto addClient(RequestClientDto request){
        Client clientForAdd = new Client(request.getName(), request.getEmail(), request.getPassword());

        Client clientAfterAdd = repository.add(clientForAdd);

        return new ResponseClientDto(
                clientAfterAdd.getIdClient(),
                clientAfterAdd.getName(),
                clientAfterAdd.getEmail());
    }
}
