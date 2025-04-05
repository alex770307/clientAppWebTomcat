package org.clientappwebtomcat.back.controller;

import org.clientappwebtomcat.back.dto.ResponseClientDto;
import org.clientappwebtomcat.back.service.AddClientService;
import org.clientappwebtomcat.back.service.DeleteClientService;
import org.clientappwebtomcat.back.service.FindClientService;

import java.util.List;

public class ClientController {

    private AddClientService addClientService;
    private FindClientService findClientService;
    private DeleteClientService deleteClientService;

    public ClientController(AddClientService addClientService, FindClientService findClientService, DeleteClientService deleteClientService) {
        this.addClientService = addClientService;
        this.findClientService = findClientService;
        this.deleteClientService = deleteClientService;
    }

    public List<ResponseClientDto> getAllClients(){
        return findClientService.findAll();
    }
}
