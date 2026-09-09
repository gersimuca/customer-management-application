package com.gersimuca.erp.feature.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author gersimuca
 */
public interface CustomerService {
  Page<CustomerDto> search(Status status, Long ownerId, String search, Pageable pageable);

  CustomerDto findById(Long id);

  CustomerDto create(CustomerDto dto);

  CustomerDto update(Long id, CustomerDto dto);

  void delete(Long id);
}
