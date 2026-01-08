package com.fabinatix.vuedoapi.task;

import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fabinatix.vuedoapi.service.TodoListService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DeleteTodoListTask {

  private final TodoListService todoListService;

  public DeleteTodoListTask(TodoListService todoListService) {
    this.todoListService = todoListService;
  }

  @Scheduled(fixedRateString = "${vuedoapi.expirationSeconds}", timeUnit = TimeUnit.SECONDS)
  public void deleteExpiredTodoLists() {
    try {
      todoListService.deleteExpired();
      log.info("Expired todo lists deleted successfully.");
    } catch (Exception e) {
      log.error("Error occurred while deleting expired todo lists: ", e);
    }
  }
}
