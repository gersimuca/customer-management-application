package com.gersimuca.erp.feature.test;

import com.gersimuca.erp.common.annotation.DataTransferObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@DataTransferObject
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDto {
  private Long customerId;
  private String customerName;
  private String customerEmail;
}
