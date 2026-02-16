package com.devscompass.quarkustemplate.dao.note;

import com.devscompass.quarkustemplate.entity.note.Note;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NoteRepository implements PanacheRepositoryBase<Note, String> {}
