package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class userServiceImpl{

    private UserMapper userMapper;
    private final HashService hashService;

    public userServiceImpl(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public Boolean addNewUser(User user) {
        Boolean result = false;
        //密碼加密
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        user.setSalt(encodedSalt);
        user.setPassword(hashPassword);
        int i = userMapper.addNewUser(user);
        if(i > 0){
            result = true;
        }
        return result;
    }

    public User getUser(String username) {
        return userMapper.selectByUsername(username);
    }
}
