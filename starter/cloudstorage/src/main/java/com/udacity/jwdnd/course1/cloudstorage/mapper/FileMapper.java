package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("insert into FILES(fileName, contentType, fileSize, userId, fileData) VALUES" +
            "(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(File file);

    @Select("select * from FILES WHERE UserId = #{userId}")
    List<File> list(Integer userId);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File findById(Integer fileId);

    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    File findOneFile(String filename);

    @Delete("delete from FILES where fileId = #{fileId}")
    void delete(Integer fileId);

}
