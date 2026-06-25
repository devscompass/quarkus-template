package com.devscompass.quarkustemplate.api.rest.common;

import com.devscompass.quarkustemplate.exceptions.ConflictException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.hibernate.HibernateException;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.jboss.resteasy.reactive.common.NotImplementedYet;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ExceptionMapper {

  private static final Logger LOG = LoggerFactory.getLogger(ExceptionMapper.class);

  @ServerExceptionMapper(NotFoundException.class)
  public Response handleNotFound(NotFoundException e) {
    return ResponseUtils.handleFailure(Response.Status.NOT_FOUND, e.getMessage());
  }

  @ServerExceptionMapper(ConflictException.class)
  public Response handleConflict(ConflictException e) {
    return ResponseUtils.handleFailure(Response.Status.CONFLICT, e.getMessage());
  }

  @ServerExceptionMapper({
    IllegalArgumentException.class,
    BadRequestException.class,
    HibernateException.class
  })
  public Response handleBadRequest(Throwable e) {
    return ResponseUtils.handleFailure(Response.Status.BAD_REQUEST, e.getMessage());
  }

  @ServerExceptionMapper(NotAllowedException.class)
  public Response handleMethodNotAllowed(NotAllowedException e) {
    return ResponseUtils.handleFailure(Response.Status.METHOD_NOT_ALLOWED, e.getMessage());
  }

  @ServerExceptionMapper(NotImplementedYet.class)
  public Response handleNotImplemented(NotImplementedYet e) {
    return ResponseUtils.handleFailure(Response.Status.NOT_IMPLEMENTED, e.getMessage());
  }

  @ServerExceptionMapper(ClientWebApplicationException.class)
  public Response handleClientWebApplicationException(ClientWebApplicationException e) {
    LOG.error("handleClientWebApplicationException: ", e);
    int status = e.getResponse().getStatus();
    String raw = e.getResponse().readEntity(String.class);

    String message;
    try {
      JsonObject json = Json.decodeValue(raw, JsonObject.class);

      if (json.containsKey("error")) {
        try {
          message = json.getJsonObject("error").getString("message");
        } catch (Exception ex) {
          message = json.getString("error");
        }
      } else if (json.containsKey("message")) {
        message = json.getString("message");
      } else {
        message = raw;
      }
    } catch (Exception ex) {
      message = raw;
    }
    return ResponseUtils.handleFailure(Response.Status.fromStatusCode(status), message);
  }

  @ServerExceptionMapper(Throwable.class)
  public Response handleServiceUnavailable(Throwable e) {
    LOG.error("Unexpected error: ", e);
    return ResponseUtils.handleFailure(Response.Status.SERVICE_UNAVAILABLE, e.getMessage());
  }
}
