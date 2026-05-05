package com.gersimuca.erp.configuration.jpa;

import com.gersimuca.erp.common.AuthenticationProvider;
import com.gersimuca.erp.common.exception.AuthenticationNotSupportedException;
import com.gersimuca.erp.feature.user.UserEntity;
import com.gersimuca.erp.feature.user.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component("auditorProvider")
@RequiredArgsConstructor
public class AuditorProvider implements AuditorAware<Long> {

  private final UserRepository userRepository;

  @Override
  @NonNull
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Optional<Long> getCurrentAuditor() {
    var authentication = AuthenticationProvider.getAuthenticationFromContext();
    if (authentication == null) {
      return getUserId("System");
    }
    var username = getUsername(authentication);
    return getUserId(username);
  }

  /**
   * Get logged-in principal's username from authentication context
   *
   * @param authentication the authentication
   * @return the username claim
   */
  private String getUsername(final Authentication authentication) {
    if (authentication.getPrincipal() instanceof Jwt jwt) {
      return jwt.getClaimAsString("preferred_username");
    } else if (authentication.getPrincipal().equals("anonymousUser")) {
      return "System";
    }
    throw new AuthenticationNotSupportedException();
  }

  private Optional<Long> getUserId(final String username) {
    return userRepository.findByUsername(username).map(UserEntity::getUserId);
  }
}
