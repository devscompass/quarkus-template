package com.devscompass.quarkustemplate.dto.note;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import java.time.Instant;

@RegisterForReflection
public record NoteDTO(
    @JsonProperty("id") String id,
    @JsonProperty("title") String title,
    @JsonProperty("content") String content,
    @JsonProperty("createdAt") Instant createdAt,
    @JsonProperty("updatedAt") Instant updatedAt) {}
