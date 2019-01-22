package com.truyenho.iNotes.repository;

import com.truyenho.iNotes.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository<Note, Integer> {
  Page<Note> findAllByTitleContaining(String title, Pageable pageable);

  Page<Note> findAllByTitleContainingAndNoteType_Id(String title, Integer noteTypeId, Pageable pageable);
}
