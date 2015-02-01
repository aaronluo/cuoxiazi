package com.innovaee.eorder.service;

import com.innovaee.eorder.model.User;

public interface UserService {

    public User getUserById(Integer userId);
    
    public User getUserByCellphone(String cellphone);
}