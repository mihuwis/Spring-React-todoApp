package com.progresspoint.demofirstWebApp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ToDoService {
    private static List<Todo> todoList  = new ArrayList<>();
    private static long count = 0L;
    static {
        todoList.add(new Todo(++count, "Mihu", "Learn Spring",
                LocalDate.now().plusMonths(2), false));
        todoList.add(new Todo(++count, "Mihu", "Get job",
                LocalDate.now().plusMonths(1), false));
        todoList.add(new Todo(++count, "Ada", "Learn Adding",
                LocalDate.now().plusMonths(4), false));
        todoList.add(new Todo(++count, "Pablo", "Learn Pabling",
                LocalDate.now().plusMonths(21), false));
    }

    public List<Todo> findByUserName(String userName){
        return todoList.stream().filter(item -> item.getUsername().equals(userName)).toList();
    }

    public void addTodo(String username, String description, LocalDate date, boolean isDone) {
        todoList.add(new Todo(++count, username, description, date, isDone));
    }

    public void deleteById(long id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todoList.removeIf(predicate);
    }

    public Todo findById(long id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        return todoList.stream().filter(predicate).findFirst().get();
    }


    public void updateTodo(Todo todo) {
        deleteById(todo.getId());
        todoList.add(todo);
    }
}
