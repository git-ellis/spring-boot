package com.example.demo.service;

import com.example.demo.domain.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAll();

    Client findById(long id);

    Client insert(Client client);

    Client update(Client client);

    void delete(long id);
}
