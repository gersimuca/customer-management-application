package com.gersimuca.erp.common.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface BaseRepository<T, I extends Serializable>
    extends JpaRepository<T, I>, PagingAndSortingRepository<T, I>, JpaSpecificationExecutor<T> {
  @Transactional
  <S extends T> S statefulSave(S entity);
}
