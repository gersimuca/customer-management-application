package com.gersimuca.erp.architecture.archunit;

import static lombok.AccessLevel.PRIVATE;

import com.tngtech.archunit.core.domain.JavaAnnotation;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import java.lang.annotation.Annotation;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class Conditions {

  public static ArchCondition<JavaClass> notBeReferenced() {
    return new ArchCondition<>("not be referenced") {
      @Override
      public void check(JavaClass javaClass, ConditionEvents conditionEvents) {
        boolean conditionSatisfied = javaClass.getDirectDependenciesToSelf().isEmpty();
        conditionEvents.add(
            new SimpleConditionEvent(
                javaClass,
                conditionSatisfied,
                String.format(
                    "'%s' is referenced by %s'",
                    javaClass.getFullName(),
                    javaClass.getDirectDependenciesToSelf().stream()
                        .map(d -> d.getOriginClass().getFullName())
                        .distinct()
                        .collect(Collectors.joining(",")))));
      }
    };
  }

  public static ArchCondition<JavaClass> implementInterfaceAnnotatedWith(
      Class<? extends Annotation> annotationClass,
      Function<JavaAnnotation<JavaClass>, Boolean> annotationMatcher) {
    return new ArchCondition<>(
        String.format("implement interface annotated with @%s", annotationClass.getSimpleName())) {
      @Override
      public void check(JavaClass javaClass, ConditionEvents conditionEvents) {
        boolean conditionSatisfied =
            javaClass.getRawInterfaces().stream()
                .flatMap(i -> i.getAnnotations().stream())
                .filter(a -> a.getRawType().reflect() == annotationClass)
                .anyMatch(annotationMatcher::apply);
        conditionEvents.add(
            new SimpleConditionEvent(
                javaClass,
                conditionSatisfied,
                String.format(
                    "'%s' does not implement interface annotated with %s",
                    javaClass.getFullName(), annotationClass.getSimpleName())));
      }
    };
  }

  public static ArchCondition<JavaMethod> notBeCalledFromMethodsInSameClass(
      Class<? extends Annotation> annotation) {
    return new ArchCondition<>("not be called from methods in the same class") {
      @Override
      public void check(JavaMethod javaMethod, ConditionEvents conditionEvents) {
        javaMethod
            .getCallsOfSelf()
            .forEach(
                call -> {
                  if (call.getOrigin().getOwner().equals(javaMethod.getOwner())) {
                    String message =
                        String.format(
                            "Method %s annotated with @%s is called by method %s in same class.",
                            javaMethod.getFullName(),
                            annotation.getSimpleName(),
                            call.getOrigin().getFullName());
                    conditionEvents.add(SimpleConditionEvent.violated(call, message));
                  }
                });
      }
    };
  }
}
