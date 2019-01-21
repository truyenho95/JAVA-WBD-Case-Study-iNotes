package com.truyenho.iNotes.service;

import com.truyenho.iNotes.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteService {
  Page<Note> findAll(Pageable pageable);

  Page<Note> findAllByTitleContaining(String title, Pageable pageable);

  void save(Note note);

  void remove(int id);
}
