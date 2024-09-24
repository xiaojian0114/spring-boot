package org.example.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Server {

    @Value("${Server.host}")
    private String host;

    @Value("${Server.port}")
    private int port;

    @Value("${Server.context-path}")
    private String path;


}
