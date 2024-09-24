package org.example.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String firstName;
    private String lastName;
    private Integer Page;
    private UUID uuid;
    private Integer phone;
}
