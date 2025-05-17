package com.gersimuca.erp.configuration.async;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutor;

@EnableAsync
@Configuration
@ConfigurationProperties(prefix = "async")
@Slf4j
@Getter
@Setter
@Data
public class AsyncConfiguration {

  private Integer corePoolSize;
  private Integer maxPoolSize;
  private Integer queueCapacity;
  private Integer keepAliveSeconds;
  private Boolean waitForTasksToCompleteOnShutdown;
  private String rejectedExecutionHandlerPolicy;
  private Integer phase;

  @Bean(name = "asyncTaskExecutor")
  public ThreadPoolTaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(getCorePoolSize());
    executor.setMaxPoolSize(getMaxPoolSize());
    executor.setQueueCapacity(getQueueCapacity());
    executor.setKeepAliveSeconds(getKeepAliveSeconds());
    executor.setThreadNamePrefix("AsyncThreadPoolExecutor-");
    executor.setWaitForTasksToCompleteOnShutdown(getWaitForTasksToCompleteOnShutdown());
    executor.setRejectedExecutionHandler(
        getRejectedExecutionHandler(getRejectedExecutionHandlerPolicy()));
    executor.setPhase(getPhase());
    executor.setTaskDecorator(new MappedDiagnosticContextTaskDecorator());
    executor.initialize();
    return executor;
  }

  @Bean(name = "asyncSecurityContexExecutor")
  public Executor asyncSecurityTaskExecutor(
      @Qualifier("asyncTaskExecutor") ThreadPoolTaskExecutor threadPoolTaskExecutor) {
    return new DelegatingSecurityContextExecutor(threadPoolTaskExecutor);
  }

  private RejectedExecutionHandler getRejectedExecutionHandler(
      final String rejectedExecutionHandlerPolicy) {
    return RejectionPolicy.getHandler(rejectedExecutionHandlerPolicy);
  }
}
