package com.gersimuca.erp.common.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * @author gersimuca
 */
@Component
public class PageableFactory {

  private static final int DEFAULT_PAGE = 0;
  private static final int DEFAULT_SIZE = 20;
  private static final String DEFAULT_SORT = "customerId";

  public Pageable create(final Integer page, final Integer size, final String sort) {
    return PageRequest.of(
        page != null ? page : DEFAULT_PAGE, size != null ? size : DEFAULT_SIZE, createSort(sort));
  }

  private Sort createSort(final String sort) {
    return Sort.by(sort != null ? sort : DEFAULT_SORT);
  }
}
