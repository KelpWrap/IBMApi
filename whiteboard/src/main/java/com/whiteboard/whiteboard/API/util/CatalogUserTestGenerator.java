package com.whiteboard.whiteboard.API.util;

import java.util.ArrayList;
import java.util.List;

import com.whiteboard.whiteboard.CatalogMetaDatum;
import com.whiteboard.whiteboard.CatalogUser;
import com.whiteboard.whiteboard.SecurityEnum;

public class CatalogUserTestGenerator {
    public static List<CatalogUser> generateTestUsers()
    {
      List<CatalogUser> users = new ArrayList<CatalogUser>();
      for(int i = 0; i < 20; i++) {
        CatalogUser user = new CatalogUser();
        List<CatalogMetaDatum> metaData = new ArrayList<CatalogMetaDatum>();
        for(int k = 0; k < 10; k++) {
          CatalogMetaDatum item = new CatalogMetaDatum();
          item.setName("name"+k);
          item.setValue("value" + k);
          metaData.add(item);
        }
        user.setUserId(Integer.toString(i));
        user.setMetaData(metaData);
        user.setUserAlias(Integer.toString(i));
        user.setUserHashedPassword("userHashedPassword"+i);
        user.setUserType(SecurityEnum.USER.toString());
        users.add(user);
      }
      return users;
    }

    public static CatalogUser generateTestUser(String userId){
        CatalogUser user = new CatalogUser();
        List<CatalogMetaDatum> metaData = new ArrayList<CatalogMetaDatum>();
        for(int k = 0; k < 10; k++) {
          CatalogMetaDatum item = new CatalogMetaDatum();
          item.setName("name"+k);
          item.setValue("value" + k);
          metaData.add(item);
        }
        user.setUserId(userId);
        user.setMetaData(metaData);
        user.setUserAlias(userId);
        user.setUserHashedPassword("userHashedPassword"+userId);
        user.setUserType(SecurityEnum.USER.toString());

        return user;
    }
}
