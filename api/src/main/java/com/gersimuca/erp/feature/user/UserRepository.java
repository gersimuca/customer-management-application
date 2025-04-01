package com.gersimuca.erp.feature.user;

import com.gersimuca.erp.common.repository.BaseRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmailIgnoreCase(String email);

  //  // Query using JPQL with index params
  //  @Query("SELECT c FROM UserEntity  c WHERE c.givenName = ?1 AND c.familyName = ?2")
  //  UserEntity findByFirstNameAndLastNameIndexParams(String givenName, String familyName);
  //
  //  // Query using JPQL with named params
  //  @Query("SELECT c FROM UserEntity  c WHERE c.givenName = :firstName AND c.familyName =
  // :familyName")
  //  UserEntity findByFirstnameAndLastnameNamedParams(
  //      @Param("givenName") String givenName, @Param("lastName") String familyName);
  //
  //  // Query using Native SQL with index params
  //  @Query(
  //      value = "SELECT c FROM UserEntity  c WHERE c.givenName = ?1 AND c.familyName = ?2",
  //      nativeQuery = true)
  //  UserEntity findByFirstNameAndLastNameNativeQueryIndexParams(String givenName, String
  // familyName);
  //
  //  // Query using Native SQL with index params
  //  @Query(
  //      value =
  //          "SELECT c FROM UserEntity  c WHERE c.givenName = :givenName AND c.familyName =
  // :familyName",
  //      nativeQuery = true)
  //  UserEntity findByFirstNameAndLastNameNativeQueryNamedParams(
  //      @Param("givenName") String givenName, @Param("familyName") String familyName);

  // Application Core
  boolean existsByUsername(String username);

  Optional<UserEntity> findByUsername(String username);
}
