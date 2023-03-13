package com.example.todo.repository;

import com.example.todo.entity.ToDo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ToDoRepository implements CommonRepository<ToDo> {

    public static final String SQL_UPDATE = "UPDATE todo SET description = :description, created = :created, updated = :updated, completed = :completed WHERE id = :id";
    public static final String SQL_INSERT = "INSERT INTO todo (id, description, created, updated, completed) VALUES (:id, :description, :created, :updated, :completed)";

    public static final String SQL_SELECT_ALL = "SELECT * FROM todo";

    public static final String SQL_SELECT_BY_ID = "SELECT * FROM todo WHERE id = :id";

    public static final String SQL_DELETE = "DELETE FROM todo WHERE id = :id";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ToDoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<ToDo> toDoRowMapper = (ResultSet rs, int rowNum) -> {
        ToDo toDo = new ToDo();
        toDo.setId(rs.getString("id"));
        toDo.setDescription(rs.getString("description"));
        toDo.setCreated(rs.getTimestamp("created").toLocalDateTime());
        toDo.setUpdated(rs.getTimestamp("updated").toLocalDateTime());
        toDo.setCompleted(rs.getBoolean("completed"));
        return toDo;
    };

    @Override
    public ToDo save(ToDo entity) {
        ToDo result = findById(entity.getId());
        if (result != null) {
            // Обновление записи
            result.setDescription(entity.getDescription());
            result.setCompleted(entity.isCompleted());
            result.setUpdated(LocalDateTime.now());
            return upsert(result, SQL_UPDATE);
        }
        return upsert(entity, SQL_INSERT);
    }

    @Override
    public Iterable<ToDo> save(Collection<ToDo> entities) {
        entities.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(ToDo entity) {
        jdbcTemplate.update(SQL_DELETE, Collections.singletonMap("id", entity.getId()));
    }

    @Override
    public ToDo findById(String id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, Collections.singletonMap("id", id), toDoRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Collection<ToDo> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, toDoRowMapper);
    }

    private ToDo upsert(ToDo toDo, String sql) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", toDo.getId());
        namedParameters.put("description", toDo.getDescription());
        namedParameters.put("created", toDo.getCreated());
        namedParameters.put("update", toDo.getUpdated());
        namedParameters.put("completed", toDo.isCompleted());
        jdbcTemplate.update(sql, namedParameters);
        return findById(toDo.getId());
    }

}
