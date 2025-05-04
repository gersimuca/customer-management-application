package com.gersimuca.erp.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.gersimuca.erp.architecture.archunit.JavaClassesImporter;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

class ApiTest {

  @Test
  void api_facade_should_only_be_used_by_service_that_reside_in_a_feature() {
    classes()
        .that()
        .haveSimpleName("ApiFacade")
        .should()
        .onlyHaveDependentClassesThat()
        .resideInAPackage("com.gersimuca.erp.feature..")
        .andShould()
        .onlyHaveDependentClassesThat()
        .areAnnotatedWith(Service.class)
        .check(JavaClassesImporter.get("com.gersimuca.erp.api", "com.gersimuca.erp.feature.."));
  }

  //  @Test
  //  void external_api_interface_should_only_be_used_by_class_inside_api_package() {
  //    classes()
  //        .that()
  //        .resideInAnyPackage("com.gersimuca.erp.api.external..")
  //        .and()
  //        .areAnnotatedWith(FeignClient.class)
  //        .should()
  //        .onlyHaveDependentClassesThat()
  //        .resideInAPackage("com.gersimuca.erp.api..")
  //        .check(JavaClassesImporter.get("com.gersimuca.erp.api..",
  // "com.gersimuca.erp.feature.."));
  //  }
}
