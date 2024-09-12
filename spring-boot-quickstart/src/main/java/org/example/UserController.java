package org.example;


import jakarta.annotation.Resource;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/adults")
    public List<String> getAdultsUserName(){
        return userService.getAdultsUserName();
    }
}