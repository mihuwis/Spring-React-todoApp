package com.progresspoint.demofirstWebApp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class ToDoControllerJpa {

    private TodoRepository todoRepository;

    public ToDoControllerJpa(ToDoService toDoService, TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @RequestMapping("list-todos")
    public String listAllToDos(ModelMap modelMap){
        String username = getLoggedInUseername();
        List<Todo> listOfToDos = todoRepository.findByUsername(username);
        modelMap.addAttribute("todos", listOfToDos);
        return "todos";
    }

    @RequestMapping(value="add-todo", method= RequestMethod.GET)
    public String showNewTodoPage(ModelMap modelMap){
        String username = getLoggedInUseername();
        Todo todo = new Todo(0, username, "",
                LocalDate.now().plusMonths(1), false);
        modelMap.put("todo", todo);
        return "addtodo";
    }

    @RequestMapping(value="add-todo", method= RequestMethod.POST)
    public String addNewTodoPage(ModelMap modelMap, @Valid Todo todo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addtodo";
        }
        String username = getLoggedInUseername();
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String removeById(@RequestParam long id){
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value="edit-todo", method = RequestMethod.GET)
    public String showUpdateToDoPage(@RequestParam long id, ModelMap modelMap){
        Todo todo = todoRepository.findById(id).get();
        modelMap.addAttribute("todo", todo);
        return "addtodo";
    }

    @RequestMapping(value="edit-todo", method= RequestMethod.POST)
    public String updateTodo(ModelMap modelMap, @Valid Todo todo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addtodo";
        }
        String username = getLoggedInUseername();
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:list-todos";
    }

    private static String getLoggedInUseername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
