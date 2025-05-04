package com.gersimuca.erp.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.gersimuca.erp.architecture.archunit.Conditions;
import com.gersimuca.erp.architecture.archunit.FeatureAwareArchRuleEvaluator;
import com.gersimuca.erp.architecture.archunit.Predicates;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

class AnnotationTest {

  @Test
  void rest_controller_should_implement_interface_annotated_with_tag() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePath())
                    .and()
                    .areAnnotatedWith(RestController.class)
                    .should(
                        Conditions.implementInterfaceAnnotatedWith(
                            Tag.class,
                            p -> {
                              String PROPERTY_NAME = "name";
                              boolean propertyExists =
                                  p.hasExplicitlyDeclaredProperty(PROPERTY_NAME);
                              return propertyExists
                                  && String.valueOf(p.getExplicitlyDeclaredProperty(PROPERTY_NAME))
                                      .equalsIgnoreCase(context.getFeatureName());
                            })));
  }

  @Test
  void
      method_that_overrides_method_annotated_with_operation_should_be_annotated_with_pre_authorize() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                methods()
                    .that()
                    .areDeclaredInClassesThat()
                    .resideInAPackage(context.getFeaturePackagePath())
                    .and()
                    .areDeclaredInClassesThat()
                    .areAnnotatedWith(RestController.class)
                    .and(Predicates.overrideMethodAnnotatedWith(Operation.class))
                    .and(Predicates.hasOperationWithSecurity())
                    .should()
                    .beAnnotatedWith(PreAuthorize.class));
  }

  //  @Test
  //  void methods_that_are_annotated_with_async_should_not_be_called_by_same_class() {
  //    methods()
  //        .that()
  //        .areAnnotatedWith(Async.class)
  //        .should(Conditions.notBeCalledFromMethodsInSameClass(Async.class))
  //        .check(JavaClassesImporter.get());
  //  }

  //  @Test
  //  void methods_that_are_annotated_with_transactional_should_not_be_called_by_same_class() {
  //    methods()
  //        .that()
  //        .areAnnotatedWith(Transactional.class)
  //        .should(Conditions.notBeCalledFromMethodsInSameClass(Transactional.class))
  //        .check(JavaClassesImporter.get());
  //  }
}
