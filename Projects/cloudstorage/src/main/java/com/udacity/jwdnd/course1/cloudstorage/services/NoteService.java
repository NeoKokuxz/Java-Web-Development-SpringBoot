package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    //Initial Mapper
    private final NotesMapper notesMapper;
    private final UserMapper userMapper;

    public NoteService(NotesMapper notesMapper, UserMapper userMapper) {
        this.notesMapper = notesMapper;
        this.userMapper = userMapper;
    }

    //Get
    public Note getNote(String username){
       return notesMapper.selectNote(userMapper.getUser(username).getUserId());
    }

    public List<Note> getNoteList(String username){
        User user = userMapper.getUser(username);
        List<Note> noteList = notesMapper.selectNoteList(user.getUserId());
        //null - not found in database
        if(noteList == null){
            return new ArrayList<>();
        }
        return noteList;
    }

    //Post
    public Integer insertNote(Note note){
       return notesMapper.insertNote(new Note(null,
                                                note.getUserId(),
                                                note.getNoteTitle(),
                                                note.getNoteDescription()));

    }

    //Put
    public Integer updateNote(Note note){
        return notesMapper.updateNote(note);
    }

    //Delete
    public void deleteNote(Integer noteId){
        notesMapper.deleteNote(noteId);
    }
}
