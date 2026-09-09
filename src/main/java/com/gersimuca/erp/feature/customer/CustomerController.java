package com.gersimuca.erp.feature.customer;

import static org.springframework.http.ResponseEntity.ok;

import com.gersimuca.erp.api.CustomersApi;
import com.gersimuca.erp.model.CustomerStatus;
import com.gersimuca.erp.model.CustomersPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gersimuca
 */
@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomersApi {
  private final CustomerService service;
  private final CustomerMapper mapper;

  @Override
  public ResponseEntity<CustomersPageResponse> searchCustomers(
      CustomerStatus status, Long ownerId, String search, Integer page, Integer size, String sort) {
    final CustomersPageDto customersPageDto =
        service.search(mapper.toStatus(status), ownerId, search, page, size, sort);
    return ok(new CustomersPageResponse().customersPage(mapper.toResponse(customersPageDto)));
  }
}
