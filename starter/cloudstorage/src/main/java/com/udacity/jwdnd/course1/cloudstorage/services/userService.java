package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.User;

public interface userService {

    //新增用戶的方法
    Boolean addNewUser(User user);

    //查詢用戶資料的方法
    User getUser(String username);

}
