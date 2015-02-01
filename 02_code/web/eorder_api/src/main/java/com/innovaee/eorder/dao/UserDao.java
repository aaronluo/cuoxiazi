package com.innovaee.eorder.dao;

import com.innovaee.eorder.entity.User;

public interface UserDao extends BaseDao<User> {
    
    public User getUserByCellphone(String cellphone);

}
