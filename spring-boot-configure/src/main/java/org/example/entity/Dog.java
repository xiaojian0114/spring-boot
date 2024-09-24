package org.example.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Dog {

    @Value("${student.dog.name}")
    private String name;

    @Value("${student.dog.age}")
    private Integer age;
}
