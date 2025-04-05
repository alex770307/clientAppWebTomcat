package org.clientappwebtomcat.back.repository;

import org.clientappwebtomcat.back.entity.Client;

import java.util.*;

public class ClientRepositoryImpl implements ClientRepository{

    private Map<Integer, Client> database;
    private Integer clientId;

    public ClientRepositoryImpl() {
        this.database = new HashMap<>();
        this.clientId = 0;

    }

    @Override
    public Client add(Client client) {
        clientId++;
        Client newClient =
                new Client(clientId,
                        client.getName(),
                        client.getEmail(),
                        client.getPassword());

        database.put(clientId, newClient);
        return newClient;
    }

    @Override
    public List<Client> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<Client> findById(Integer idClient) {

        return Optional.ofNullable(database.get(idClient));
    }

    @Override
    public List<Client> findByName(String name) {
        return database.values().stream()
                .filter(client ->  client.getName().equals(name))
                .toList();
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return database.values().stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public Optional<Client> deleteById(Integer idClient) {
        return Optional.ofNullable(database.remove(idClient));
    }

    /*
    если бы наше хранилище строилось на коллекции типа List, то тогда,
    для того чтобы удалить элемент коллекции по idClient нам нужно было бы
    сперва его найти.
    Изначально, когда мы добавляем элементы, то их нумерация совпадает
    (или отличается на 1) с индексами в коллекции.
    Но со временем из-за удаления некоторых элементов нумерация и индексы
    могут существенно отличаться.
    0,1,2,3,4,5,6,7,8,9 - было
    0,1,2,4,6,8,9 - стало

    а для коллекции типа HashMap, где ключом является как раз idClient
    мы просто используем метод remove(idClient)
     */
}
