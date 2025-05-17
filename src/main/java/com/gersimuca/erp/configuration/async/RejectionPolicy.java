package com.gersimuca.erp.configuration.async;

import com.gersimuca.erp.common.util.LoggerUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum RejectionPolicy {
  ABORT(
      "abort",
      "AbortPolicy",
      "Immediately rejects the task and throws a RejectedExecutionException to indicate the rejection",
      new ThreadPoolExecutor.AbortPolicy()),
  CALLER_RUNS(
      "callerruns",
      "CallerRunsPolicy",
      "Executes the rejected task in the calling thread if the executor is unable to accept it",
      new ThreadPoolExecutor.CallerRunsPolicy()),
  DISCARD(
      "discard",
      "DiscardPolicy",
      "Silently discards the rejected task without any notification or exception",
      new ThreadPoolExecutor.DiscardPolicy()),
  DISCARD_OLDEST(
      "discardoldest",
      "DiscardOldestPolicy",
      "Removes the oldest unprocessed task in the queue and retries the rejected task",
      new ThreadPoolExecutor.DiscardOldestPolicy());

  private final String policyName;
  private final String policyType;
  private final String description;
  private final RejectedExecutionHandler handler;

  RejectionPolicy(
      String policyName, String policyType, String description, RejectedExecutionHandler handler) {
    this.policyName = policyName;
    this.policyType = policyType;
    this.description = description;
    this.handler = handler;
  }

  private static final Map<String, RejectionPolicy> POLICY_MAP = new HashMap<>();

  static {
    for (RejectionPolicy policy : values()) {
      POLICY_MAP.put(policy.getPolicyName(), policy);
    }
  }

  public static RejectedExecutionHandler getHandler(String policy) {
    RejectionPolicy rejectionPolicy = POLICY_MAP.get(policy);
    if (rejectionPolicy != null) {
      LoggerUtils.info(
          log,
          "Using rejection policy '{}': {}.",
          rejectionPolicy.getPolicyName(),
          rejectionPolicy.getDescription());
      return rejectionPolicy.getHandler();
    }

    LoggerUtils.warn(
        log,
        "Unrecognized or missing rejection policy '{}'. Applying default AbortPolicy to manage rejected tasks.",
        policy);
    return new ThreadPoolExecutor.AbortPolicy();
  }
}
