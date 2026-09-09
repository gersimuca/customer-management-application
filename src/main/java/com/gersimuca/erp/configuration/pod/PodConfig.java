package com.gersimuca.erp.configuration.pod;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PodConfig {

  @Bean
  public String podName() throws UnknownHostException {
    return InetAddress.getLocalHost().getHostName();
  }
}
