package org.example.entity;

import jakarta.annotation.Resource;
import org.example.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Server.class)
class ServerTest {

    @Resource
    private Server server;

    @Test
    void testServer() {

        System.out.println(server);
    }


}