package com.gersimuca.erp.feature.customer;

import com.gersimuca.erp.common.MapperConfig;
import com.gersimuca.erp.feature.user.JwtMapper;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author gersimuca
 */
@Mapper(config = MapperConfig.class, uses = JwtMapper.class)
public interface CustomerMapper {
  CustomerDto toDto(final CustomerEntity entity);

  List<CustomerDto> toDtoList(final List<CustomerEntity> entities);

  CustomerEntity toEntity(final CustomerDto dto);

  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "lastUpdatedAt", ignore = true)
  @Mapping(target = "lastModifiedBy", ignore = true)
  void copyToEntity(CustomerDto dto, @MappingTarget CustomerEntity entity);
}
