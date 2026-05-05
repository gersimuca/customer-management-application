package com.gersimuca.erp.feature.machine_registry;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "machine_registry")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class MachineRegistryEntity {
  @Id
  @Column(name = "machine_id", nullable = false)
  private Integer machineId;

  @Column(name = "instance_id", nullable = false, unique = true)
  private String instanceId;

  @Column(name = "service_name", nullable = false, length = 100)
  private String serviceName;

  @Column(name = "allocated_at", nullable = false, updatable = false)
  private OffsetDateTime allocatedAt;

  @Column(name = "last_heartbeat")
  private OffsetDateTime lastHeartbeat;
}
