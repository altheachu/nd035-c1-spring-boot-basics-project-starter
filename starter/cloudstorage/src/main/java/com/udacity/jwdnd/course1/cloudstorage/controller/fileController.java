package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.fileServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.services.userServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/files")
public class fileController {

    private userServiceImpl userService;
    private fileServiceImpl fileService;

    public fileController(userServiceImpl userService, fileServiceImpl fileService) {

        this.userService = userService;
        this.fileService = fileService;

    }

    @PostMapping("/fileUpload")
    public String handFileUploade(@RequestParam("fileUpload") MultipartFile multipartFile,
                                  File file, Authentication authentication, Model model) throws Exception {
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserId();
        file.setUserId(userId);
        fileService.upload(file,multipartFile);
        return "home";

    }

}
