package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.noteServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.services.userServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addNote(Authentication authentication, Note note, Model model){
        // confirm the user id
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();
        Integer noteId = note.getNoteId();
        Note dbNote = noteService.getNote(noteId);

        if(dbNote==null){
            noteService.InsertNote(note, userId);
            model.addAttribute("successMessage",true);
        }

        return "result";
    }
    @GetMapping("/note/{noteId}/delete")
    public String deleteNote(@PathVariable Integer noteId, Model model){
        try{
            noteService.deleteNote(noteId);
            model.addAttribute("successMessage",true);
        }catch(Exception e){
            model.addAttribute("successMessage",false);
        }
        return "result";
    }

}
