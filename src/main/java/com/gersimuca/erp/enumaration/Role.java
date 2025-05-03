package com.gersimuca.erp.enumaration;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role {
  CLIENT(Collections.emptySet()),
  EMPLOYEE(
      Set.of(
          Permission.EMPLOYEE_READ,
          Permission.EMPLOYEE_UPDATE,
          Permission.EMPLOYEE_DELETE,
          Permission.EMPLOYEE_CREATE)),
  SYSTEM_ADMIN(
      Set.of(
          Permission.SYSTEM_ADMIN_READ,
          Permission.SYSTEM_ADMIN_CREATE,
          Permission.SYSTEM_ADMIN_UPDATE,
          Permission.SYSTEM_ADMIN_DELETE));

  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities =
        getPermissions().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
