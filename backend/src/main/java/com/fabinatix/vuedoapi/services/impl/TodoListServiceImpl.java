package com.fabinatix.vuedoapi.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabinatix.vuedoapi.domain.entities.TodoList;
import com.fabinatix.vuedoapi.repositories.TodoListRepository;
import com.fabinatix.vuedoapi.services.TodoListService;

@Service
public class TodoListServiceImpl implements TodoListService {

  private final TodoListRepository todoListRepository;

  public TodoListServiceImpl(TodoListRepository todoListRepository) {
    this.todoListRepository = todoListRepository;
  }

  @Override
  public TodoList save(TodoList todoList) {
    if(null != todoList.getId()) {
      throw new IllegalArgumentException("Todo list already has an ID!");
    }
    if (todoList.getTitle() == null || todoList.getTitle().isBlank()) {
      throw new IllegalArgumentException("Todo list title must be present!");
    }
    return todoListRepository.save(todoList);
  }

  @Override
  public List<TodoList> findAll() {
    return StreamSupport.stream(todoListRepository
      .findAll()
      .spliterator(),
      false).collect(Collectors.toList());
  }

  @Override
  public Optional<TodoList> findOne(UUID todoListId) {
    return todoListRepository.findById(todoListId);
  }

  @Transactional
  @Override
  public TodoList partialUpdate(UUID todoListId, TodoList todoList) {
    todoList.setId(todoListId);

    return todoListRepository.findById(todoListId).map(existingTodoList -> {
      Optional.ofNullable(todoList.getTitle()).ifPresent(existingTodoList::setTitle);
      Optional.ofNullable(todoList.getDescription()).ifPresent(existingTodoList::setDescription);
      return todoListRepository.save(existingTodoList);
    }).orElseThrow(() -> new RuntimeException("Todo list does not exist"));
  }

  @Transactional
  @Override
  public TodoList update(UUID todoListId, TodoList todoList) {
    if(null == todoList.getId()) {
      throw new IllegalArgumentException("Todo list must have an ID");
    }
    if(!Objects.equals(todoList.getId(), todoListId)) {
      throw new IllegalArgumentException("Attempting to change todo list ID, this is not permitted");
    }

    TodoList exisitingTodoList = todoListRepository.findById(todoListId).orElseThrow(() -> new IllegalArgumentException("Task list not found"));

    exisitingTodoList.setTitle(todoList.getTitle());
    exisitingTodoList.setDescription(todoList.getDescription());

    return todoListRepository.save(exisitingTodoList);
  }

  @Override
  public void delete(UUID todoListId) {
    todoListRepository.deleteById(todoListId);
  }

}
