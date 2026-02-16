package com.devscompass.quarkustemplate.dto.note;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import java.time.Instant;

@RegisterForReflection
public record NoteSummaryDTO(
    @JsonProperty("id") String id,
    @JsonProperty("title") String title,
    @JsonProperty("updatedAt") Instant updatedAt) {}
