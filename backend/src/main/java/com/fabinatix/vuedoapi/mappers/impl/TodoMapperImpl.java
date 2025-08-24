package com.fabinatix.vuedoapi.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fabinatix.vuedoapi.domain.dto.TodoDto;
import com.fabinatix.vuedoapi.domain.entities.Todo;
import com.fabinatix.vuedoapi.mappers.Mapper;

@Component
public class TodoMapperImpl implements Mapper<Todo, TodoDto> {

  private ModelMapper modelMapper;
  

  public TodoMapperImpl(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public TodoDto mapTo(Todo todo) {
    return modelMapper.map(todo, TodoDto.class);
  }

  @Override
  public Todo mapFrom(TodoDto todoDto) {
    return modelMapper.map(todoDto, Todo.class);
  }

}
