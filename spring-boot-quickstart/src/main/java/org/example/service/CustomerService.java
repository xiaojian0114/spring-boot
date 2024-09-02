package org.example.service;

import org.springframework.stereotype.Service;


@Service
public class CustomerService {
    public String handleRequest(RequestType requestType) {

        return switch (requestType) {
            case QUERY -> handleRequest();
            case COMPLAINT -> handleRequest();
            case SUGGESTION -> handleRequest();


        };


    }

    private String handleQuery() {
        return "请求查询";
    }

    private String handleComlaint() {
        return "投诉请求";
    }

    private String handleSuggestion() {
        return "建议请求";
    }

}
