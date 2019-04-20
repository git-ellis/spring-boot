package com.example.demo.web;

import com.example.demo.domain.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService cs;

    @GetMapping("/getOne/{id}")
    public String getOne(@PathVariable long id, Model model) {
        Client client = cs.findById(id);
        model.addAttribute("client", client);
        model.addAttribute("title", "客戶資料");
        return "client";
    }

    @GetMapping("/getAll")
    public String getAll(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                         ModelMap model) {
        Page<Client> pageData = cs.findByPage(pageable);
        model.addAttribute("page", pageData);
        model.addAttribute("title", "客戶列表");

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
        return "input";
    }

    @PostMapping("/save")
    public String addClient(Client client, RedirectAttributes ra) {
        long id = client.getId();
        cs.save(client);

        if (id == 0)
            ra.addFlashAttribute("opTitle", "新增用戶");
        else
            ra.addFlashAttribute("opTitle", "修改用戶");
        ra.addFlashAttribute("opMsg", client.getName() + "成功");
        return "redirect:getAll"; // 會自動加前墜
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id, RedirectAttributes ra) {
        Client client = cs.findById(id);
        cs.delete(id);

        ra.addFlashAttribute("opTitle", "刪除用戶");
        ra.addFlashAttribute("opMsg", client.getName() + "成功");
        return "redirect:/client/getAll";
    }
}
