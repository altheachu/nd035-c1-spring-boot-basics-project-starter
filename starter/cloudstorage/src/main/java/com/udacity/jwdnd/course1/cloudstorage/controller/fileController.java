package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class fileController {

    @PostMapping("/fileUpload")
    public String handFileUploade(@RequestParam("fileupload") MultipartFile fileUpload, Model model) throws IOException {
        InputStream is = fileUpload.getInputStream();
        return "";
    }

}
