package com.gersimuca.erp.feature.machine_registry;

// import com.gersimuca.erp.common.repository.BaseRepository;
// import java.time.Instant;
// import org.springframework.data.jpa.repository.*;
//
// public interface MachineRegistryRepository extends BaseRepository<MachineRegistryEntity, Integer>
// {
//
//  @Modifying
//  @Query(
//      """
//      UPDATE MachineRegistryEntity
//      SET lastHeartbeat = :heartbeat
//      WHERE machineId = :machineId
//      """)
//  void updateHeartbeat(Integer machineId, Instant heartbeat);
//
//  @Modifying
//  @Query(
//      """
//      DELETE
//      FROM MachineRegistryEntity
//      WHERE lastHeartbeat < :cutoff
//      """)
//  void cleanup(Instant cutoff);
// }
