package com.example.demo.web;

import com.example.demo.domain.Client;
import com.example.demo.domain.ClientRepository;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientRestController {

    @Autowired
    private ClientService cs;
    @Autowired
    private ClientRepository cr;

    @GetMapping("/client")
    public List<Client> findAll() {
        return cs.findAll();
    }

    @GetMapping("/client/{id}")
    public Client findById(@PathVariable long id) {
        return cs.findById(id);
    }

    /**
     * 可以換成下面update的那種寫法，spring會自動轉換成該modole物件
     *
     * @param id
     * @param name
     * @param email
     * @param password
     * @return
     */
    @PostMapping("/client")
    public Client insert(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password) {
        return cs.insert(new Client(name, email, password));
    }


    @PutMapping("/client")
    public Client update(Client client) {
        return cs.update(client);
    }

    @DeleteMapping("/client/{id}")
    public Object delete(@PathVariable long id) {
        cs.delete(id);
        return "deletion client by id = " + id + " is success.";
    }
}
