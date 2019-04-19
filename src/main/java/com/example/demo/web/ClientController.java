package com.example.demo.web;

import com.example.demo.domain.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService cs;

    @GetMapping("/getOne/{id}")
    public String getOne(@PathVariable long id, Model model) {
        Client client = cs.findById(id);
        model.addAttribute("client", client);
        model.addAttribute("title", "查詢單個用戶");
        return "client";
    }

    @GetMapping("/getAll")
    public String getOne(ModelMap model) {
        List<Client> clients = cs.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("title", "查詢多個用戶");

        return "clients";
    }

    @GetMapping("/input")
    public String showInputPage(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "input";
    }

    @GetMapping("{id}/input")
    public String showInputPage(@PathVariable long id, Model model) {
        Client client = cs.findById(id);
        model.addAttribute("client", client);
        System.out.println(client);
        return "input";
    }

    @PostMapping("/save")
    public String addClient(Client client) {
        Client c = cs.save(client);
        return "redirect:getAll"; // 會自動加前墜
    }
}
