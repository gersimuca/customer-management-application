# 007-use-java-25

## Context
With the release of Java 25 as a Long-Term Support (LTS) version, we have an opportunity to benefit from continued performance improvements, enhanced language features, and long-term stability. The upgrade ensures our codebase remains compatible with the latest ecosystem developments and supported tools.

## Decision
Standardize on **Java 25 (LTS)** across the project. All builds, tests, and runtime environments will use JDK 25 as the default Java version.

## Consequences
- **Positive**: Improved performance, security, and access to the latest JVM and language enhancements; long-term support ensures forward stability.
- **Negative**: Some third-party dependencies and Maven plugins may require updates to ensure compatibility; temporary build adjustments may be needed.

## Alternatives Considered
- **Continue using Java 21 (LTS)**: Stable and fully supported but lacks access to the latest performance improvements and language features.
- **Use non-LTS versions**: Provides faster access to new features but increases maintenance overhead due to shorter support cycles.

## Future Considerations
Monitor library and framework compatibility with Java 25 and plan for incremental adoption of future JVM improvements. Reevaluate this decision when the next LTS release becomes available.
