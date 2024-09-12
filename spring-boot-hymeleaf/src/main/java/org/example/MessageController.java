package org.example;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {
    @GetMapping("/msg")
    public String getMsg(Model model){
        // 将“message”属性传到视图
        model.addAttribute("message" ,"hello,thymeleaf");
        //返回视图名称：默认在resources/templates/msg.html
        return "msg";
    }
}
