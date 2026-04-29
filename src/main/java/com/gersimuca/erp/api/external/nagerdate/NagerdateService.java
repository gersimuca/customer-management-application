package com.gersimuca.erp.api.external.nagerdate;

import com.gersimuca.erp.api.external.ExternalApiService;
import com.gersimuca.erp.api.external.nagerdate.config.NagerdateApiClient;
import com.gersimuca.model.external.nagerdate.CountryInfoWithBordersDto;
import com.gersimuca.model.external.nagerdate.CountryV3Dto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NagerdateService extends ExternalApiService<NagerdateApiClient>
    implements NagerdateApiClient {

  public NagerdateService(final NagerdateApiClient apiClient) {
    super(apiClient);
  }

  @Override
  public ResponseEntity<List<CountryV3Dto>> apiV3AvailableCountriesGet() {
    return execute(apiClient::apiV3AvailableCountriesGet);
  }

  @Override
  public ResponseEntity<CountryInfoWithBordersDto> apiV3CountryInfoCountryCodeGet(
      final String countryCode) {
    return execute(() -> apiClient.apiV3CountryInfoCountryCodeGet(countryCode));
  }
}
