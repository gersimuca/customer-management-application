package com.gersimuca.erp.feature.country;

import com.gersimuca.erp.api.ApiFacade;
import com.gersimuca.model.external.nagerdate.CountryV3Dto;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

  private final CountryRepository repository;
  private final CountryMapper mapper;
  private final ApiFacade apiFacade;

  @Override
  public List<CountryDto> findAll() {
    final List<CountryEntity> entities = repository.findAll();
    return mapper.entityToDto(entities).stream().toList();
  }

  @Override
  @Scheduled(cron = "0 0 0 1 * ?")
  public List<CountryDto> registerCountry() {
    final List<CountryV3Dto> countryV3Dtos = apiFacade.countryAvailableCountries();
    final List<CountryDto> countryDtos = mapper.externalToDto(countryV3Dtos);
    final Collection<String> codes = countryDtos.stream().map(CountryDto::getCountryCode).toList();
    final Map<String, CountryEntity> existingCountries =
        new HashMap<>(
            repository.findAllByCountryCodeIn(codes).stream()
                .collect(
                    Collectors.toUnmodifiableMap(
                        CountryEntity::getCountryCode, Function.identity())));
    Collection<CountryEntity> entities = new LinkedList<>();
    countryDtos.forEach(
        dto -> {
          final CountryEntity entity =
              existingCountries.compute(
                  dto.getCountryCode(),
                  (_, existing) -> {
                    if (existing == null) {
                      return mapper.toEntity(dto);
                    }
                    mapper.updateEntity(dto, existing);
                    return existing;
                  });
          entities.add(entity);
        });
    final List<CountryEntity> saved = repository.saveAll(entities);
    return mapper.entityToDto(saved);
  }
}
