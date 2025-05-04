package com.gersimuca.erp.feature.user;

import static com.gersimuca.erp.feature.user.UserTestData.IRON_MAN_DTO;
import static com.gersimuca.erp.feature.user.UserTestData.IRON_MAN_ENTITY;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class UserMapperTest {

  private UserMapper underTest;

  @BeforeEach
  void setUp() {
    underTest = Mappers.getMapper(UserMapper.class);
  }

  @Test
  void mapToDtoEntity() {
    var dto = underTest.mapToDto(IRON_MAN_ENTITY);
    assertThat(dto.getFamilyName()).isEqualTo(IRON_MAN_ENTITY.getFamilyName());
    assertThat(dto.getGivenName()).isEqualTo(IRON_MAN_ENTITY.getGivenName());
    assertThat(dto.getEmail()).isEqualTo(IRON_MAN_ENTITY.getEmail());
    assertThat(dto.getUsername()).isEqualTo(IRON_MAN_ENTITY.getUsername());
  }

  @Test
  void mapToDtoDto() {
    var dto = underTest.mapToModel(IRON_MAN_DTO);
    assertThat(dto.getFamilyName()).isEqualTo(IRON_MAN_ENTITY.getFamilyName());
    assertThat(dto.getGivenName()).isEqualTo(IRON_MAN_ENTITY.getGivenName());
    assertThat(dto.getEmail()).isEqualTo(IRON_MAN_ENTITY.getEmail());
    assertThat(dto.getUsername()).isEqualTo(IRON_MAN_ENTITY.getUsername());
  }

  @Test
  void fromNullEntity() {
    assertThat(underTest.mapToDto((UserEntity) null)).isNull();
  }

  @Test
  void fromNullEntityCollection() {
    assertThat(underTest.mapToDto((UserEntity) null)).isNull();
  }
}
