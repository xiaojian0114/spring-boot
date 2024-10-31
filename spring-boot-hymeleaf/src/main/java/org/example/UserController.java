package org.example;




import org.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UsersController {

    // 模拟的用户数据
    private List<User> users = new ArrayList<>();

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String name, @RequestParam String email, Model model) {
        User newUser = new User(users.size() + 1L, name, email);
        users.add(newUser);
        model.addAttribute("users", users);
        return "redirect:/users";
    }


    {
        users.add(new User(1L, "孙悟空", "sun666@huaguoshan.com"));
        users.add(new User(2L, "杨戬", "yang666@tianting.com"));
    }
}
