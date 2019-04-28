package com.mislbd.ababil.foreignremittance.utils;

import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class PagedResultBuilderFR {

  private final ModelMapper modelMapper;

  public PagedResultBuilderFR(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public static <T, R> PagedResult<R> build(Page<T> page, Class clazz) {
    return new PagedResult(
        (List)
            page.getContent()
                .stream()
                .map(
                    (entity) -> {
                      ModelMapper mapper = new ModelMapper();
                      return mapper.map(entity, clazz);
                    })
                .collect(Collectors.toList()),
        page.getSize(),
        page.getNumber(),
        page.getTotalPages());
  }
}
