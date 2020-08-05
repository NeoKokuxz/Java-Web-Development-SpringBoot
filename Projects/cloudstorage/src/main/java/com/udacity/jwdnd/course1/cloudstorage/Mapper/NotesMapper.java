package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Note selectNote(Integer noteId);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Note> selectNoteList(Integer userId);

    @Insert("INSERT INTO NOTES(userid, notetitle, notedescription)" +
            "VALUES(#{userId},#{noteTitle},#{noteDescription})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Integer insertNote(Note note);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    Integer updateNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNote(Integer noteId);

}

/*
CREATE TABLE IF NOT EXISTS NOTES (
    noteid INT PRIMARY KEY auto_increment,
    notetitle VARCHAR(20),
    notedescription VARCHAR (1000),
    userid INT,
    foreign key (userid) references USERS(userid)
);
 */

