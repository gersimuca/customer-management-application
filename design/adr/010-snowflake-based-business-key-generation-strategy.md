# 010-snowflake-based-business-key-generation-strategy

## Context

The system requires globally unique identifiers for core business entities (e.g., users, contracts, orders) that:

- Are safe to expose via APIs
- Avoid leaking internal database IDs
- Work across distributed deployments (including Kubernetes autoscaling)
- Maintain high performance under high throughput
- Avoid collisions across multiple application instances

Traditional database-generated IDs (IDENTITY) are not suitable for distributed systems and expose internal sequencing information. A custom ID strategy is required.

We adopt a Snowflake-inspired approach combined with a machine allocation registry to ensure uniqueness across multiple instances.

## Decision

We will implement a Snowflake-based Business Key generation system with the following architecture:

### 1. Dual-Identifier Model

Each entity will have:

- `user_id`: internal surrogate primary key (database-only)
- `business_key`: externally exposed Snowflake ID

### 2. Snowflake ID Structure

The generated ID is composed of:

- Timestamp (millisecond precision, epoch-based)
- Machine ID (allocated dynamically from registry)
- Sequence number (per millisecond)

This ensures:

- Time-ordered IDs
- High throughput generation
- Collision-free distributed generation

### 3. Machine Registry

A database-backed machine_registry table is introduced to:

- Allocate unique machine IDs per application instance
- Track active instances via heartbeat
- Reclaim machine IDs from crashed or expired instances
- Support dynamic scaling (e.g., Kubernetes pods)

### 4. Heartbeat Mechanism

Each running instance periodically updates its heartbeat timestamp.

If a machine becomes inactive beyond a defined threshold:

- Its machine ID is considered free
- It can be reassigned to new instances

### 5. Optional Fallback Strategy

A Redis-based atomic counter may be used as a fallback mechanism in rare failure scenarios where:

- Machine registry is unavailable
- DB coordination is temporarily unreachable

## Consequences

- **Positive**: Improved performance, security, and access to the latest JVM and language enhancements; long-term support ensures forward stability.
- **Negative**: Some third-party dependencies and Maven plugins may require updates to ensure compatibility; temporary build adjustments may be needed.
- **Positive**: Globally unique identifiers without coordination bottlenecks
- **Negative**:
    - Increased system complexity (machine registry + heartbeat logic)
    - Additional operational dependency (registry table health required)
    - Requires careful monitoring of machine allocation lifecycle
    - Slight overhead in startup coordination for instance ID assignment
- **Positive**:
    - No dependency on database identity generation for public IDs
    - Safe for distributed and cloud environments
    - Supports horizontal scaling (including Kubernetes autoscaling)
    - Prevents ID enumeration attacks by hiding internal IDs
    - High-throughput ID generation (lock-free hot path)

## Alternatives Considered

### Database Identity (IDENTITY / AUTO_INCREMENT)

- **Rejected because**:
    - Not safe for distributed systems
    - Exposes internal sequencing
    - Does not scale across multiple instances

### UUID (random identifiers)

- **Rejected because**:
    - Poor index performance in relational databases
    - Larger storage footprint
    - No time ordering
    - Harder to debug and trace

### External ID only (businessKey as primary key)

- **Rejected because**:
    - Couples database design to external contract
    - Reduces flexibility in persistence layer
    - Less efficient for joins and indexing
    - Limits future schema evolution

### Central ID service (monolithic generator)

- **Rejected because**:
    - Becomes a bottleneck under high load
    - Introduces single point of failure
    - Requires additional infrastructure scaling

## Future Considerations

- Evaluate migration to a Redis-backed machine allocator for reduced DB contention under extreme scale
- Introduce multi-region machine ID partitioning to support active-active deployments
- Add observability metrics (ID generation rate, sequence saturation, machine allocation health)
- Consider epoch rotation strategy without downtime (future-proofing beyond 2090+ scenarios)
- Investigate lock-free in-memory Snowflake generator improvements for ultra-high throughput services