package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO Credentials(url, username, key, password, userId) VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredential(Credential credential);

    @Select("SELECT * FROM Credentials WHERE userId = #{userId}")
    List<Credential> getCredentialForUser(Integer userId);

    @Update("update CREDENTIALS SET url=#{url}, username =#{username}, key =#{key}, password =#{password} where credentialId =#{credentialId}")
    int updateCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    void deleteCredential(Integer credentialId);
}
