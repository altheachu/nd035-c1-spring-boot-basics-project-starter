package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class noteServiceImpl {

    private NoteMapper noteMapper;

    public noteServiceImpl(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public Note getNote(Integer noteId){
        return noteMapper.selectNote(noteId);
    }

    public List<Note> getAllNote(Integer userId){return noteMapper.getNoteForUser(userId);}

    public void InsertNote(Note note, Integer userId){
        Note newNote = new Note(null, note.getNoteTitle(), note.getNoteDescription(),userId);
        noteMapper.addNote(newNote);
    }
    public void deleteNote(Integer noteId){
        noteMapper.removeNote(noteId);
    }

}
