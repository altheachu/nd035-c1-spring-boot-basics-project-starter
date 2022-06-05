package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO NOTES (userId, notetitle, notedescription) VALUES(#{userId}, #{notetitle}, #{notedescription})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int addNote(Note note);

    @Select("SELECT * FROM NOTES WHERE noteId = #{noteId}")
    Note selectNote(Integer noteId);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    void removeNote(Integer noteId);

    @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
    int updateNote(Note note);

}
