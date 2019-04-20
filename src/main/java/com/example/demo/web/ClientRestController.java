package com.example.demo.web;

import com.example.demo.domain.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientRestController {

    @Autowired
    private ClientService cs;

    @GetMapping("/client")
    public List<Client> findAll() {
        return cs.findAll();
    }

    @GetMapping("/page/client")
    public Page<Client> findAll(@RequestParam int page, @RequestParam int size) {
        String sortBy = "id";
        return cs.findByPage(page, size, sortBy);
    }

    @GetMapping("/client/{id}")
    public Client findById(@PathVariable long id) {
        return cs.findById(id);
    }

    @PostMapping("/client/by1c")
    public List<Client> findByName(@RequestParam String name) {
        return cs.findByName(name);
    }

    @PostMapping("/client/by2c")
    public List<Client> findByGenderAndAgeLessThanEqual(@RequestParam String gender, @RequestParam int age) {
        return cs.findByGenderAndAgeLessThanEqual(gender, age);
    }

    @PostMapping("client/jpql/byEmail")
    public List<Client> findByEmailKeyWord(@RequestParam String keyWord) {
        System.out.println("keyWord = " + keyWord);
        return cs.findByEmailKeyWord(keyWord);
    }

    @PostMapping("client/jpql/count")
    public int findCountByGender(String gender) {
        return cs.findCountByGender(gender);
    }

    // 可以換成下面update的那種寫法，spring會自動轉換成該modole物件
    @PostMapping("/client")
    public Client insert(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String gender,
                         @RequestParam int age
    ) {
        return cs.save(new Client(name, gender, email, password, age));
    }


    @PutMapping("/client")
    public Client update(Client client) {
        return cs.save(client);
    }

    @PutMapping("/client/jpql")
    public int updateByJpql(@RequestParam String gender, @RequestParam String pwd) {
        return cs.updatePwdByGender(gender, pwd);
    }


    @DeleteMapping("/client/{id}")
    public Object delete(@PathVariable long id) {
        cs.delete(id);
        return "deletion client by id = " + id + " is success.";
    }

    @DeleteMapping("/client/jpql/{id}")
    public Object deleteByJpql(@PathVariable long id) {
        cs.deleteById(id);
        return "deletion client by id = " + id + " is success.";
    }

}
