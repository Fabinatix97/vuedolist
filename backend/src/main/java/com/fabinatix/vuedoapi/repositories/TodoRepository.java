package com.fabinatix.vuedoapi.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fabinatix.vuedoapi.domain.entities.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, UUID> {

    List<Todo> findByTodoListId(UUID todoListId);
    Optional<Todo> findByTodoListIdAndId(UUID todoListId, UUID id);
    void deleteByTodoListIdAndId(UUID todoListId, UUID id);

}
