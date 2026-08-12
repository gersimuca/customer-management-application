package com.gersimuca.erp.api.external;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

import feign.FeignException;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

/**
 * Generic service to execute any external API call.
 *
 * @param <C> API client type
 */
@RequiredArgsConstructor
public class ExternalApiService<C> {

  protected final C apiClient;

  /**
   * Execute an API call and unwrap the ResponseEntity body.
   *
   * @param apiCall Supplier of the ResponseEntity
   * @param <R> Response type
   * @return response body
   */
  protected <R> ResponseEntity<R> execute(Supplier<ResponseEntity<R>> apiCall) {
    try {
      final ResponseEntity<R> response = apiCall.get();
      if (!response.getStatusCode().is2xxSuccessful()) {
        throw new IllegalStateException("API call failed with status: " + response.getStatusCode());
      }
      var contentType = response.getHeaders().getContentType();
      if (contentType == null || !contentType.includes(APPLICATION_JSON)) {
        throw new IllegalStateException(
            "Invalid Content-Type from API: " + contentType + " (expected application/json)");
      }
      return response;
    } catch (FeignException fe) {
      throw new IllegalStateException("External API call failed: " + fe.contentUTF8(), fe);
    } catch (Exception ex) {
      throw new IllegalStateException("Unexpected error calling external API", ex);
    }
  }
}
