package com.gersimuca.erp.configuration.async;

import com.gersimuca.erp.common.exception.TaskRejectionHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum RejectionPolicy {
  ;

  private static final Map<String, RejectedExecutionHandler> POLICY_MAP = new HashMap<>();

  static {
    POLICY_MAP.put("abort", new ThreadPoolExecutor.AbortPolicy());
    POLICY_MAP.put("callerruns", new ThreadPoolExecutor.CallerRunsPolicy());
    POLICY_MAP.put("discard", new ThreadPoolExecutor.DiscardPolicy());
    POLICY_MAP.put("discardoldest", new ThreadPoolExecutor.DiscardOldestPolicy());
  }

  public static RejectedExecutionHandler getHandler(String policy) {
    if (POLICY_MAP.containsKey(policy)) {
      log.info("Using rejection policy '{}'.", policy);
      return POLICY_MAP.get(policy);
    }

    log.warn(
        "Unrecognized or missing rejection policy '{}'. Applying default TaskRejectionHandler to manage rejected tasks.",
        policy);
    return new TaskRejectionHandler();
  }
}
