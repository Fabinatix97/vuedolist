package com.fabinatix.vuedoapi.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fabinatix.vuedoapi.domain.entities.TodoList;

@Repository
public interface TodoListRepository extends CrudRepository<TodoList, UUID> {

    void deleteByCreatedAtBefore(java.time.Instant dateTime);

}
