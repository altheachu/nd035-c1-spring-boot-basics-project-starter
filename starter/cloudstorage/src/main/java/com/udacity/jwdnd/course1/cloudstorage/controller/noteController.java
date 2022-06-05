package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.noteServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.services.userServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class noteController {

    private userServiceImpl userService;
    private noteServiceImpl noteService;
    private EncryptionService encryptionService;

    public noteController(userServiceImpl userService, noteServiceImpl noteService, EncryptionService encryptionService) {
        this.userService = userService;
        this.noteService = noteService;
        this.encryptionService = encryptionService;
    }

    @PostMapping("/note/add")
    public String addNote(Authentication authentication, Note note){
        // confirm the user id
        User user = userService.getUser(authentication.getClass().getName());
        Integer userId = user.getUserId();
        Integer noteId = note.getNoteId();
        Note dbNote = noteService.getNote(noteId);

        if(dbNote==null){
            noteService.InsertNote(note, userId);
        }

        return "result";
    }

}
