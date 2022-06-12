package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.fileServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.services.userServiceImpl;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("listOfFiles",fileService.listAll(userId));
        return "home";

    }

    @GetMapping("{fileId}/delete")
    public String deleteFile(@PathVariable Integer fileId, Model model){
        try{
            fileService.delete(fileId);
            model.addAttribute("successMessage",true);
            return "result";
        }catch (Exception e){
            model.addAttribute("successMessage",false);
            return "result";
        }
    }

    @RequestMapping(value="{fileId}/view", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    ResponseEntity<Resource> downloadFile(@PathVariable Integer fileId){
        try {
            File file = fileService.findById(fileId);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                    .body(new ByteArrayResource(file.getFileData()));

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

}
