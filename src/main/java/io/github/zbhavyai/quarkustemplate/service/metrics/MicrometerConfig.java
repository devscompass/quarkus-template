package io.github.zbhavyai.quarkustemplate.service.metrics;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.config.MeterFilter;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;
import java.util.List;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Singleton
public class MicrometerConfig {

  @ConfigProperty(name = "quarkus.application.name")
  private String applicationName;

  @Produces
  @Singleton
  public MeterFilter commonTagsFilter() {
    return MeterFilter.commonTags(List.of(Tag.of("application", applicationName)));
  }
}
