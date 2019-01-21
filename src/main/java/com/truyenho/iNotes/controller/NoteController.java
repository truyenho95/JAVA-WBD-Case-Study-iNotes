package com.truyenho.iNotes.controller;

import com.truyenho.iNotes.model.Note;
import com.truyenho.iNotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class NoteController {

  @Autowired
  private NoteService noteService;

  @GetMapping("/")
  public ModelAndView index(@RequestParam Optional<String> title, Pageable pageable) {
    Page<Note> notes;
    ModelAndView modelAndView = new ModelAndView("index");
    if (title.isPresent()) {
      notes = noteService.findAllByTitleContaining(title.get(), pageable);
    } else {
      notes = noteService.findAll(PageRequest.of(pageable.getPageNumber(), 5));
    }
    modelAndView.addObject("notes", notes);
    return modelAndView;
  }

}
