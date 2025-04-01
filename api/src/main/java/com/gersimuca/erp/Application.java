package com.gersimuca.erp;

import com.gersimuca.erp.configuration.AsyncConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AsyncConfiguration.class)
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
