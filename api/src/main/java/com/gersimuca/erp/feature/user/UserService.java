package com.gersimuca.erp.feature.user;

import com.gersimuca.erp.common.exception.EntityAlreadyExistsException;
import com.gersimuca.erp.common.exception.EntityNotFoundException;
import com.gersimuca.erp.common.util.LoggerUtils;
import com.gersimuca.erp.common.util.ValidationUtil;
import jakarta.validation.Validator;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
  private final UserMapper mapper;
  private final UserRepository repository;
  private final Validator validator;

  public UserDto getCurrentUser(String username) {
    LoggerUtils.info(log, "User loaded with username {}", username);
    final UserEntity userEntity =
        repository
            .findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException(UserEntity.class, username));
    return mapper.mapToDto(userEntity);
  }

  private boolean existsByUsername(String username) {
    return repository.existsByUsername(username);
  }

  @Transactional
  public UserDto createUser(final UserDto userDto) {
    ValidationUtil.validate(validator, userDto);

    final String username = userDto.getUsername();
    if (existsByUsername(username)) {
      throw new EntityAlreadyExistsException(UserEntity.class, username);
    }

    final UserEntity userEntity = mapper.mapToEntity(userDto);

    LoggerUtils.info(log, "Database user created: {}", username);
    final UserEntity createdUser = repository.statefulSave(userEntity);

    return mapper.mapToDto(createdUser);
  }

  public void deleteUser(final String username) {
    Optional<UserEntity> client = repository.findByUsername(username);
    client.ifPresentOrElse(
        repository::delete,
        () -> {
          throw new EntityNotFoundException(
              UserEntity.class, String.format("User not found with username: %s", username));
        });
  }

  public UserDto updateClient(final UserDto userDto) {
    ValidationUtil.validate(validator, userDto);

    UserEntity userEntity =
        repository
            .findByUsername(userDto.getUsername())
            .orElseThrow(
                () -> new EntityNotFoundException(UserEntity.class, userDto.getUsername()));

    userEntity = mapper.mapToEntity(userDto);
    repository.save(userEntity);
    return mapper.mapToDto(userEntity);
  }
}
