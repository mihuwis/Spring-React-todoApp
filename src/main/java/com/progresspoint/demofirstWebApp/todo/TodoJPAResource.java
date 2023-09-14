package com.progresspoint.demofirstWebApp.todo;

import com.progresspoint.demofirstWebApp.todo.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoJPAResource {

    private TodoRepository todoRepository;

    public TodoJPAResource(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username){
        return todoRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodoById(@PathVariable String username, @PathVariable Integer id){
        return todoRepository.getReferenceById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String username, @PathVariable Integer id){
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodoById(@PathVariable String username, @PathVariable Integer id, @RequestBody Todo todo){
        todoRepository.save(todo);
        return todo;
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo){
        todo.setUsername(username);
        todo.setId(null);
        return todoRepository.save(todo);
    }
}
