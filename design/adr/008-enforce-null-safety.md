# 008-enforce-null-safety

## Context

Null pointer exceptions are one of the most common runtime failures in Java applications. In a large Spring Boot system with layered architecture (controllers, services, repositories, mappers), null propagation is often implicit and difficult to track.

To improve reliability and reduce runtime `NullPointerException`s, we introduce compile-time null safety enforcement.

We already use:
  * Lombok
  * MapStruct
  * Spring Boot 3+
  * Java 25

However, none of these enforce strict null-safety guarantees by default.

We need a static analysis mechanism that prevents null-related defects at compile time.

## Decision

We will enforce null safety at compile time using:

### Error Prone Compiler Plugin

Google Error Prone is enabled via the Maven Compiler Plugin to provide static analysis during compilation.

### NullAway Annotation Processor

We integrate:

* `com.uber.nullaway:nullaway`
* configured with:
    * `-Xep:NullAway:ERROR`
    * `-XepOpt:NullAway:OnlyNullMarked`

This enforces strict null-checking rules only on explicitly annotated code.

### Opt-in Null Safety Model

We adopt an opt-in null safety strategy:

* Only code annotated with `@NullMarked` is strictly checked
* Legacy or external code remains unaffected unless explicitly marked
* Reduces migration risk in large codebases

### Build-time Enforcement

Null safety violations will:

* Fail the build
* Prevent unsafe code from reaching production
* Be enforced in CI and local builds

### Tooling Integration

NullAway is integrated alongside:

* MapStruct (DTO mapping safety)
* Lombok (boilerplate reduction)
* Hibernate (entity lifecycle)
* Spring Boot dependency injection

Compiler configuration ensures compatibility with:
* Java 25 module system
* JDK internal APIs via `--add-exports` and `--add-opens`

## Consequences

### Positive

* Eliminates runtime `NullPointerException`s
* Enforces safer API contracts at compile time
* Improves code quality across service and mapper layers
* Makes nullability explicit and self-documenting
* Works in CI and local builds
* Encourages defensive API design

### Negative

* Requires developer discipline (`@Nullable`, `@NonNull`)
* Initial migration effort for existing codebase
* Framework integration complexity (Spring, JPA, MapStruct)
* Possible false positives during early adoption
* Slight increase in compilation complexity

## Alternatives Considered

### Runtime Null Checks Only

Rejected because:
* Errors appear in production
* No compile-time guarantees
* Hard to trace root cause

### Kotlin for Null Safety

Rejected because:
* Requires language migration
* Adds complexity to existing Java ecosystem
* Increases onboarding overhead

### Manual Code Reviews

Rejected because:
* Not enforceable at scale
* Human error prone
* No consistency guarantees

### Checker Framework

Rejected because:
* Heavier annotation model
* More complex integration with Spring + Lombok
* Less aligned with existing build tooling

## Future Considerations

* Introduce `@NullMarked` as a project-wide default once migration stabilizes
* Gradually enforce null safety in:
    * Controller layer
    * Service layer
    * DTOs and mappers
* Add CI enforcement rules for critical packages
* Extend strict null checking to external API boundaries
* Evaluate combining NullAway with ArchUnit for architectural enforcement

## References

* NullAway (Uber)
* Error Prone (Google)
* JSpecify / Java nullability standards
* Spring Framework null-safety annotations