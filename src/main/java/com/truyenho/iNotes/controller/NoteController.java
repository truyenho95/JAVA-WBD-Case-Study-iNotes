package com.truyenho.iNotes.controller;

import com.truyenho.iNotes.model.Note;
import com.truyenho.iNotes.model.NoteType;
import com.truyenho.iNotes.service.NoteService;
import com.truyenho.iNotes.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class NoteController {

  @Autowired
  private NoteService noteService;

  @Autowired
  private NoteTypeService noteTypeService;

  @GetMapping(value = {"/", "/note/list"})
  public ModelAndView index(@RequestParam Optional<String> title, Pageable pageable) {
    Page<Note> notes;
    ModelAndView modelAndView = new ModelAndView("note/index");
    if (title.isPresent()) {
      notes = noteService.findAllByTitleContaining(title.get(), pageable);
    } else {
      notes = noteService.findAll(PageRequest.of(pageable.getPageNumber(), 5));
    }
    modelAndView.addObject("notes", notes);
    return modelAndView;
  }

  @GetMapping("/note/create")
  public ModelAndView showCreateForm() {
    ModelAndView modelAndView = new ModelAndView("note/create");
    modelAndView.addObject("note", new Note());
    Iterable<NoteType> noteTypes = noteTypeService.findAll();
    modelAndView.addObject("noteTypies", noteTypes);
    return modelAndView;
  }

}
