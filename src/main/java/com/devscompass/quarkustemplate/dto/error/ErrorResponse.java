package com.devscompass.quarkustemplate.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.vertx.core.json.JsonObject;

@RegisterForReflection
public record ErrorResponse(@JsonProperty("error") String errorMessage) {

  public static ErrorResponse create(String message) {
    return new ErrorResponse(message);
  }

  public JsonObject asJsonObject() {
    return JsonObject.mapFrom(this);
  }
}
