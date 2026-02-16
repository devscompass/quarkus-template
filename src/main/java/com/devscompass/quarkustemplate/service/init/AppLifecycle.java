package com.devscompass.quarkustemplate.service.init;

import io.quarkus.runtime.Shutdown;
import io.quarkus.runtime.Startup;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;

@Singleton
public class AppLifecycle {

  private static final Logger LOG = Logger.getLogger(AppLifecycle.class);

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
