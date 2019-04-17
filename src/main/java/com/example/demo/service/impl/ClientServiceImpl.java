package com.example.demo.service.impl;

import com.example.demo.domain.Client;
import com.example.demo.domain.ClientRepository;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository cr;

    @Override
    public List<Client> findAll() {
        return cr.findAll();
    }

    @Override
    public Client findById(long id) {
        return cr.findById(id).orElse(new Client());
    }

    @Override
    public Client insert(Client client) {
        return cr.save(client);
    }

    @Override
    public Client update(Client client) {
        return cr.save(client);
    }

    @Override
    public void delete(long id) {
        cr.deleteById(id);
    }
}
