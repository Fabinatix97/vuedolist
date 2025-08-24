package com.fabinatix.vuedoapi.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import com.fabinatix.vuedoapi.TestDataUtil;
import com.fabinatix.vuedoapi.domain.entities.Todo;
import com.fabinatix.vuedoapi.domain.entities.TodoList;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TodoListRepositoryIntegrationTest {

  private TodoListRepository testTodoListRepository;

  @Autowired
  public TodoListRepositoryIntegrationTest(TodoListRepository testTodoListRepository) {
    this.testTodoListRepository = testTodoListRepository;
  }

  @Test
  @Transactional
  public void testThatTodoListCanBeCreatedAndRecalled() {
    Todo todoA = TestDataUtil.createTestTodoA();
    Todo todoB = TestDataUtil.createTestTodoB();
    Todo todoC = TestDataUtil.createTestTodoC();
    TodoList todoList = TestDataUtil.createTestTodoListA(todoA, todoB, todoC);
    testTodoListRepository.save(todoList);
    
    Optional<TodoList> retrievedTodoList = testTodoListRepository.findById(todoList.getId());
    assertThat(retrievedTodoList).isPresent();
    assertThat(retrievedTodoList.get()).isEqualTo(todoList);
  }

  @Test
  @Transactional
  public void testThatMultipleTodoListsCanBeCreatedAndRecalled() {
    Todo todoA = TestDataUtil.createTestTodoA();
    Todo todoB = TestDataUtil.createTestTodoB();
    Todo todoC = TestDataUtil.createTestTodoC();
    TodoList todoListA = TestDataUtil.createTestTodoListA(todoA, todoB, todoC);
    TodoList todoListB = TestDataUtil.createTestTodoListA(todoB, todoC);
    TodoList todoListC = TestDataUtil.createTestTodoListA(todoA, todoC);
    testTodoListRepository.save(todoListA);
    testTodoListRepository.save(todoListB);
    testTodoListRepository.save(todoListC);

    Iterable<TodoList> retrievedTodoLists = testTodoListRepository.findAll();
    assertThat(retrievedTodoLists)
      .hasSize(3)
      .containsExactly(todoListA, todoListB, todoListC);
  }

  @Test
  public void testThatTodoListCanBeUpdate() {
    Todo todoA = TestDataUtil.createTestTodoA();
    Todo todoB = TestDataUtil.createTestTodoB();
    Todo todoC = TestDataUtil.createTestTodoC();
    TodoList todoList = TestDataUtil.createTestTodoListA(todoA, todoB, todoC);
    testTodoListRepository.save(todoList);
    todoList.setTitle("Updated Title");
    testTodoListRepository.save(todoList);
    
    Optional<TodoList> retrievedTodoList = testTodoListRepository.findById(todoList.getId());
    assertThat(retrievedTodoList).isPresent();
    assertThat(retrievedTodoList.get().getTitle()).isEqualTo("Updated Title");
  }

  @Test
  public void testThatTodoListCanBeDeleted() {
    Todo todoA = TestDataUtil.createTestTodoA();
    Todo todoB = TestDataUtil.createTestTodoB();
    Todo todoC = TestDataUtil.createTestTodoC();
    TodoList todoList = TestDataUtil.createTestTodoListA(todoA, todoB, todoC);
    testTodoListRepository.save(todoList);
    testTodoListRepository.delete(todoList);

    Optional<TodoList> retrievedTodoList = testTodoListRepository.findById(todoList.getId());
    assertThat(retrievedTodoList).isNotPresent();
  }


}
