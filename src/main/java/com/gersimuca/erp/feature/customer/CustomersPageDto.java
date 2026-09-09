package com.gersimuca.erp.feature.customer;

import java.util.List;
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
public class CustomersPageDto {
  private CustomerPageMetadataDto metadataDto;
  private List<CustomerDto> customerDtoList;
}
