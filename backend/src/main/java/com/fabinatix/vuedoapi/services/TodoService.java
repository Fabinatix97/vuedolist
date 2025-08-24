package com.fabinatix.vuedoapi.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fabinatix.vuedoapi.domain.entities.Todo;

public interface TodoService {

  Todo save(UUID todoListId, Todo todo);

  List<Todo> findAll(UUID todoListId);

  Optional<Todo> findOne(UUID todoListId, UUID todoId);

  Todo partialUpdate(UUID todoListId, UUID todoId, Todo todo);

  Todo update(UUID todoListId, UUID todoId, Todo todo);

  void delete(UUID todoListId, UUID todoId);

}
