package com.smartjob.eam.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenericMapperConverterUtils {

  private final ModelMapper modelMapper;

  public GenericMapperConverterUtils(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public <T, U> U convertToDto(T obj, Class<U> dtoClass) {
    return modelMapper.map(obj, dtoClass);
  }

  public <T, U> T convertToEntity(U dto, Class<T> entityClass) {
    return modelMapper.map(dto, entityClass);
  }

  public <T, U> List<U> convertListToListDto(List<T> objects, Class<U> dtoClass) {
    return objects.stream()
            .map(obj -> modelMapper.map(obj, dtoClass))
            .collect(Collectors.toList());
  }

  public <T, U> List<T> convertListDtoToListEntity(List<U> dtos, Class<T> entityClass) {
    return dtos.stream()
            .map(dto -> modelMapper.map(dto, entityClass))
            .collect(Collectors.toList());
  }
}
