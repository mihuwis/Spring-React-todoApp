package com.progresspoint.demofirstWebApp.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodoById(@PathVariable String username, @PathVariable Integer id){
        return toDoService.findById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String username, @PathVariable Integer id){
        toDoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> updateTodoById(@PathVariable String username, @PathVariable Integer id, @RequestBody Todo todo){
        toDoService.updateTodo(todo);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo){

        return toDoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
    }
}
