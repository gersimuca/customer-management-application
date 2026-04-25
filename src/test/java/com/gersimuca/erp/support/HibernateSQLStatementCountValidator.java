package com.gersimuca.erp.support;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

public final class HibernateSQLStatementCountValidator {

  private HibernateSQLStatementCountValidator() {}

  private static Statistics stats(EntityManagerFactory emf) {
    return emf.unwrap(SessionFactory.class).getStatistics();
  }

  public static void reset(EntityManagerFactory emf) {
    stats(emf).clear();
  }

  public static void assertSelectCount(EntityManagerFactory emf, long expected) {

    long actual = stats(emf).getQueryExecutionCount();

    if (actual != expected) {
      throw new AssertionError("Expected " + expected + " SELECT queries, but got " + actual);
    }
  }

  public static void assertTotalCount(EntityManagerFactory emf, long expected) {

    long actual = stats(emf).getPrepareStatementCount();

    if (actual != expected) {
      throw new AssertionError("Expected " + expected + " SQL statements, but got " + actual);
    }
  }
}
