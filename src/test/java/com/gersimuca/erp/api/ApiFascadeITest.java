package com.gersimuca.erp.api;

import com.gersimuca.erp.AuthenticatedMvcTest;
import com.gersimuca.erp.common.util.LogAppender;
import com.gersimuca.erp.common.util.TestUtils;
import com.gersimuca.erp.configuration.external.AbstractOAuth2TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@AuthenticatedMvcTest
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ApiFascadeITest {
  @Autowired private ApiFacade apiFacade;
  private LogAppender logAppender;

  @BeforeEach
  void setup() {
    logAppender = TestUtils.createLogAppenderForClass(AbstractOAuth2TokenService.class);
  }
}
