package com.truyenho.iNotes.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "notes")
public class Note {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotEmpty
  @Size(max = 20)
  private String title;

  @Size(max = 100)
  private String content;

  @ManyToOne
  @JoinColumn(name = "type_id")
  private NoteType noteType;

  public Note() {
  }

  public Note(String title) {
    this.title = title;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public NoteType getNoteType() {
    return noteType;
  }

  public void setNoteType(NoteType noteType) {
    this.noteType = noteType;
  }
}
