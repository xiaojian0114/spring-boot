package org.example;

import jakarta.annotation.Resource;
import org.example.dto.BookDTO;
import org.example.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookControl {
    @Resource
    private BookService bookservice;

    @GetMapping()
    public List<BookDTO> getBooks(){
        return bookservice.getAllBooks();
    }
}
