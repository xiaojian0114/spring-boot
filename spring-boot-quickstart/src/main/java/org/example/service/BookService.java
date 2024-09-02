package org.example.service;

import org.example.dto.BookDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    public List<BookDTO> getAllBooks(){
        return List.of(
                new BookDTO(1l,"Java" ,"aaa",29.99),
                new BookDTO(2L,"Spring","Bob",39.99)
        );
    }
}
