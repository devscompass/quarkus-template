package com.devscompass.quarkustemplate.service.ping;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class PingServiceImpl implements PingService {

  private static final Logger LOG = LoggerFactory.getLogger(PingServiceImpl.class);

  @Override
  public Uni<String> ping() {
    LOG.debug("ping");
    return Uni.createFrom().item("pong\n");
  }
}
