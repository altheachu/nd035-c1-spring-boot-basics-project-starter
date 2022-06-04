package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl{

    @Autowired
    private UserMapper userMapper;
    //private final HashService hashService;
/*
    public userServiceImpl(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }*/

    public Boolean addNewUser(User user) {
        Boolean result = false;
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
