package com.devscompass.quarkustemplate.api.rest.common;

import com.devscompass.quarkustemplate.dto.error.ErrorResponse;
import jakarta.ws.rs.core.Response;
import java.net.URI;

public class ResponseUtils {

  public static Response handleSuccess(Object obj) {
    return Response.ok().entity(obj).build();
  }

  public static Response handleCreated(Object obj, URI uri) {
    return Response.created(uri).entity(obj).build();
  }

  public static Response handleDeleted() {
    return Response.noContent().build();
  }

  public static Response handleFailure(Response.Status status, String message) {
    return Response.status(status).entity(ErrorResponse.create(message)).build();
  }
}
