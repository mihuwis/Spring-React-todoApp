package com.progresspoint.demofirstWebApp.todo.repository;

import com.progresspoint.demofirstWebApp.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByUsername(String username);

}
