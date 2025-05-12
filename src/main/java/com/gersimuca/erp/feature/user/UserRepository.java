package com.gersimuca.erp.feature.user;

import com.gersimuca.erp.common.repository.BaseRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmailIgnoreCase(String email);

  boolean existsByUsername(String username);

  Optional<UserEntity> findByUsername(String username);
}
