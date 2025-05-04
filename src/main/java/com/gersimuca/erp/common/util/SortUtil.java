package com.gersimuca.erp.common.util;

import com.gersimuca.erp.common.validation.SortFieldValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SortUtil {
  /**
   * Parse sort string into JPA Sort object
   *
   * @param sortString the comma seperated string with prefixes
   * @return the Sort
   */
  public static Sort parseSort(String sortString, Class<?> type) {
    Sort sort = Sort.unsorted();
    if (sortString != null && !sortString.isEmpty()) {
      String[] sortParams = sortString.split(",");
      for (String param : sortParams) {
        boolean descending = param.startsWith("-");
        String field = param.startsWith("+") || descending ? param.substring(1) : param;

        SortFieldValidator.validateSortField(field, type);

        sort = sort.and(Sort.by(descending ? Sort.Order.desc(field) : Sort.Order.asc(field)));
      }
    }
    return sort;
  }
}
