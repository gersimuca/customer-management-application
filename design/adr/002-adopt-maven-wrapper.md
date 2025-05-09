# 002-adopt-maven-wrapper

## Context
Onboarding new developers and ensuring consistent build behavior requires a uniform Maven version without manual installs.

## Decision
Integrate **Maven Wrapper** (`mvnw`, `mvnw.cmd`, `.mvn/wrapper/`) so everyone builds with the same Maven distribution.

## Consequences
- **Positive**: Eliminates "works on my machine" build issues; simplifies setup.
- **Negative**: Slight repo size increase due to wrapper files.

## Alternatives Considered
- **Document required Maven version**: Relies on each developer to install correctly.
- **Use Docker for builds**: More isolation but adds complexity for local builds.
