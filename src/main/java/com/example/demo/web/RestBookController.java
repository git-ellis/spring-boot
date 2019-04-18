package com.ellis.demo.web;

import com.example.demo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // 基於RESTFUL開發，方法回傳字串
@RequestMapping(value = "/api/v1")
public class RestBookController {

    @Autowired
    private Book book1;

    @Value("${book.name}")
    private String name;
    @Value("${book.author}")
    private String author;
    @Value("${book.price}")
    private int price;
    @Value("${book.description}")
    private String desc;


    // @RequestMapping(value = "/say") 支持所有method
    // @RequestMapping(value = "/say", method = RequestMethod.GET)
    // @PostMapping("/say")
    // 處理外部請求的對應方法
    @GetMapping("/say")
    public String hello() {
        return "Hello Spring Boot";
    }

    @GetMapping("/books")
    public List<Map<String, Object>> getAll(@RequestParam int page, @RequestParam(defaultValue = "10") int size) {
        System.out.println("page = " + page);
        System.out.println("size = " + size);

        List<Map<String, Object>> books = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> book = new HashMap<>();
            book.put("name", "book_" + i);
            book.put("author", "author_" + i);
            book.put("price", i * 100 / 2);
            books.add(book);
        }

        int bs = books.size();
        int totlePages = bs % size + 1;
        int end = page * size;
        int start = end - size;
        if (end > bs)
            end = bs;
//        if (start > totlePages)
//            start = end - size;

        return books.subList(start, end);
    }

    // 使用path varible 傳遞參數
    @GetMapping("/books/id/{id}")
    public Object getBookById(@PathVariable long id) {
        System.out.println("id = " + id);

        Map<String, Object> book = new HashMap<>();
        book.put("id", id);
        book.put("name", "10分鐘教你成為幽默大師!");
        book.put("price", 100);
        book.put("author", "Ellis");

        return book;
    }

    @GetMapping("/books/bid/{bid}")
    public Object getBookByBid(@PathVariable("bid") String id) {
        System.out.println("id = " + id);

        Map<String, Object> book = new HashMap<>();
        book.put("name", "10分鐘教你成為幽默大師!");
        book.put("author", "Ellis");
        book.put("price", 200);

        return book;
    }

    // 傳遞多個參數
    @GetMapping("/books/{id}/{author}")
    public Object getBookByIdAndAuthor(@PathVariable long id, @PathVariable String author) {
        System.out.println("id = " + id + " author = " + author);

        Map<String, Object> book = new HashMap<>();
        book.put("id", id);
        book.put("name", "10分鐘教你成為幽默大師!");
        book.put("author", author);
        book.put("price", 100);

        return book;
    }

    /**
     * 使用正則表達式，只有小寫a-z與underscore允許訪問，
     * 不然會回傳404
     *
     * @param author String
     * @return book
     */
    @GetMapping("/books/{author:[a-z_]+}")
    public Map<String, Object> getBookByAuthor(@PathVariable String author) {
        System.out.println("author = " + author);

        Map<String, Object> book = new HashMap<>();
        book.put("name", "10分鐘教你成為幽默大師!");
        book.put("author", author);
        book.put("price", 100);

        return book;
    }

    @GetMapping("/books/config/{id}")
    public Object getBookByPropAndYml(@PathVariable int id) {
        if (id == 1)
            return book1; // from yml
        else if (id == 2) {
            System.out.println(desc);
            return new Book(id, name, author, price); // from prop
        } else
            return "Nothing";
    }


    @PostMapping("/books")
    public Object create(@RequestParam("name") String name,
                         @RequestParam String author,
                         @RequestParam int price) {
        Map<String, Object> book = new HashMap<>();
        book.put("name", name);
        book.put("author", author);
        book.put("price", price);

        return book;
    }

}

