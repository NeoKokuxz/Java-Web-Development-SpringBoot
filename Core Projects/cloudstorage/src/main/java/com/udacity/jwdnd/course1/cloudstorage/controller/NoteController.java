package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {

    private final NoteService noteService;
    private final UserMapper userMapper;

    public NoteController(NoteService noteService, UserMapper userMapper){
        this.noteService = noteService;
        this.userMapper = userMapper;
    }

    @PostMapping("/note/create")
    public String createNote(Authentication a, Note note, Model model){
        String errorMessage = null;
        int rowAffected;
        if(note.getNoteId() == null){
            note.setUserId(userMapper.getUser(a.getName()).getUserId());
            rowAffected = noteService.insertNote(note);
        } else {
            rowAffected = noteService.updateNote(note);
        }
        if(rowAffected <= 0){
            errorMessage = "Error during note request! Please try again later!";
            model.addAttribute("errorMessage", errorMessage);
            return "result";
        }
        String successMessage = "Success uploaded note! ";
        model.addAttribute("successMessage", successMessage);
        return "result";
    }

    @GetMapping("/note/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Model model){
        String successMessage = "Success deleted note";
        noteService.deleteNote(noteId);
        model.addAttribute("successMessage", successMessage);
        return "result";
    }

}
