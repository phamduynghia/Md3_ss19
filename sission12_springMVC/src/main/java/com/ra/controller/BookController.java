package com.ra.controller;

import com.ra.model.entity.Book;
import com.ra.model.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public String index(Model model){
        List<Book> books = bookService.findAll();
        model.addAttribute("books",books);
        return "book/index";
    }
    @GetMapping("/add")
    public String add(Model model){
        //trả về form thêm mới
        Book book = new Book();
        model.addAttribute("book",book);
        return "book/add";
    }
    @PostMapping("/add")
    public String create(@ModelAttribute Book book){
        // lấy dữ liệu ừ form
        // gọi đến service thêm mới
        if (bookService.create(book)){
            return "redirect:/book";
        }
        return "book/add";
    }
}