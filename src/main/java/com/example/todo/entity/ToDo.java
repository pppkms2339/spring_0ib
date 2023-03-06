package com.example.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ToDo {

    @NotNull
    private String id;

    @NotNull
    @NotEmpty
    private String description;

    private LocalDateTime created;

    private LocalDateTime updated;

    private boolean completed;

    public ToDo() {
        LocalDateTime date = LocalDateTime.now();
        created = date;
        updated = date;
        id = UUID.randomUUID().toString();
    }

    public ToDo(String description) {
        this();
        this.description = description;
    }
}
