package com.gersimuca.erp.api.external.nagerdate.config;

import com.gersimuca.erp.api.config.FeignConfig;
import com.gersimuca.erp.api.oauth2.OAuth2ApiClient;
import com.gersimuca.erp.api.oauth2.OAuth2Config;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
    name = "nagerdate-oauth2",
    url = "${spring.security.oauth2.client.provider.nagerdate.token-uri}",
    configuration = {FeignConfig.class, OAuth2Config.class})
public interface NagerdateOAuth2FeignClient extends OAuth2ApiClient {}
