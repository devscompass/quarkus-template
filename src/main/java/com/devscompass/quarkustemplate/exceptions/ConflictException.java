package com.devscompass.quarkustemplate.exceptions;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.core.Response;

public class ConflictException extends ClientErrorException {

  public ConflictException() {
    super(Response.Status.CONFLICT);
  }

  public ConflictException(final String message) {
    super(message, Response.Status.CONFLICT);
  }
}
