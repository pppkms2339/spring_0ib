package com.example.todo.controller;

import com.example.todo.entity.ToDo;
import com.example.todo.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class ToDoController {

    private final CommonRepository<ToDo> repository;

    @Autowired
    public ToDoController(CommonRepository<ToDo> repository) {
        this.repository = repository;
    }

    @GetMapping("/todo")
    public ResponseEntity<Collection<ToDo>> getToDos() {
        Collection<ToDo> toDos = repository.findAll();
        if (toDos == null || toDos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(repository.findAll());
    }

    @RequestMapping(value = "/todo", method = {RequestMethod.POST,
            RequestMethod.PUT})
    public ResponseEntity<?> addTodo(
            @Valid @RequestBody ToDo toDo,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        ToDo todoFromDb = repository.save(toDo);
        return ResponseEntity.status(HttpStatus.CREATED).body(todoFromDb);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<ToDo> deleteToDo(@PathVariable String id) {
        ToDo toDo = repository.findById(id);
        if (toDo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        repository.delete(toDo);
        return ResponseEntity.noContent().build();
    }

}
