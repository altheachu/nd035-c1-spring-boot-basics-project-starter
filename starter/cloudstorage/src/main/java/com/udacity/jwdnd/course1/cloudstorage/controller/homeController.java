package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class homeController {

    private final noteServiceImpl noteService;
    private final userServiceImpl userService;

    private final fileServiceImpl fileService;

    private final credentialServiceImpl credentialService;

    private final EncryptionService encryptionService;

    public homeController(noteServiceImpl noteService, userServiceImpl userService, fileServiceImpl fileService, credentialServiceImpl credentialService, EncryptionService encryptionService) {
        this.noteService = noteService;
        this.userService = userService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public String homePage(Model model, Authentication authentication){

        User user = userService.getUser(authentication.getName());
        model.addAttribute("clientNotes",noteService.getAllNote(user.getUserId()));
        model.addAttribute("listOfFiles",fileService.listAll(user.getUserId()));
        model.addAttribute("listOfCredentials",credentialService.getAllCredential(user.getUserId()));
        model.addAttribute("encryptionService", this.encryptionService);
        return "home";
    }

    @PostMapping("/logout")
    public String backLogin(Model model){
        model.addAttribute("logOutStatus",true);
        return "login";
    }

}
