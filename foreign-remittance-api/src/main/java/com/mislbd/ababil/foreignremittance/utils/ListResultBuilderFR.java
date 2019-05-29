package com.mislbd.ababil.foreignremittance.utils;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

public class ListResultBuilderFR {
  private final ModelMapper modelMapper;

  public ListResultBuilderFR(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public static <T, R> List<R> build(List<T> items, Class clazz) {
    return (List)
        items
            .stream()
            .map(
                (item) -> {
                  ModelMapper mapper = new ModelMapper();
                  return mapper.map(item, clazz);
                })
            .collect(Collectors.toList());
  }
}
