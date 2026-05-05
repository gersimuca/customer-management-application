package com.gersimuca.erp.feature.user;

import com.gersimuca.erp.common.annotation.DataTransferObject;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DataTransferObject
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
  private Long businessKey;

  @NotBlank(message = "Username is mandatory")
  @Size(max = 8, message = "Username must be at most 8 characters")
  private String username;

  @NotBlank(message = "Given name is mandatory")
  private String givenName;

  @NotBlank(message = "Family name is mandatory")
  private String familyName;

  @Email(message = "Email should be valid")
  private String email;

  private String preferredLanguage;
}
