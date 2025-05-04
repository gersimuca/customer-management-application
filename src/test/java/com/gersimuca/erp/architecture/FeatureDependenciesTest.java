package com.gersimuca.erp.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.gersimuca.erp.architecture.archunit.Conditions;
import com.gersimuca.erp.architecture.archunit.FeatureAwareArchRuleEvaluator;
import com.gersimuca.erp.architecture.archunit.Predicates;
import com.gersimuca.erp.common.annotation.DataTransferObject;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

class FeatureDependenciesTest {

  public static final String API_MODEL_PACKAGE = "de.dlh.lht.engdci.model";

  @Test
  void rest_controller_should_not_be_referenced() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePathWithSubPackages())
                    .and()
                    .areAnnotatedWith(RestController.class)
                    .should(Conditions.notBeReferenced()));
  }

  @Test
  void service_should_not_be_referenced_by_repository() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePathWithSubPackages())
                    .and()
                    .areAnnotatedWith(Service.class)
                    .should()
                    .onlyHaveDependentClassesThat(
                        Predicates.areNotAnnotatedWithAny(List.of(Repository.class))));
  }

  @Test
  void repository_should_only_be_referenced_by_service_or_repository() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePathWithSubPackages())
                    .and()
                    .areAnnotatedWith(Repository.class)
                    .should()
                    .onlyHaveDependentClassesThat(
                        Predicates.areAnnotatedWithAny(List.of(Service.class, Repository.class))));
  }

  @Test
  void rest_controller_should_not_use_an_entity() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePathWithSubPackages())
                    .and()
                    .areAnnotatedWith(RestController.class)
                    .should()
                    .onlyDependOnClassesThat()
                    .areNotAnnotatedWith(Entity.class));
  }

  @Test
  void service_should_not_use_an_api_model() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePathWithSubPackages())
                    .and()
                    .areAnnotatedWith(Service.class)
                    .should()
                    .onlyDependOnClassesThat()
                    .resideOutsideOfPackage(API_MODEL_PACKAGE));
  }

  @Test
  void repository_should_neither_use_an_api_model_nor_a_data_transfer_object() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePathWithSubPackages())
                    .and()
                    .areAnnotatedWith(Repository.class)
                    .should()
                    .onlyDependOnClassesThat()
                    .resideOutsideOfPackage(API_MODEL_PACKAGE)
                    .andShould()
                    .onlyDependOnClassesThat()
                    .areNotAnnotatedWith(DataTransferObject.class));
  }

  @Test
  void entity_manager_should_only_be_accessed_by_a_repository() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePathWithSubPackages())
                    .and()
                    .areAssignableTo(EntityManager.class)
                    .should()
                    .beAnnotatedWith(Repository.class));
  }
}
