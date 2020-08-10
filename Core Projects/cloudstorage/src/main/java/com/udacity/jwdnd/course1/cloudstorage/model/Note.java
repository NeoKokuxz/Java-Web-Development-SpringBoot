package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Note {
    private Integer noteId;
    private int userId;
    private String noteTitle;
    private String noteDescription;

    public Note(Integer noteId, int userId, String noteTitle, String noteDescription) {
        this.noteId = noteId;
        this.userId = userId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }
}
