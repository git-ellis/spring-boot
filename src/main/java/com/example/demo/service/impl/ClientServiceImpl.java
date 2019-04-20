package com.example.demo.service.impl;

import com.example.demo.domain.Client;
import com.example.demo.domain.ClientRepository;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Client> findByPage(Pageable bageable) {
        return  cr.findAll(bageable);
    }

    @Override
    public Page<Client> findByPage(int page, int size, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return cr.findAll(pageable);
    }

    @Override
    public Client findById(long id) {
        return cr.findById(id).orElse(new Client());
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

    @Override
    public Client save(Client client) {
        return cr.save(client);
    }

    @Transactional
    @Override
    public int updatePwdByGender(String gender, String pwd) {
        System.out.println(cr.findByGender(gender));
        return cr.updatePwdByGender(pwd, gender);
    }

    @Override
    public void delete(long id) {
        cr.deleteById(id);
    }

    @Transactional
    @Override
    public int deleteById(long id) {
        return cr.deleteById(id);
    }
}
