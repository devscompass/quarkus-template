package com.devscompass.quarkustemplate.dto.note;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record NoteCreateDTO(
    @JsonProperty("title") String title, @JsonProperty("content") String content) {}
