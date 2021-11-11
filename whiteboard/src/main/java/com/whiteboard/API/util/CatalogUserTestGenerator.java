package com.whiteboard.API.util;

import java.util.ArrayList;
import java.util.List;

import com.whiteboard.SecurityEnum;
import com.whiteboard.elements.CatalogMetadata;
import com.whiteboard.elements.CatalogUser;

public class CatalogUserTestGenerator {
    public static List<CatalogUser> generateTestUsers()
    {
      List<CatalogUser> users = new ArrayList<CatalogUser>();
      for(int i = 0; i < 20; i++) {
        CatalogUser user = new CatalogUser();
        List<CatalogMetadata> metaData = new ArrayList<CatalogMetadata>();
        for(int k = 0; k < 10; k++) {
          CatalogMetadata item = new CatalogMetadata();
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
        List<CatalogMetadata> metaData = new ArrayList<CatalogMetadata>();
        for(int k = 0; k < 10; k++) {
          CatalogMetadata item = new CatalogMetadata();
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
