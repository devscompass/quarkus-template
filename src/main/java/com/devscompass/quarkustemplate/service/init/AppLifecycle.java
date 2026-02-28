package com.devscompass.quarkustemplate.service.init;

import io.quarkus.runtime.Shutdown;
import io.quarkus.runtime.Startup;
import jakarta.inject.Singleton;
import org.slf4j.LoggerFactory;

@Singleton
public class AppLifecycle {

  private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AppLifecycle.class);

  @Startup
  void start() {
    logMessage("Starting application");
  }

  @Shutdown
  void shutdown() {
    logMessage("Shutting down application");
  }

  private void logMessage(String message) {
    LOG.info("--------------------------------------------------------------------------------");
    LOG.info(message);
    LOG.info("--------------------------------------------------------------------------------");
  }
}
