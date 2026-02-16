package com.devscompass.quarkustemplate.service.ping;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class PingServiceImpl implements PingService {

  private static final Logger LOG = Logger.getLogger(PingServiceImpl.class);

  @Override
  public Uni<String> ping() {
    LOG.debugf("ping");
    return Uni.createFrom().item("pong\n");
  }
}
