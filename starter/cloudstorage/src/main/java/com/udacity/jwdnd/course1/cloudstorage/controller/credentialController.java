package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.credentialServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.services.userServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("/credentials")
public class credentialController {

    private userServiceImpl userService;
    private credentialServiceImpl credentialService;
    private EncryptionService encryptionService;

    public credentialController(userServiceImpl userService, credentialServiceImpl credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping("/{credentialId}/delete")
    public String deleteCredential(@PathVariable Integer credentialId, Model model){
        try{
            credentialService.deleteNote(credentialId);
            model.addAttribute("successMessage",true);
        }catch(Exception e){
            model.addAttribute("successMessage",false);
        }
        return "result";
    }

    @PostMapping
    public String addOrUpdateCredential(Authentication authentication, Credential credential, Model model){

        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();

        if(credential.getCredentialId()!=null){
            credentialService.updateCredential(credential);
        }else{
            credentialService.insertCredential(credential,userId);
        }
        model.addAttribute("successMessage",true);
        return "result";
    }

}
