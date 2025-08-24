package com.fabinatix.vuedoapi.domain.dto;

import java.util.UUID;

import com.fabinatix.vuedoapi.domain.entities.TodoPriority;
import com.fabinatix.vuedoapi.domain.entities.TodoStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {

  private UUID id;

  private String title;

  private String description;

  private TodoStatus status;

  private TodoPriority priority;

}
