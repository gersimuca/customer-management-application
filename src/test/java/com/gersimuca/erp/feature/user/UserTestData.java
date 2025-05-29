package com.gersimuca.erp.feature.user;

public class UserTestData {
  public static final UserEntity IRON_MAN_ENTITY =
      UserEntity.builder()
          .familyName("Man")
          .givenName("Iron")
          .email("iron.man@marvel.com")
          .username("iron.man")
          .userId(2L)
          .build();
  public static final UserDto IRON_MAN_DTO =
      UserDto.builder()
          .familyName("Man")
          .givenName("Iron")
          .email("iron.man@marvel.com")
          .username("iron.man")
          .userId(2L)
          .build();
}
