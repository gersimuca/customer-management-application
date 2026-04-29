package com.gersimuca.erp.feature.user;

import static com.gersimuca.erp.common.AuthorizationExpressions.IS_AUTHORIZED;
import static org.springframework.http.ResponseEntity.ok;

import com.gersimuca.erp.api.UsersApi;
import com.gersimuca.erp.common.AuthenticationProvider;
import com.gersimuca.erp.feature.country.CountryService;
import com.gersimuca.erp.model.RolesResponse;
import com.gersimuca.erp.model.UserResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

  @Qualifier("userServiceImpl")
  private final UserService userService;

  private final UserMapper mapper;

  @Qualifier("countryServiceImpl")
  private final CountryService countryService;

  @Override
  @PreAuthorize(IS_AUTHORIZED)
  public ResponseEntity<UserResponse> createUser() {
    final Authentication authentication = AuthenticationProvider.getAuthenticationFromContext();
    final UserDto userDto = mapper.mapToDto((Jwt) authentication.getPrincipal());
    return ok(new UserResponse().user(mapper.mapToModel(userService.createUser(userDto))));
  }

  @Override
  @PreAuthorize(IS_AUTHORIZED)
  public ResponseEntity<UserResponse> getCurrentUser() {
    final String username = AuthenticationProvider.getPreferredUsernameFromContext();
    return ok(new UserResponse().user(mapper.mapToModel(userService.mustLoadByUsername(username))));
  }

  @Override
  @PreAuthorize(IS_AUTHORIZED)
  public ResponseEntity<RolesResponse> getCurrentUserRoles() {
    final Authentication authentication = AuthenticationProvider.getAuthenticationFromContext();
    final List<String> roles =
        authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    return ok(new RolesResponse().roles(roles));
  }

  @GetMapping("/users/data")
  public ResponseEntity<?> getData() {
    return ok(service.countryAvailableCountries());
  }
}
