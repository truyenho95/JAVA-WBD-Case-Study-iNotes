package com.truyenho.iNotes.service.impl;

import com.truyenho.iNotes.model.Note;
import com.truyenho.iNotes.repository.NoteRepository;
import com.truyenho.iNotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

  @Autowired
  private NoteRepository noteRepository;

  @Override
  public Page<Note> findAll(Pageable pageable) {
    return noteRepository.findAll(pageable);
  }

  @Override
  public Page<Note> findAllByTitleContaining(String title, Pageable pageable) {
    return noteRepository.findAllByTitleContaining(title, pageable);
  }

  @Override
  public Page<Note> findAllByTitleContainingAndNoteType_Id(String title, Integer noteTypeId, Pageable pageable) {
    return noteRepository.findAllByTitleContainingAndNoteType_Id(title, noteTypeId, pageable);
  }

  @Override
  public void save(Note note) {
    noteRepository.save(note);
  }

  @Override
  public void remove(Integer id) {
    noteRepository.deleteById(id);
  }

  @Override
  public Optional<Note> findById(Integer id) {
    return noteRepository.findById(id);
  }


}
