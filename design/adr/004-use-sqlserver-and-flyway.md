# 004-use-sqlserver-and-flyway

## Context
Our SpringBoot application requires a robust, ACID-compliant relational database with enterprise features and Azure integration, and we need repeatable schema migrations integrated into Maven CI/CD.

## Decision
Adopt **Microsoft SQLServer** as the primary database (using `mssql-jdbc`) and manage schema changes via **Flyway** Maven plugin (`flyway-sqlserver`).

## Consequences
- **Positive**: Enterprise-grade features (AlwaysOn, analytics, Azure parity); convention-based migrations with Flyway; strong JDBC driver support.
- **Negative**: Licensing costs; potential platform lock-in; team ramp-up on Flyway conventions.

## Alternatives Considered
- **PostgreSQL + Flyway**: Open-source and free, but lacks native Azure PaaS features.
- **MySQL + Flyway**: Ubiquitous but fewer advanced SQL features.
- **Liquibase + SQLServer**: More flexible but heavier configuration compared to Flyway.
