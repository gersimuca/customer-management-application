package com.gersimuca.erp.architecture.archunit;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.lang.annotation.Annotation;
import java.util.List;

public class Predicates {

  public static DescribedPredicate<JavaMethod> overrideMethodAnnotatedWith(
      Class<? extends Annotation> annotationClass) {
    return new DescribedPredicate<>(
        String.format("override method annotated with @%s", annotationClass.getSimpleName())) {
      @Override
      public boolean test(JavaMethod javaMethod) {
        return javaMethod.getOwner().getRawInterfaces().stream()
            .flatMap(
                c ->
                    c.getMethods().stream()
                        .filter(
                            m ->
                                m.isAnnotatedWith(annotationClass)
                                    && javaMethod.getName().equals(m.getName())))
            .findFirst()
            .isPresent();
      }
    };
  }

  public static DescribedPredicate<JavaClass> hasFeatureAwareName(String suffix) {
    return new DescribedPredicate<>(
        String.format("has feature aware name with suffix '%s'", suffix)) {
      @Override
      public boolean test(JavaClass javaClass) {
        return javaClass
            .getSimpleName()
            .equalsIgnoreCase(
                String.format("%s%s", javaClass.getPackage().getRelativeName(), suffix));
      }
    };
  }

  public static DescribedPredicate<JavaClass> areNotAnnotatedWithAny(
      List<Class<? extends Annotation>> annotationClasses) {
    return new DescribedPredicate<>(
        String.format(
            "are not annotated with any of '%s'",
            String.join(
                ", ",
                annotationClasses.stream()
                    .map(a -> String.format("@%s", a.getSimpleName()))
                    .toList()))) {
      @Override
      public boolean test(JavaClass javaClass) {
        return annotationClasses.stream().noneMatch(javaClass::isAnnotatedWith);
      }
    };
  }

  public static DescribedPredicate<JavaClass> areAnnotatedWithAny(
      List<Class<? extends Annotation>> annotationClasses) {
    return new DescribedPredicate<>(
        String.format(
            "are annotated with any of '%s'",
            String.join(
                ", ",
                annotationClasses.stream()
                    .map(a -> String.format("@%s", a.getSimpleName()))
                    .toList()))) {
      @Override
      public boolean test(JavaClass javaClass) {
        return annotationClasses.stream().anyMatch(javaClass::isAnnotatedWith);
      }
    };
  }

  public static DescribedPredicate<JavaMethod> hasOperationWithSecurity() {
    return new DescribedPredicate<>("has security not null") {
      @Override
      public boolean test(JavaMethod javaMethod) {
        return javaMethod.getOwner().getRawInterfaces().stream()
            .flatMap(c -> c.getMethods().stream())
            .filter(
                m -> m.isAnnotatedWith(Operation.class) && javaMethod.getName().equals(m.getName()))
            .anyMatch(
                m ->
                    m.getAnnotations().stream()
                        .filter(
                            annotation -> annotation.getRawType().isAssignableTo(Operation.class))
                        .anyMatch(
                            annotation -> {
                              Object security = annotation.getProperties().get("security");
                              if (security instanceof SecurityRequirement[] securityRequirements) {
                                return securityRequirements.length > 0;
                              }
                              return false;
                            }));
      }
    };
  }
}
