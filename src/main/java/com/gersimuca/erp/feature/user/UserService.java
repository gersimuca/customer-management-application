package com.gersimuca.erp.feature.user;

public interface UserService {
  UserDto mustLoadByUsername(final String username);

  UserDto createUser(final UserDto userDto);
}
