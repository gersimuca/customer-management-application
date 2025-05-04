package com.gersimuca.erp.architecture.archunit;

import static org.junit.jupiter.api.Assertions.fail;

import com.gersimuca.erp.common.util.LoggerUtils;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.EvaluationResult;
import java.util.function.Function;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeatureAwareArchRuleEvaluator {

  private static FeatureAwareArchRuleEvaluator INSTANCE;

  private final JavaClasses classes;

  private final String PROJECT_ROOT_PACKAGE = "de.dlh.lht.engdci";

  private final String FEATURE_BASE_PACKAGE_PATH =
      String.format("%s.%s", PROJECT_ROOT_PACKAGE, "feature");

  private FeatureAwareArchRuleEvaluator() {
    this.classes = JavaClassesImporter.get(FEATURE_BASE_PACKAGE_PATH);
  }

  public static FeatureAwareArchRuleEvaluator instance() {
    if (INSTANCE == null) {
      INSTANCE = new FeatureAwareArchRuleEvaluator();
    }
    return INSTANCE;
  }

  public void evaluate(Function<FeatureContext, ArchRule> ruleFunction) {

    var featurePackages =
        classes.stream()
            .map(JavaClass::getPackage)
            .distinct()
            .filter(
                p ->
                    p.getParent().stream()
                        .allMatch(r -> r.getName().equalsIgnoreCase(FEATURE_BASE_PACKAGE_PATH)))
            .toList();
    var evaluationResults =
        featurePackages.stream()
            .map(
                p ->
                    ruleFunction
                        .apply(
                            FeatureContext.builder()
                                .featurePackagePath(String.format("%s", p.getName()))
                                .featurePackagePathWithSubPackages(
                                    String.format("%s..", p.getName()))
                                .featureName(p.getRelativeName())
                                .featureBasePackagePath(FEATURE_BASE_PACKAGE_PATH)
                                .build())
                        .evaluate(classes))
            .filter(EvaluationResult::hasViolation)
            .toList();
    if (!evaluationResults.isEmpty()) {
      evaluationResults.forEach(e -> LoggerUtils.error(log, e.getFailureReport().toString()));
      fail();
    }
  }

  @Getter
  @Builder
  public static class FeatureContext {
    private String featureName;

    private String featurePackagePath;

    private String featurePackagePathWithSubPackages;

    private String featureBasePackagePath;
  }
}
