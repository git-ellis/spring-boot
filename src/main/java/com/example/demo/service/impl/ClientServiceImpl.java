package com.example.demo.service.impl;

import com.example.demo.domain.Client;
import com.example.demo.domain.ClientRepository;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<Client> findByName(String name) {
        return cr.findByName(name);
    }

    @Override
    public List<Client> findByGenderAndAgeLessThanEqual(String gender, int age) {
        return cr.findByGenderAndAgeLessThanEqual(gender, age);
    }

    @Override
    public int findCountByGender(String gender) {
        return cr.findCountByGender(gender);
    }

    @Override
    public List<Client> findByEmailKeyWord(String keyWord) {
        return cr.findByEmailKeyWord(keyWord);
    }

    @Transactional
    @Override
    public int updatePwdByGender(String gender, String pwd) {
        System.out.println(cr.findByGender(gender));
        return cr.updatePwdByGender(pwd, gender);
    }

    @Transactional
    @Override
    public int deleteById(long id) {
        return cr.deleteById(id);
    }
}
