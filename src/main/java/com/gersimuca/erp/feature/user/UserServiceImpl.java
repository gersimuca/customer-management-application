package com.gersimuca.erp.feature.user;

import com.gersimuca.erp.common.exception.EntityAlreadyExistsException;
import com.gersimuca.erp.common.exception.EntityNotFoundException;
import com.gersimuca.erp.common.util.LoggerUtils;
import com.gersimuca.erp.common.util.ValidationUtil;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
  private final UserMapper mapper;
  private final UserRepository repository;
  private final Validator validator;

  @Override
  public UserDto mustLoadByUsername(final String username) {
    final UserEntity userEntity =
        repository
            .findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException(UserEntity.class, username));
    return mapper.mapToDto(userEntity);
  }

  @Override
  @Transactional
  public UserDto createUser(final UserDto userDto) {
    ValidationUtil.validate(validator, userDto);

    final String username = userDto.getUsername();
    if (existsByUsername(username)) {
      throw new EntityAlreadyExistsException(UserEntity.class, username);
    }

    final UserEntity userEntity =
        UserEntity.builder()
            .username(username)
            .givenName(userDto.getGivenName())
            .familyName(userDto.getFamilyName())
            .email(userDto.getEmail())
            .build();
    LoggerUtils.info(log, "Database user created");
    final UserEntity createdUser = repository.statefulSave(userEntity);

    return mapper.mapToDto(createdUser);
  }

  public boolean existsByUsername(final String username) {
    return repository.existsByUsername(username);
  }
}
