package com.gersimuca.erp.feature.country;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gersimuca.erp.api.ApiFacade;
import com.gersimuca.model.external.nagerdate.CountryV3Dto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

  @Mock private CountryRepository repository;

  @Mock private ApiFacade apiFacade;

  private final CountryMapper mapper = Mappers.getMapper(CountryMapper.class);

  @InjectMocks private CountryServiceImpl service;

  @BeforeEach
  void setUp() {
    service = new CountryServiceImpl(repository, mapper, apiFacade);
  }

  @Test
  void shouldFindAllCountries() {
    List<CountryEntity> entities =
        List.of(CountryTestData.ALBANIA_ENTITY, CountryTestData.ITALY_ENTITY);

    when(repository.findAll()).thenReturn(entities);

    List<CountryDto> result = service.findAll();

    assertThat(result).isNotNull().hasSize(2);

    assertThat(result.get(0).getCountryName()).isEqualTo("Albania");
    assertThat(result.get(0).getCountryCode()).isEqualTo("AL");

    assertThat(result.get(1).getCountryName()).isEqualTo("Italy");
    assertThat(result.get(1).getCountryCode()).isEqualTo("IT");

    verify(repository).findAll();
  }

  @Test
  void shouldRegisterNewCountries() {
    CountryV3Dto albania = new CountryV3Dto("AL", "Albania");

    CountryV3Dto italy = new CountryV3Dto("IT", "Italy");

    when(apiFacade.countryAvailableCountries()).thenReturn(List.of(albania, italy));

    when(repository.findAllByCountryCodeIn(anyCollection())).thenReturn(List.of());

    when(repository.saveAll(anyCollection()))
        .thenAnswer(invocation -> List.copyOf(invocation.getArgument(0)));

    List<CountryDto> result = service.registerCountry();

    assertThat(result).isNotNull().hasSize(2);

    assertThat(result.get(0).getCountryName()).isEqualTo("Albania");
    assertThat(result.get(0).getCountryCode()).isEqualTo("AL");

    assertThat(result.get(1).getCountryName()).isEqualTo("Italy");
    assertThat(result.get(1).getCountryCode()).isEqualTo("IT");

    verify(apiFacade).countryAvailableCountries();
    verify(repository).findAllByCountryCodeIn(anyCollection());
    verify(repository).saveAll(anyCollection());
  }

  @Test
  void shouldUpdateExistingCountries() {
    CountryV3Dto updatedAlbania = new CountryV3Dto("AL", "Albania Updated");

    CountryEntity existingEntity =
        CountryEntity.builder().countryId(1L).countryName("Albania").countryCode("AL").build();

    when(apiFacade.countryAvailableCountries()).thenReturn(List.of(updatedAlbania));

    when(repository.findAllByCountryCodeIn(anyCollection())).thenReturn(List.of(existingEntity));

    when(repository.saveAll(anyCollection()))
        .thenAnswer(invocation -> List.copyOf(invocation.getArgument(0)));

    List<CountryDto> result = service.registerCountry();

    assertThat(result).isNotNull().hasSize(1);

    assertThat(result.getFirst().getCountryName()).isEqualTo("Albania Updated");

    assertThat(result.getFirst().getCountryCode()).isEqualTo("AL");

    verify(apiFacade).countryAvailableCountries();
    verify(repository).findAllByCountryCodeIn(anyCollection());
    verify(repository).saveAll(anyCollection());
  }
}
