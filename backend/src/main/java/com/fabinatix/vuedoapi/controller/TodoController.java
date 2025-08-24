package com.fabinatix.vuedoapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fabinatix.vuedoapi.domain.dto.TodoDto;
import com.fabinatix.vuedoapi.domain.entities.Todo;
import com.fabinatix.vuedoapi.mappers.Mapper;
import com.fabinatix.vuedoapi.services.TodoService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(path = "/todo-lists/{todo_list_id}/todos")
public class TodoController {

  private TodoService todoService;

  private Mapper<Todo, TodoDto> todoMapper;

  public TodoController(TodoService todoService, Mapper<Todo, TodoDto> todoMapper) {
    this.todoService = todoService;
    this.todoMapper = todoMapper;
  }

  @PostMapping
  public ResponseEntity<TodoDto> createTodo(
    @PathVariable UUID todo_list_id,
    @RequestBody TodoDto todoDto
  ) {
    Todo todo = todoMapper.mapFrom(todoDto);
    Todo savedTodo = todoService.save(todo_list_id, todo);
    TodoDto savedTodoDto = todoMapper.mapTo(savedTodo);
    return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
  }
  
  @GetMapping
  public List<TodoDto> listTodos(@PathVariable UUID todo_list_id) {
    List<Todo> todos = todoService.findAll(todo_list_id);
    return todos.stream()
      .map(todoMapper::mapTo)
      .collect(Collectors.toList());
  }

  @GetMapping(path = "/{todo_id}")
  public ResponseEntity<TodoDto> getTodo(
    @PathVariable UUID todo_list_id,
    @PathVariable UUID todo_id
  ) {
    Optional<Todo> foundTodo = todoService.findOne(todo_list_id, todo_id);
    return foundTodo.map(todo -> {
      TodoDto todoDto = todoMapper.mapTo(todo);
      return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PatchMapping(path = "/{todo_id}")
  public ResponseEntity<TodoDto> partiallyUpdateTodo(
    @PathVariable UUID todo_list_id,
    @PathVariable UUID todo_id,
    @RequestBody TodoDto todoDto
  ) {
    Todo todo = todoMapper.mapFrom(todoDto);
    Todo updatedTodo = todoService.partialUpdate(todo_list_id, todo_id, todo);
    TodoDto updatedTodoDto = todoMapper.mapTo(updatedTodo);

    return new ResponseEntity<>(updatedTodoDto, HttpStatus.OK);
  }
  
  @PutMapping(path = "/{todo_id}")
  public ResponseEntity<TodoDto> updateTodo(
    @PathVariable UUID todo_list_id,
    @PathVariable UUID todo_id,
    @RequestBody TodoDto todoDto
  ) {
    Todo updatedTodo = todoService.update(
      todo_list_id,
      todo_id,
      todoMapper.mapFrom(todoDto)
    );
    TodoDto updatedTodoDto = todoMapper.mapTo(updatedTodo);
    
    return new ResponseEntity<>(updatedTodoDto, HttpStatus.OK);
  }

  @DeleteMapping(path = "/{todo_id}")
  public ResponseEntity<Void> deleteTodo(
    @PathVariable UUID todo_list_id,
    @PathVariable UUID todo_id
  ) {
    todoService.delete(todo_list_id, todo_id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
