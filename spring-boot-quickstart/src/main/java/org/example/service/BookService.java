package org.example.service;

import org.example.dto.BookDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    public List<BookDTO> getAllBooks(){
        return List.of(
                new BookDTO(1l,"Java" ,"张三",29.99),
                new BookDTO(2L,"Spring","李四",39.99)
        );
    }
}
