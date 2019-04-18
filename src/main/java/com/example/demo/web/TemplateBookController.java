package com.ellis.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller // 模板式開發，方法回傳樣板名稱的字串
public class TemplateBookController {
    @GetMapping("/showBookPage")
    public String showBookPage() {
        return "books"; // 模板名稱
    }

    @GetMapping("/showBookStr")
    @ResponseBody
    // 改傳回字串(變得和RestController模式一樣)
    public String showBookStr() {
        return "books";
    }

    @GetMapping("/getAllBooks")
    @ResponseBody
    // 改傳回物件
    public Object getAll() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "hello");
        map.put("age", 18);

        return map;
    }
}
