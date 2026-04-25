package com.gersimuca.erp.api.external.nagerdate;

import com.gersimuca.erp.api.external.ExternalApiService;
import com.gersimuca.erp.api.external.nagerdate.config.NagerdateApiClient;
import com.gersimuca.model.external.nagerdate.CountryV3Dto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NagerdateService extends ExternalApiService<NagerdateApiClient> {

  public NagerdateService(NagerdateApiClient apiClient) {
    super(apiClient);
  }

  public List<CountryV3Dto> countryAvailableCountries() {
    return execute(apiClient::apiV3AvailableCountriesGet);
  }
}
