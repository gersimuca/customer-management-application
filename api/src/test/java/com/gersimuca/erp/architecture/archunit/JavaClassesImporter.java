package com.gersimuca.erp.architecture.archunit;

import static lombok.AccessLevel.PRIVATE;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class JavaClassesImporter {
  public static JavaClasses get() {
    return get("com.gersimuca");
  }

  public static JavaClasses get(String... packages) {
    return new ClassFileImporter()
        .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_ARCHIVES)
        .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
        .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
        .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TEST_FIXTURES)
        .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_PACKAGE_INFOS)
        .importPackages(packages);
  }
}
