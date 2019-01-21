package com.truyenho.iNotes.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "note_type")
public class NoteType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String description;

  @OneToMany(targetEntity = Note.class)
  private List<Note> notes;

  public NoteType() {
  }

  public NoteType(String name) {
    this.name = name;
  }

  public NoteType(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Note> getNotes() {
    return notes;
  }

  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }
}
