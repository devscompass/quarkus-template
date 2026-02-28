package com.devscompass.quarkustemplate.api.rest.common;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class LoggingFilter implements ContainerRequestFilter {

  private static final Logger LOG = LoggerFactory.getLogger(LoggingFilter.class);

  @Override
  public void filter(ContainerRequestContext context) {
    LOG.debug("Request: {} at '{}'", context.getMethod(), context.getUriInfo().getPath());
  }
}
