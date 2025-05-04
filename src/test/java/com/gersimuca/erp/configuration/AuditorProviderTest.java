package com.gersimuca.erp.configuration;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.gersimuca.erp.common.exception.AuthenticationNotSupportedException;
import com.gersimuca.erp.feature.user.UserEntity;
import com.gersimuca.erp.feature.user.UserRepository;
import com.gersimuca.erp.feature.user.UserTestData;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

@ExtendWith(MockitoExtension.class)
class AuditorProviderTest {
  @Mock private UserRepository userRepository;

  @Mock private SecurityContext securityContext;

  @Mock private Authentication authentication;

  private AuditorProvider auditorProvider;

  @BeforeEach
  void setUp() {
    SecurityContextHolder.setContext(securityContext);
    auditorProvider = new AuditorProvider(userRepository);
  }

  /**
   * Test {@link AuditorProvider#getCurrentAuditor()}
   *
   * <p>Case: User authentication is retrieved
   *
   * <p>Expected: Current auditor is the currently logged-in user
   */
  @Test
  void getCurrentAuditor() {
    var jwt = mock(Jwt.class);
    var user = UserTestData.IRON_MAN_ENTITY;
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(authentication.getPrincipal()).thenReturn(jwt);
    when(jwt.getClaimAsString("preferred_username")).thenReturn(user.getUsername());
    when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

    var currentAuditor = auditorProvider.getCurrentAuditor();

    assertEquals(Optional.of(user.getUserId()), currentAuditor);
    verify(userRepository).findByUsername(user.getUsername());
  }

  /**
   * Test {@link AuditorProvider#getCurrentAuditor()}
   *
   * <p>Case: No user is authenticated
   *
   * <p>Expected: Current auditor will be SYSTEM
   */
  @Test
  void getCurrentAuditor_authenticationIsNull() {
    var systemUser = UserEntity.builder().userId(1L).build();
    when(securityContext.getAuthentication()).thenReturn(null);
    when(userRepository.findByUsername("System")).thenReturn(Optional.of(systemUser));

    var currentAuditor = auditorProvider.getCurrentAuditor();

    assertEquals(Optional.of(systemUser.getUserId()), currentAuditor);
    verify(userRepository).findByUsername("System");
  }

  /**
   * Test {@link AuditorProvider#getCurrentAuditor()}
   *
   * <p>Case: Not a json web token authentication
   *
   * <p>Expected: Should not happen
   */
  @Test
  void getCurrentAuditor_notJwtAuthentication() {
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(authentication.getPrincipal()).thenReturn(new Object());

    assertThatThrownBy(() -> auditorProvider.getCurrentAuditor())
        .isInstanceOf(AuthenticationNotSupportedException.class)
        .hasMessage("Authentication not supported.");

    verifyNoInteractions(userRepository);
  }
}
