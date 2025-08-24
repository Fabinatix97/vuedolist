package com.fabinatix.vuedoapi.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fabinatix.vuedoapi.domain.dto.TodoListDto;
import com.fabinatix.vuedoapi.domain.entities.TodoList;
import com.fabinatix.vuedoapi.mappers.Mapper;

@Component
public class TodoListMapperImpl implements Mapper<TodoList, TodoListDto> {

  private ModelMapper modelMapper;

  public TodoListMapperImpl(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public TodoListDto mapTo(TodoList todoList) {
    return modelMapper.map(todoList, TodoListDto.class);
  }

  @Override
  public TodoList mapFrom(TodoListDto todoListDto) {
    return modelMapper.map(todoListDto, TodoList.class);
  }

}
