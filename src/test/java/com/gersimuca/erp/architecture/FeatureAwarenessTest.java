package com.gersimuca.erp.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.gersimuca.erp.architecture.archunit.FeatureAwareArchRuleEvaluator;
import com.gersimuca.erp.architecture.archunit.Predicates;
import com.gersimuca.erp.common.annotation.DataTransferObject;
import com.tngtech.archunit.base.DescribedPredicate;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

class FeatureAwarenessTest {

  @Test
  void
      feature_package_should_contain_exactly_one_class_annotated_with_rest_controller_ending_with_controller() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePath())
                    .and()
                    .areAnnotatedWith(RestController.class)
                    .should()
                    .containNumberOfElements(DescribedPredicate.equalTo(1))
                    .andShould()
                    .haveSimpleNameEndingWith("Controller"));
  }

  @Test
  void feature_package_should_contain_exactly_one_rest_controller_with_feature_aware_name() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePath())
                    .and()
                    .areAnnotatedWith(RestController.class)
                    .and(Predicates.hasFeatureAwareName("Controller"))
                    .should()
                    .containNumberOfElements(DescribedPredicate.equalTo(1)));
  }

  @Test
  void
      feature_package_should_contain_at_least_one_class_annotated_with_service_ending_with_service() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePath())
                    .and()
                    .areAnnotatedWith(Service.class)
                    .should()
                    .containNumberOfElements(DescribedPredicate.greaterThanOrEqualTo(1))
                    .andShould()
                    .haveSimpleNameEndingWith("Service"));
  }

  @Test
  void feature_package_should_contain_exactly_one_service_with_feature_aware_name() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePath())
                    .and()
                    .areAnnotatedWith(Service.class)
                    .and(Predicates.hasFeatureAwareName("Service"))
                    .should()
                    .containNumberOfElements(DescribedPredicate.equalTo(1)));
  }

  @Test
  void
      feature_package_should_contain_at_least_one_class_annotated_with_repository_ending_with_repository() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePath())
                    .and()
                    .areAnnotatedWith(Repository.class)
                    .should()
                    .containNumberOfElements(DescribedPredicate.greaterThanOrEqualTo(1))
                    .andShould()
                    .haveSimpleNameEndingWith("Repository"));
  }

  @Test
  void feature_package_should_contain_exactly_one_repository_with_feature_aware_name() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePath())
                    .and()
                    .areAnnotatedWith(Repository.class)
                    .and(Predicates.hasFeatureAwareName("Repository"))
                    .should()
                    .containNumberOfElements(DescribedPredicate.equalTo(1)));
  }

  @Test
  void
      feature_package_should_contain_at_least_one_class_annotated_with_data_transfer_object_ending_with_dto() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePath())
                    .and()
                    .areAnnotatedWith(DataTransferObject.class)
                    .should()
                    .containNumberOfElements(DescribedPredicate.greaterThanOrEqualTo(1))
                    .andShould()
                    .haveSimpleNameEndingWith("Dto"));
  }

  @Test
  void feature_package_should_contain_exactly_one_data_transfer_object_with_feature_aware_name() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePath())
                    .and()
                    .areAnnotatedWith(DataTransferObject.class)
                    .and(Predicates.hasFeatureAwareName("Dto"))
                    .should()
                    .containNumberOfElements(DescribedPredicate.equalTo(1)));
  }

  @Test
  void feature_package_should_contain_entities_with_name_ending_with_entity_if_present() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePath())
                    .and()
                    .areAnnotatedWith(Entity.class)
                    .should()
                    .haveSimpleNameEndingWith("Entity"));
  }

  @Test
  void feature_package_should_contain_at_most_one_entity_with_feature_aware_name() {
    FeatureAwareArchRuleEvaluator.instance()
        .evaluate(
            context ->
                classes()
                    .that()
                    .resideInAPackage(context.getFeaturePackagePath())
                    .and()
                    .areAnnotatedWith(Entity.class)
                    .and(Predicates.hasFeatureAwareName("Entity"))
                    .should()
                    .containNumberOfElements(DescribedPredicate.lessThanOrEqualTo(1)));
  }
}
