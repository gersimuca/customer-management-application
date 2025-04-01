package com.gersimuca.erp.common.repository;

import jakarta.persistence.EntityManager;
import java.io.Serializable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public class BaseRepositoryImpl<T, I extends Serializable> extends SimpleJpaRepository<T, I>
    implements BaseRepository<T, I> {
  private final EntityManager entityManager;

  public BaseRepositoryImpl(
      JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.entityManager = entityManager;
  }

  @Transactional
  @Override
  public <S extends T> S statefulSave(final S entity) {
    final S savedEntity = super.save(entity);
    entityManager.refresh(savedEntity);
    return savedEntity;
  }
}
