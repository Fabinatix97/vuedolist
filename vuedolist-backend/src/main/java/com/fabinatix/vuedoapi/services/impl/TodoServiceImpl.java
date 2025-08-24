package com.fabinatix.vuedoapi.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabinatix.vuedoapi.domain.entities.Todo;
import com.fabinatix.vuedoapi.domain.entities.TodoList;
import com.fabinatix.vuedoapi.domain.entities.TodoPriority;
import com.fabinatix.vuedoapi.domain.entities.TodoStatus;
import com.fabinatix.vuedoapi.repositories.TodoListRepository;
import com.fabinatix.vuedoapi.repositories.TodoRepository;
import com.fabinatix.vuedoapi.services.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

  private final TodoRepository todoRepository;
  private final TodoListRepository todoListRepository;

  public TodoServiceImpl(TodoRepository todoRepository, TodoListRepository todoListRepository) {
    this.todoRepository = todoRepository;
    this.todoListRepository = todoListRepository;
  }

  @Transactional
  @Override
  public Todo save(UUID todoListId, Todo todo) {
    if (null != todo.getId()) {
      throw new IllegalArgumentException("Todo already has an ID!");
    }
    if (null == todo.getTitle() || todo.getTitle().isBlank()) {
      throw new IllegalArgumentException("Todo must have a title!");
    }

    TodoPriority todoPriority = Optional.ofNullable(todo.getPriority()).orElse(TodoPriority.MEDIUM);
    TodoStatus todoStatus = Optional.ofNullable(todo.getStatus()).orElse(TodoStatus.OPEN);

    TodoList todoList = todoListRepository.findById(todoListId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid todo list ID provided!"));

    todo.setPriority(todoPriority);
    todo.setStatus(todoStatus);
    todo.setTodoList(todoList);

    return todoRepository.save(todo);
  }

  @Override
  public List<Todo> findAll(UUID todoListId) {
    return todoRepository.findByTodoListId(todoListId);
  }

  @Override
  public Optional<Todo> findOne(UUID todoListId, UUID todoId) {
    return todoRepository.findByTodoListIdAndId(todoListId, todoId);
  }

  @Transactional
  @Override
  public Todo partialUpdate(UUID todoListId, UUID todoId, Todo todo) {
    todo.setId(todoId);

    return todoRepository.findByTodoListIdAndId(todoListId, todoId).map(existingTodo -> {
      Optional.ofNullable(todo.getTitle()).ifPresent(existingTodo::setTitle);
      Optional.ofNullable(todo.getDescription()).ifPresent(existingTodo::setDescription);
      Optional.ofNullable(todo.getStatus()).ifPresent(existingTodo::setStatus);
      Optional.ofNullable(todo.getPriority()).ifPresent(existingTodo::setPriority);
      return todoRepository.save(existingTodo);
    }).orElseThrow(() -> new RuntimeException("Todo does not exist"));
  }

  @Transactional
  @Override
  public Todo update(UUID todoListId, UUID todoId, Todo todo) {
    if(null == todo.getId()) {
      throw new IllegalArgumentException("Todo must have an ID!");
    }
    if(!Objects.equals(todoId, todo.getId())) {
      throw new IllegalArgumentException("Todo IDs do not match");
    }
    if(null == todo.getPriority()) {
      throw new IllegalArgumentException("Todo must have a valid priority");
    }
    if(null == todo.getStatus()) {
      throw new IllegalArgumentException("Todo must have a valid status");
    }

    Todo existingTodo = todoRepository.findByTodoListIdAndId(todoListId, todoId).orElseThrow(() -> new IllegalArgumentException("Todo not found"));

    existingTodo.setTitle(todo.getTitle());
    existingTodo.setDescription(todo.getDescription());
    existingTodo.setPriority(todo.getPriority());
    existingTodo.setStatus(todo.getStatus());

    return todoRepository.save(existingTodo);
  }

  @Transactional
  @Override
  public void delete(UUID todoListId, UUID todoId) {
    todoRepository.deleteByTodoListIdAndId(todoListId, todoId);
  }

}
