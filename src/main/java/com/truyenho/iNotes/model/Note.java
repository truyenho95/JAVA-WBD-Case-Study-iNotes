package com.truyenho.iNotes.model;

import javax.persistence.*;

@Entity
@Table(name = "notes")
public class Note {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;
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