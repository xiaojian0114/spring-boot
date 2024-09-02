package org.example.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final List<User> user = List.of(
            new User(),
            new User(),
            new User(),
            new User(),
            new User(),
            new User(),
    );

    public List<User> getAdultUsers{
        return user.stream() Stream<User>
        .filter(user -> user)
    }
}
