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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class NoteController {

  @Autowired
  private NoteService noteService;

  @Autowired
  private NoteTypeService noteTypeService;

  @GetMapping(value = {"/", "/note/list"})
  public ModelAndView index(@RequestParam(name="noteType") Optional<Integer> noteTypeId, @RequestParam(name="title") Optional<String> title, Pageable pageable, @ModelAttribute("success") String success) {
    Page<Note> notes;
    ModelAndView modelAndView = new ModelAndView("note/index");
    if (noteTypeId.isPresent() | title.isPresent()) {
      notes = noteService.findAllByTitleContaining(title.get(), pageable);
    } else {
      notes = noteService.findAll(PageRequest.of(pageable.getPageNumber(), 5));
    }

    Iterable<NoteType> noteTypes = noteTypeService.findAll();
    modelAndView.addObject("noteTypes", noteTypes);
    modelAndView.addObject("notes", notes);
    modelAndView.addObject("success", success);

    return modelAndView;
  }

  @GetMapping("/note/create")
  public ModelAndView showCreateNoteForm() {
    ModelAndView modelAndView = new ModelAndView("note/create");
    Iterable<NoteType> noteTypes = noteTypeService.findAll();
    modelAndView.addObject("note", new Note());
    modelAndView.addObject("noteTypies", noteTypes);
    return modelAndView;
  }

  @PostMapping("/note/create")
  public ModelAndView createNote(@ModelAttribute("note") Note note) {
    noteService.save(note);
    ModelAndView modelAndView = new ModelAndView("redirect:/");
    modelAndView.addObject("success", "Tạo ghi chú mới thành công!");
    modelAndView.addObject("note", new Note());
    return modelAndView;
  }

  @GetMapping("/note/edit/{id}")
  public ModelAndView showEditNoteForm(@PathVariable("id") Note note) {
    ModelAndView modelAndView = new ModelAndView("note/edit");
    Iterable<NoteType> noteTypes = noteTypeService.findAll();
    modelAndView.addObject("note", note);
    modelAndView.addObject("noteTypes", noteTypes);
    return modelAndView;
  }

  @PostMapping("/note/edit")
  public ModelAndView editNote(@ModelAttribute("note") Note note) {
    noteService.save(note);
    ModelAndView modelAndView = new ModelAndView("redirect:/");
    modelAndView.addObject("success", "Sửa ghi chú thành công");
    modelAndView.addObject("note", new Note());
    return modelAndView;
  }

  @GetMapping("/note/delete/{id}")
  public ModelAndView showDeleteNoteForm(@PathVariable("id") Note note) {
    ModelAndView modelAndView = new ModelAndView("note/delete");
    modelAndView.addObject("note", note);
    return modelAndView;
  }

  @PostMapping("/note/delete")
  public ModelAndView deleteNote(@ModelAttribute("note") Note note) {
    noteService.remove(note.getId());
    ModelAndView modelAndView = new ModelAndView("redirect:/");
    modelAndView.addObject("success", "Bản ghi đã được xóa!");
    return modelAndView;
  }
}
