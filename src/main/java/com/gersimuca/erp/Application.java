package com.gersimuca.erp;

import com.gersimuca.erp.configuration.async.AsyncConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(AsyncConfiguration.class)
@EnableScheduling
@EnableFeignClients(basePackages = "com.gersimuca.erp.api.external")
@EnableAspectJAutoProxy
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
