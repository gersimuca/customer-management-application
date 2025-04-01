package com.gersimuca.erp.feature.user;

import static org.springframework.http.ResponseEntity.ok;

import com.gersimuca.erp.api.UsersApi;
import com.gersimuca.erp.common.AuthenticationProvider;
import com.gersimuca.erp.model.RolesResponse;
import com.gersimuca.erp.model.UserResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

  private final UserService service;
  private final UserMapper mapper;

  @Override
  public ResponseEntity<UserResponse> createUser() {
    final Authentication authentication = AuthenticationProvider.getAuthenticationFromContext();
    final UserDto userDto = mapper.mapToDto((Jwt) authentication.getPrincipal());
    return ok(new UserResponse().user(mapper.mapToModel(service.createUser(userDto))));
  }

  @Override
  public ResponseEntity<UserResponse> getCurrentUser() {
    final String username = AuthenticationProvider.getPreferredUsernameFromContext();
    return ok(new UserResponse().user(mapper.mapToModel(service.getCurrentUser(username))));
  }

  @Override
  public ResponseEntity<RolesResponse> getCurrentUserRoles() {
    final Authentication authentication = AuthenticationProvider.getAuthenticationFromContext();
    final List<String> roles =
        authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    return ok(new RolesResponse().roles(roles));
  }
}
