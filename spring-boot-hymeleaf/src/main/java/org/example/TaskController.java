package org.example;


import org.example.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {
    private List<Task> tasks = new ArrayList<>();

    @GetMapping("/taskList")
    public String taskList(Model model) {
        model.addAttribute("tasks", tasks);
        return "taskList";


    }

    @PostMapping("/addTask")
    public String addTask(@RequestParam String name) {
        Long id = (long) (tasks.size() + 1);
        Task task = new Task(id, name);
        tasks.add(task);
        return "redirect:/taskList";


    }

    @PostMapping("/deleteTask")
    public String deleteTask(@RequestParam Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
        return "redirect:/taskList";
    }


    @PostMapping("/updateTask")
    public String updateTask(@RequestParam Long id) {
        Task task = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (task != null) {
            task.setCompleted(!task.getCompleted());
        }
        return "redirect:/taskList";
    }
}
