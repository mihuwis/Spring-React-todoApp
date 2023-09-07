package com.progresspoint.demofirstWebApp.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoResource {
    private ToDoService toDoService;

    public TodoResource(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username){
        return toDoService.findByUserName(username);
    }
}
