package com.gersimuca.erp.feature.test;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;

public interface CustomerMapper {
  @Mapping(target = "id", source = "key")
  @Mapping(target = "name", source = "value")
  CustomerDto mapToDtoMapEntry(Map.Entry<Integer, String> entry);

  default List<CustomerDto> mapToDtoList(Map<Integer, String> map) {
    return map.entrySet().stream().map(this::mapToDtoMapEntry).collect(Collectors.toList());
  }

  @InheritInverseConfiguration(name = "mapToDtoMapEntry")
  default Map.Entry<Long, String> mapToMapEntry(CustomerDto dto) {
    return new AbstractMap.SimpleEntry<>(dto.getCustomerId(), dto.getCustomerName());
  }

  default Map<Long, String> mapToMap(List<CustomerDto> dtos) {
    return dtos.stream()
        .map(this::mapToMapEntry)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }
}
