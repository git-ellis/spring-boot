package com.example.demo;

import com.example.demo.domain.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private ClientRepository cr;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}