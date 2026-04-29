package com.gersimuca.erp.feature.country;

import com.gersimuca.erp.common.repository.BaseRepository;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
interface CountryRepository extends BaseRepository<CountryEntity, Long> {
  List<CountryEntity> findAllByCountryCodeIn(final Collection<String> codes);
}
