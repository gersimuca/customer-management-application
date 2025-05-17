package com.gersimuca.erp.configuration.async;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import com.gersimuca.erp.common.exception.TaskRejectionHandler;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import org.junit.jupiter.api.Test;

class RejectionPolicyTest {

  @Test
  void knownPolicies() {
    assertInstanceOf(ThreadPoolExecutor.AbortPolicy.class, RejectionPolicy.getHandler("abort"));
    assertInstanceOf(
        ThreadPoolExecutor.CallerRunsPolicy.class, RejectionPolicy.getHandler("callerruns"));
    assertInstanceOf(ThreadPoolExecutor.DiscardPolicy.class, RejectionPolicy.getHandler("discard"));
    assertInstanceOf(
        ThreadPoolExecutor.DiscardOldestPolicy.class, RejectionPolicy.getHandler("discardoldest"));
  }

  @Test
  void unknownPolicyDefaults() {
    RejectedExecutionHandler handler = RejectionPolicy.getHandler("invalid");
    assertEquals(TaskRejectionHandler.class, handler.getClass());
  }
}
