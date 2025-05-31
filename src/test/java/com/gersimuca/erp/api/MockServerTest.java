package com.gersimuca.erp.api;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gersimuca.erp.configuration.external.Token;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.MediaType;
import org.mockserver.model.StringBody;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MockServerTest {
  private MockServerClient mockServerClient;
  private ObjectMapper objectMapper;

  @BeforeAll
  public void setupMockServer() throws JsonProcessingException {
    ClientAndServer server = ClientAndServer.startClientAndServer(8082);
    mockServerClient = new MockServerClient("localhost", server.getLocalPort());
    objectMapper = new ObjectMapper();
    mockEndpoints();
  }

  @AfterAll
  public void tearDownServer() {
    mockServerClient.stop();
  }

  private void mockEndpoints() throws JsonProcessingException {
    mockTokenEndpoints();
  }

  private void mockTokenEndpoints() throws JsonProcessingException {
    String tokenResponse =
        objectMapper.writeValueAsString(Token.builder().accessToken("accessToken").build());
    String validRequestBody =
        "grant_type=password&client_id=core_calculation&username=admin&password=admin";
    mockServerClient
        .when(
            request()
                .withMethod("POST")
                .withPath("/token")
                .withBody(StringBody.exact(validRequestBody)))
        .respond(
            response()
                .withBody(tokenResponse)
                .withContentType(MediaType.APPLICATION_JSON)
                .withStatusCode(200));
  }
}
