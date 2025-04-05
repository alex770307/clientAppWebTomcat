package org.clientappwebtomcat.back.service;

import org.clientappwebtomcat.back.entity.Client;
import org.clientappwebtomcat.back.repository.ClientRepository;
import java.util.Optional;

public class DeleteClientService {

    private ClientRepository repository;

    public DeleteClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Optional<Client> deleteClient(Integer idClient){
        return repository.deleteById(idClient);
    }
}
