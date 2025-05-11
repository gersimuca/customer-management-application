# 003-use-java-21

## Context
Our codebase targets modern Java features (record patterns, pattern matching, virtual threads) and we need an LTS release for stability.

## Decision
Standardize on **Java21** in the `pom.xml` (`<java.version>21</java.version>`) and run all builds/tests on JDK21.

## Consequences
- **Positive**: LTS support; access to newest language and JVM features; performance and security improvements.
- **Negative**: Some libraries may lag in compatibility; team learning curve for new features.

## Alternatives Considered
- **Java17 (LTS)**: Proven support but lacks latest features.
- **Java19/20 (non-LTS)**: Preview features but requires frequent upgrades.
