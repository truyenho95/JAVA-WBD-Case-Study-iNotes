package com.truyenho.iNotes.service;

import com.truyenho.iNotes.model.NoteType;

public interface NoteTypeService {
  Iterable<NoteType> findAll();

  void save(NoteType noteType);

  void remove(int id);
}
