package com.gersimuca.erp.configuration;

import com.gersimuca.erp.common.repository.BaseRepositoryImpl;
import com.gersimuca.erp.feature.user.UserEntity;
import com.gersimuca.erp.feature.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableJpaRepositories(
    basePackages = "com.gersimuca.erp.feature",
    repositoryBaseClass = BaseRepositoryImpl.class)
public class JpaConfig {

  @Bean
  public AuditorAware<UserEntity> auditorAware(UserRepository userRepository) {
    return new AuditorProvider(userRepository);
  }
}
