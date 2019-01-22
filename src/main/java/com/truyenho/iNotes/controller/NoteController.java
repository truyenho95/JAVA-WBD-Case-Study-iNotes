package com.truyenho.iNotes.controller;

import com.truyenho.iNotes.model.Note;
import com.truyenho.iNotes.model.NoteType;
import com.truyenho.iNotes.service.NoteService;
import com.truyenho.iNotes.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class NoteController {

  @Autowired
  private NoteService noteService;

  @Autowired
  private NoteTypeService noteTypeService;

  @GetMapping(value = {"/", "/note/list"})
  public ModelAndView index(@RequestParam(name = "noteType") Optional<Integer> noteTypeId, @RequestParam(name = "title") Optional<String> title, @PageableDefault(size = 5) Pageable pageable, @ModelAttribute("success") String success) {
    Page<Note> notes;
    ModelAndView modelAndView = new ModelAndView("note/index");
    if (noteTypeId.isPresent() | title.isPresent()) {
      if (noteTypeId.get() == -1) {
        notes = noteService.findAllByTitleContaining(title.get(), pageable);
      } else {
        notes = noteService.findAllByTitleContainingAndNoteType_Id(title.get(), noteTypeId.get(), pageable);
      }
      modelAndView.addObject("search", title.get());
    } else {
      notes = noteService.findAll(pageable);
      modelAndView.addObject("search", "");
    }

    Iterable<NoteType> noteTypes = noteTypeService.findAll();
    modelAndView.addObject("noteTypes", noteTypes);
    modelAndView.addObject("notes", notes);
    modelAndView.addObject("success", success);

    int totalPages = notes.getTotalPages();
    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
      modelAndView.addObject("pageNumbers", pageNumbers);
    }

    return modelAndView;
  }

  @GetMapping("/note/create")
  public ModelAndView showCreateNoteForm() {
    ModelAndView modelAndView = new ModelAndView("note/create");
    Iterable<NoteType> noteTypes = noteTypeService.findAll();
    modelAndView.addObject("note", new Note());
    modelAndView.addObject("noteTypes", noteTypes);
    return modelAndView;
  }

  @PostMapping("/note/create")
  public ModelAndView createNote(@Validated @ModelAttribute("note") Note note, BindingResult bindingResult) {
    Iterable<NoteType> noteTypes = noteTypeService.findAll();

    if (bindingResult.hasFieldErrors()) {
      ModelAndView modelAndView = new ModelAndView("note/create");
      modelAndView.addObject("note", new Note());
      modelAndView.addObject("noteTypes", noteTypes);
      modelAndView.addAllObjects(bindingResult.getModel());
      return modelAndView;
    } else {
      noteService.save(note);
      ModelAndView modelAndView = new ModelAndView("redirect:/");
      modelAndView.addObject("success", "Tạo ghi chú mới thành công!");
      modelAndView.addObject("note", new Note());
      return modelAndView;
    }
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
