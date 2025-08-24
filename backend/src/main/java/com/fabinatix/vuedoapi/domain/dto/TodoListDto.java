package com.fabinatix.vuedoapi.domain.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoListDto {
  
  private UUID id;

  private String title;

  private String description;

  private List<TodoDto> todos;

}
