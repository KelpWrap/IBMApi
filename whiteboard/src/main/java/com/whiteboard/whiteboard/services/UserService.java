package com.whiteboard.whiteboard.services;


import com.whiteboard.whiteboard.SecurityEnum;
import com.whiteboard.whiteboard.dbAdapter.repositories.UserRepository;
import com.whiteboard.whiteboard.elements.CatalogUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public CatalogUser addNewUser(String username, String hashedPw) {
        CatalogUser user = new CatalogUser();
        user.setUserName(username);
        user.setUserHashedPassword(hashedPw);
        user.setUserType(SecurityEnum.USER.toString());
        user.setUserAlias(username);
        try {
            userRepository.addObject(user);
        } catch (Exception e){
            return null;
        }
        return user;

    }
}
