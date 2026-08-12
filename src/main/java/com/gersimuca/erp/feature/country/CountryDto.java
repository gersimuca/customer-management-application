package com.gersimuca.erp.feature.country;

import com.gersimuca.erp.common.annotation.DataTransferObject;
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
public class CountryDto {
  private Long countryId;

  @Size(min = 2, max = 2)
  @NotBlank
  private String countryCode;

  @NotBlank private String countryName;
}
