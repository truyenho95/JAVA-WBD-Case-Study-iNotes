package com.truyenho.iNotes.service;

import com.truyenho.iNotes.model.NoteType;

import java.util.Optional;

public interface NoteTypeService {
  Iterable<NoteType> findAll();

  void save(NoteType noteType);

  void remove(int id);

  Optional<NoteType> findById(Integer id);
}
