package com.example.demo.service;

import com.example.demo.domain.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAll();

    Client findById(long id);

    Client save(Client client);

    void delete(long id);

    List<Client> findByName(String name);

    List<Client> findByGenderAndAgeLessThanEqual(String gender, int age);

    int findCountByGender(String gender);

    List<Client> findByEmailKeyWord(String keyWord);

    int updatePwdByGender(String gender, String pwd);

    int deleteById(long id);
}
