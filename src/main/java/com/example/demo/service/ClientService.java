package com.example.demo.service;

import com.example.demo.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    List<Client> findAll();

    Page<Client> findByPage(int page, int size, String sortBy);

    Page<Client> findByPage(Pageable bageable);

    Client findById(long id);

    List<Client> findByName(String name);

    List<Client> findByGenderAndAgeLessThanEqual(String gender, int age);

    List<Client> findByEmailKeyWord(String keyWord);

    int findCountByGender(String gender);

    int updatePwdByGender(String gender, String pwd);

    Client save(Client client);

    void delete(long id);

    int deleteById(long id);
}
