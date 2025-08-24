package com.fabinatix.vuedoapi;

import java.util.List;

import com.fabinatix.vuedoapi.domain.entities.Todo;
import com.fabinatix.vuedoapi.domain.entities.TodoList;
import com.fabinatix.vuedoapi.domain.entities.TodoPriority;
import com.fabinatix.vuedoapi.domain.entities.TodoStatus;

public final class TestDataUtil {

  private TestDataUtil() {
  }

  public static Todo createTestTodoA() {
    return Todo.builder()
        .title("Todo A")
        .description("Description for Todo A")
        .status(TodoStatus.OPEN)
        .priority(TodoPriority.LOW)
        .build();
  }

  public static Todo createTestTodoB() {
    return Todo.builder()
        .title("Todo B")
        .description("Description for Todo B")
        .status(TodoStatus.DONE)
        .priority(TodoPriority.MEDIUM)
        .build();
  }

  public static Todo createTestTodoC() {
    return Todo.builder()
        .title("Todo C")
        .description("Description for Todo C")
        .status(TodoStatus.OPEN)
        .priority(TodoPriority.HIGH)
        .build();
  }

  public static TodoList createTestTodoListA(Todo... todos) {
    return TodoList.builder()
        .title("Todo List A")
        .description("Description for Todo List A")
        .todos(todos != null ? List.of(todos) : null)
        .build();
  }


}
