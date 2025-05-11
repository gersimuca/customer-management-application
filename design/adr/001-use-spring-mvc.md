# 001-use-spring-mvc

## Context
Our `erp-customer-manager` SpringBoot application needs a proven, synchronous web framework for handling HTTP requests, rich REST support, and MVC abstractions. We require a mature ecosystem with extensive documentation and community adoption.

## Decision
Use **Spring MVC** (via `spring-boot-starter-web`) as our web framework, running on an embedded Tomcat server.

## Consequences
- **Positive**: Well-known programming model; synchronous request handling simplifies debugging; broad third-party support.
- **Negative**: Thread-per-request model may limit scalability under extremely high concurrent load compared to reactive frameworks.

## Alternatives Considered
- **Spring WebFlux**: Reactive and non-blocking, but adds complexity and steeper learning curve.
- **Vert.x**: High-performance toolkit, but diverges from Spring conventions.