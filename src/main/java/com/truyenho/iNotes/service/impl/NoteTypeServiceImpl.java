package com.truyenho.iNotes.service.impl;

import com.truyenho.iNotes.model.NoteType;
import com.truyenho.iNotes.repository.NoteTypeRepository;
import com.truyenho.iNotes.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("noteTypeService")
public class NoteTypeServiceImpl implements NoteTypeService {
  @Autowired
  private NoteTypeRepository noteTypeRepository;

  @Override
  public Iterable<NoteType> findAll() {
    return noteTypeRepository.findAll();
  }

  @Override
  public void save(NoteType noteType) {
    noteTypeRepository.save(noteType);
  }

  @Override
  public void remove(int id) {
    noteTypeRepository.deleteById(id);
  }

  @Override
  public Optional<NoteType> findById(Integer id) {
    return noteTypeRepository.findById(id);
  }
}
