package org.example.entity;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Person.class)
class PersonTest {

    @Resource
    private Person person;

    @Test
    void testPerson() {
        System.out.println(person);
    }

}