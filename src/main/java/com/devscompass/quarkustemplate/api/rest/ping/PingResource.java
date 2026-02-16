package com.devscompass.quarkustemplate.api.rest.ping;

import com.devscompass.quarkustemplate.api.rest.common.ResponseUtils;
import com.devscompass.quarkustemplate.service.ping.PingService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/ping")
public class PingResource {

  private final PingService service;

  @Inject
  public PingResource(PingService service) {
    this.service = service;
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Uni<Response> ping() {
    return service.ping().onItem().transform(ResponseUtils::handleSuccess);
  }
}
