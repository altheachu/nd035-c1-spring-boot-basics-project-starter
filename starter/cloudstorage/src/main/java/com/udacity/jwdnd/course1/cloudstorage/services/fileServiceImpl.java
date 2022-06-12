package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class fileServiceImpl {

    private FileMapper fileMapper;

    public fileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<File> listAll(Integer userId){
        return fileMapper.list(userId);
    }

    public Integer upload(File file, MultipartFile multipartFile) throws Exception{

        file.setFileName(multipartFile.getOriginalFilename());
        file.setContentType(multipartFile.getContentType());
        file.setFileSize(String.valueOf(multipartFile.getSize()));
        file.setFileData(multipartFile.getBytes());
        return fileMapper.addFile(file);
    }

    public void delete(Integer fileId){
        fileMapper.delete(fileId);
    }

    public File findById(Integer fileId){
        return fileMapper.findById(fileId);
    }


}
