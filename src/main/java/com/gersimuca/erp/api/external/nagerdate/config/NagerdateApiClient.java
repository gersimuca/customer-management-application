package com.gersimuca.erp.api.external.nagerdate.config;

import com.gersimuca.api.external.nagerdate.CountryApiClient;
import com.gersimuca.erp.api.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
    name = "nagerdate-api-service",
    url = "${feign.client.nagerdate.url}",
    configuration = {FeignConfig.class, NagerdateConfig.class})
public interface NagerdateApiClient extends CountryApiClient {}
