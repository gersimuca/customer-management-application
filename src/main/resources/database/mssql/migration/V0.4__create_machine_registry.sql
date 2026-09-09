CREATE TABLE machine_registry
(
    machine_id      INT PRIMARY KEY,
    instance_id     VARCHAR(255) NOT NULL UNIQUE,
    service_name    VARCHAR(100) NOT NULL,
    allocated_at    DATETIMEOFFSET NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_heartbeat  DATETIMEOFFSET NULL
);