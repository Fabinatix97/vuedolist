package com.fabinatix.vuedoapi.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import static org.assertj.core.api.Assertions.assertThat;

import com.fabinatix.vuedoapi.TestDataUtil; 
import com.fabinatix.vuedoapi.domain.entities.Todo;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TodoRepositoryIntegrationTest {

  private TodoRepository testTodoRepository;

  @Autowired
  public TodoRepositoryIntegrationTest(TodoRepository todoRepository) {
    this.testTodoRepository = todoRepository;
  }
  
  @Test
  public void testThatTodoCanBeCreatedAndRecalled() {
    Todo todo = TestDataUtil.createTestTodoA();
    testTodoRepository.save(todo);

    Optional<Todo> retrievedTodo = testTodoRepository.findById(todo.getId());
    assertThat(retrievedTodo).isPresent();
    assertThat(retrievedTodo.get()).isEqualTo(todo);
  }

  @Test
  public void testThatMultipleTodosCanBeCreatedAndRecalled() {
    Todo todoA = TestDataUtil.createTestTodoA();
    Todo todoB = TestDataUtil.createTestTodoB();
    Todo todoC = TestDataUtil.createTestTodoC();
    testTodoRepository.save(todoA);
    testTodoRepository.save(todoB);
    testTodoRepository.save(todoC);
    
    Iterable<Todo> retrievedTodos = testTodoRepository.findAll();
    assertThat(retrievedTodos)
      .hasSize(3)
      .containsExactly(todoA, todoB, todoC);
  }

  @Test
  public void testThatTodoCanBeUpdated() {
    Todo todo = TestDataUtil.createTestTodoA();
    testTodoRepository.save(todo);
    todo.setTitle("Updated Title");
    testTodoRepository.save(todo);

    Optional<Todo> retrievedTodo = testTodoRepository.findById(todo.getId());
    assertThat(retrievedTodo).isPresent();
    assertThat(retrievedTodo.get().getTitle()).isEqualTo("Updated Title");
  }

  @Test
  public void testThatTodoCanBeDeleted() {
    Todo todo = TestDataUtil.createTestTodoA();
    testTodoRepository.save(todo);
    testTodoRepository.delete(todo);

    Optional<Todo> retrievedTodo = testTodoRepository.findById(todo.getId());
    assertThat(retrievedTodo).isNotPresent();
  }

}
