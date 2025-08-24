package com.fabinatix.vuedoapi.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fabinatix.vuedoapi.domain.entities.TodoList;

@Repository
public interface TodoListRepository extends CrudRepository<TodoList, UUID> {

    // Additional methods specific to TodoList can be added here if needed

}
