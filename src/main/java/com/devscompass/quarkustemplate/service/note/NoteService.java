package com.devscompass.quarkustemplate.service.note;

import com.devscompass.quarkustemplate.dto.note.NoteCreateDTO;
import com.devscompass.quarkustemplate.dto.note.NoteDTO;
import com.devscompass.quarkustemplate.dto.note.NoteSummaryDTO;
import com.devscompass.quarkustemplate.dto.note.NoteUpdateDTO;
import io.smallrye.mutiny.Uni;
import java.util.List;

public interface NoteService {

  Uni<List<NoteSummaryDTO>> listNotes();

  Uni<NoteDTO> getNoteById(String id);

  Uni<NoteDTO> createNote(NoteCreateDTO dto);

  Uni<NoteDTO> updateNote(String id, NoteUpdateDTO dto);

  Uni<Void> deleteNote(String id);
}
