package com.devscompass.quarkustemplate.service.note;

import com.devscompass.quarkustemplate.dao.note.NoteRepository;
import com.devscompass.quarkustemplate.dto.note.NoteCreateDTO;
import com.devscompass.quarkustemplate.dto.note.NoteDTO;
import com.devscompass.quarkustemplate.dto.note.NoteSummaryDTO;
import com.devscompass.quarkustemplate.dto.note.NoteUpdateDTO;
import com.devscompass.quarkustemplate.mapper.note.NoteMapper;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class NoteServiceImpl implements NoteService {

  private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(NoteServiceImpl.class);

  private final NoteRepository noteRepository;

  @Inject
  public NoteServiceImpl(NoteRepository noteRepository) {
    this.noteRepository = noteRepository;
  }

  @WithSession
  @Override
  public Uni<List<NoteSummaryDTO>> listNotes() {
    LOG.debug("listNotes");

    return noteRepository
        .listAll()
        .map(list -> list.stream().map(NoteMapper::toSummaryDTO).toList());
  }

  @WithSession
  @Override
  public Uni<NoteDTO> getNoteById(String id) {
    LOG.debug("getNoteById: id='{}'", id);

    return noteRepository
        .findById(id)
        .onItem()
        .ifNull()
        .failWith(() -> new NotFoundException("Note not found"))
        .map(NoteMapper::toDTO);
  }

  @WithTransaction
  @Override
  public Uni<NoteDTO> createNote(NoteCreateDTO dto) {
    LOG.debug("createNote: dto='{}'", dto);

    return noteRepository.persist(NoteMapper.fromCreateDTO(dto)).map(NoteMapper::toDTO);
  }

  @WithTransaction
  @Override
  public Uni<NoteDTO> updateNote(String id, NoteUpdateDTO dto) {
    LOG.debug("updateNote: dto='{}'", dto);

    return noteRepository
        .findById(id)
        .onItem()
        .ifNull()
        .failWith(() -> new NotFoundException("Note not found"))
        .map(note -> NoteMapper.updateNote(note, dto))
        .map(NoteMapper::toDTO);
  }

  @WithTransaction
  @Override
  public Uni<Void> deleteNote(String id) {
    LOG.debug("deleteNote: id='{}'", id);

    return noteRepository
        .deleteById(id)
        .onItem()
        .transformToUni(
            deleted ->
                deleted
                    ? Uni.createFrom().voidItem()
                    : Uni.createFrom().failure(new NotFoundException("Note not found")));
  }
}
