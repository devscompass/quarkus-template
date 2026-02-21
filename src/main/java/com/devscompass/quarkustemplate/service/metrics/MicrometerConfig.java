package com.devscompass.quarkustemplate.service.metrics;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.config.MeterFilter;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;
import java.util.List;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Singleton
public class MicrometerConfig {

  private final String applicationName;

  @Inject
  public MicrometerConfig(
      @ConfigProperty(name = "quarkus.application.name") String applicationName) {
    this.applicationName = applicationName;
  }

  @Produces
  @Singleton
  public MeterFilter commonTagsFilter() {
    return MeterFilter.commonTags(List.of(Tag.of("application", applicationName)));
  }
}
