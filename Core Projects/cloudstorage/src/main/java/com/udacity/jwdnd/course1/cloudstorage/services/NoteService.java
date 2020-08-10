package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all method for Note services
 *
 * @Author Neo Chen
 *
 */
@Service
public class NoteService {


    private final NotesMapper notesMapper;
    private final UserMapper userMapper;

    public NoteService(NotesMapper notesMapper, UserMapper userMapper) {
        this.notesMapper = notesMapper;
        this.userMapper = userMapper;
    }

    /**
     * The getNote method select Note from noteMapper by username
     *
     * @param username
     * @return Note object
     */
    public Note getNote(String username){
       return notesMapper.selectNote(userMapper.getUser(username).getUserId());
    }

    /**
     * The getNoteList method will get user information by username
     * then return a list of notes from noteMapper by userId
     *
     * @param username
     * @return List of notes or empty Array list
     */
    public List<Note> getNoteList(String username){
        User user = userMapper.getUser(username);
        List<Note> noteList = notesMapper.selectNoteList(user.getUserId());
        //null - not found in database
        if(noteList == null){
            return new ArrayList<>();
        }
        return noteList;
    }

    /**
     * The insertNote method insert note into noteMapper
     *
     * @param note
     * @return int value
     */
    public Integer insertNote(Note note){
       return notesMapper.insertNote(new Note(null,
                                                note.getUserId(),
                                                note.getNoteTitle(),
                                                note.getNoteDescription()));
    }

    /**
     * The updateNote method will update note data in noteMapper
     *
     * @param note
     * @return int value
     */
    public Integer updateNote(Note note){
        return notesMapper.updateNote(note);
    }

    /**
     * The deleteNote method deletes note in noteMapper by noteId
     *
     * @param noteId
     */
    public void deleteNote(Integer noteId){
        notesMapper.deleteNote(noteId);
    }
}
