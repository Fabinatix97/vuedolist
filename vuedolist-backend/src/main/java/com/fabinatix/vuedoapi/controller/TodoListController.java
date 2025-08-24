package com.fabinatix.vuedoapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fabinatix.vuedoapi.domain.dto.TodoListDto;
import com.fabinatix.vuedoapi.domain.entities.TodoList;
import com.fabinatix.vuedoapi.mappers.Mapper;
import com.fabinatix.vuedoapi.services.TodoListService;

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
@RequestMapping(path = "/todo-lists")
public class TodoListController {

  private TodoListService todoListService;

  private Mapper<TodoList, TodoListDto> todoListMapper;

  public TodoListController(TodoListService todoListService, Mapper<TodoList, TodoListDto> todoListMapper) {
    this.todoListService = todoListService;
    this.todoListMapper = todoListMapper;
  }

  @PostMapping
  public ResponseEntity<TodoListDto> createTodoList(@RequestBody TodoListDto todoListDto) {
    TodoList todoList = todoListMapper.mapFrom(todoListDto);
    TodoList savedTodoList = todoListService.save(todoList);
    TodoListDto savedTodoListDto = todoListMapper.mapTo(savedTodoList);
    return new ResponseEntity<>(savedTodoListDto, HttpStatus.CREATED);
  }
  
  @GetMapping
  public List<TodoListDto> listTodoLists() {
    List<TodoList> todoLists = todoListService.findAll();
    return todoLists.stream()
      .map(todoListMapper::mapTo)
      .collect(Collectors.toList());
  }
  
  @GetMapping(path = "/{todo_list_id}")
  public ResponseEntity<TodoListDto> getTodoList(@PathVariable UUID todo_list_id) {
    Optional<TodoList> foundTodoList = todoListService.findOne(todo_list_id);
    return foundTodoList.map(todoList -> {
      TodoListDto todoListDto = todoListMapper.mapTo(todoList);
      return new ResponseEntity<>(todoListDto, HttpStatus.OK);
    }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PatchMapping(path = "/{todo_list_id}")
  public ResponseEntity<TodoListDto> partiallyUpdateTodoList(
    @PathVariable UUID todo_list_id,
    @RequestBody TodoListDto todoListDto
  ) {
    TodoList todoList = todoListMapper.mapFrom(todoListDto);
    TodoList updatedTodoList = todoListService.partialUpdate(todo_list_id, todoList);
    TodoListDto updatedTodoListDto = todoListMapper.mapTo(updatedTodoList);

    return new ResponseEntity<>(updatedTodoListDto, HttpStatus.OK);
  }

  @PutMapping(path = "/{todo_list_id}")
  public ResponseEntity<TodoListDto> updateTodoList(
    @PathVariable UUID todo_list_id,
    @RequestBody TodoListDto todoListDto
  ) {
    TodoList updateTodoList = todoListService.update(
      todo_list_id,
      todoListMapper.mapFrom(todoListDto)
    );
    TodoListDto updateTodoListDto = todoListMapper.mapTo(updateTodoList);

    return new ResponseEntity<>(updateTodoListDto, HttpStatus.OK);
  }

  @DeleteMapping(path = "/{todo_list_id}")
  public ResponseEntity<Void> deleteTodoList(@PathVariable UUID todo_list_id) {
    todoListService.delete(todo_list_id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
}
