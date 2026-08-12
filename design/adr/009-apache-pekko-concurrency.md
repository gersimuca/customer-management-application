# 009-apache-pekko-concurrency

## Context

The system requires a scalable concurrency and messaging model to support:

- High-throughput asynchronous processing
- Fault-tolerant distributed workflows
- Event-driven architecture components
- Background processing and task isolation
- Low-latency internal communication between services/modules

As the system evolves toward distributed and cloud workloads (including Kubernetes-based scaling), a traditional thread-per-request or executor-based model is not sufficient for long-running, concurrent, and resilient workflows.

We evaluated actor-based concurrency as a solution for managing stateful and asynchronous workloads safely.

## Decision

We will adopt **Apache Pekko** as the actor-based concurrency and messaging framework for internal asynchronous processing.

Pekko will be used for:

- Internal event-driven workflows
- Background job processing
- Isolated stateful components using actors
- Fault-tolerant message processing pipelines
- Optional clustering capabilities for future distributed scaling

We will use:

- `pekko-actor-typed` for type-safe actor definitions
- `pekko-slf4j` for structured logging integration

Apache Pekko provides:

- Lightweight actor model for concurrency isolation
- Supervision strategies for fault tolerance
- Location transparency (future-ready for clustering)
- Strong alignment with reactive systems principles
- Mature fork of the Akka open-source model with stable governance
- Good integration with JVM-based Spring Boot applications

The typed actor API improves safety and maintainability compared to untyped messaging systems.

## Consequences

### Positive

- Improved concurrency model with isolated state per actor
- Reduced risk of shared-state concurrency bugs
- Built-in fault tolerance via supervision strategies
- Better scalability for asynchronous workloads
- Clear separation of concerns for background processing
- Future-ready for distributed clustering and multi-node execution

### Negative

- Increased architectural complexity compared to standard Spring executors
- Requires developer familiarity with actor model concepts
- Debugging message flows can be less intuitive than synchronous code
- Additional operational overhead if clustering is introduced later
- Learning curve for teams unfamiliar with reactive systems

## Alternatives Considered

### 1. Spring @Async / TaskExecutor

- **Rejected because:**
    - Limited supervision and fault tolerance model
    - Weak support for complex stateful workflows
    - Harder to reason about concurrency boundaries at scale

### 2. Plain Java Executors / Virtual Threads

- **Rejected because:**
    - Lack of message-passing model
    - Shared-state risks increase with complexity
    - No built-in supervision or failure isolation semantics

### 3. Reactive Streams (Project Reactor only)

- **Rejected because:**
    - Good for pipelines, but weaker for stateful actor-like isolation
    - Complex state management becomes harder at scale

### 4. External queue-based processing (Kafka/RabbitMQ only)

- **Rejected because:**
    - Strong for integration, but introduces unnecessary network overhead for internal workflows
    - Does not replace in-process concurrency needs

## Future Considerations

  - Evaluate Pekko Clustering if system expands into multi-node actor systems
  - Introduce actor-based domain isolation for high-complexity business modules
  - Consider integration with event sourcing patterns if domain complexity increases
  - Monitor performance overhead vs Spring-native concurrency models
  - Potential migration of select background services from executor-based to actor-based execution