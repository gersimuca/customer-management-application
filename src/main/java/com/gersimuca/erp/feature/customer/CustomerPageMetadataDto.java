package com.gersimuca.erp.feature.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gersimuca
 */
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPageMetadataDto {
  private Long totalElements;

  private Integer totalPages;

  private Integer size;

  private Integer number;

  private Boolean first;

  private Boolean last;
}
