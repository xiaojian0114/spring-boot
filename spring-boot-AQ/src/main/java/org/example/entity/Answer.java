package org.example.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Question question;

    @ManyToOne
    private User user;

    private String content;

    private LocalDate createdAt;
}
