package org.example.service;


import org.example.entity.User;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final List<User> users = List.of(
            new User(1L,"张三",22),
            new User(2L,"张三丰",16),
            new User(3L,"张三峰",37),
            new User(4L,"张三疯",17),
            new User(5L,"张三风",21)
    );

    public List<String> getAdultsUserName(){
        return users.stream()
                .filter(user -> user.getAge() > 18)
                .map(User::getName)
                .collect(Collectors.toList());
    }
}