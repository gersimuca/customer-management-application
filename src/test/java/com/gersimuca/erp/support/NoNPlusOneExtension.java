package com.gersimuca.erp.support;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.extension.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class NoNPlusOneExtension implements BeforeEachCallback, AfterEachCallback {

  private Statistics statistics;

  @Override
  public void beforeEach(ExtensionContext context) {
    EntityManagerFactory emf =
        SpringExtension.getApplicationContext(context).getBean(EntityManagerFactory.class);

    statistics = emf.unwrap(SessionFactory.class).getStatistics();
    statistics.clear();
  }

  @Override
  public void afterEach(ExtensionContext context) {
    NoNPlusOne rule =
        context
            .getElement()
            .map(el -> el.getAnnotation(NoNPlusOne.class))
            .orElse(context.getRequiredTestClass().getAnnotation(NoNPlusOne.class));

    if (rule == null) {
      return;
    }

    long selects = statistics.getQueryExecutionCount();
    long total = statistics.getPrepareStatementCount();

    if (selects > rule.selects()) {
      throw new AssertionError(
          "N+1 detected: expected at most " + rule.selects() + " SELECTs, but got " + selects);
    }

    if (total > rule.total()) {
      throw new AssertionError(
          "Too many SQL statements: expected at most " + rule.total() + ", but got " + total);
    }

    statistics.clear();
  }
}
