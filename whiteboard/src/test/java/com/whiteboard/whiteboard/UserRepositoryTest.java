package com.whiteboard.whiteboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import com.whiteboard.whiteboard.dbAdapter.repositories.UserRepository;
import com.whiteboard.whiteboard.elements.CatalogUser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {
    @Autowired UserRepository userRepository;

    @BeforeEach
    public void beforeTest(){
        try{
            userRepository.initDb();
        } 
        catch (Exception e){
        }
    }

    @AfterEach
    public void afterTest(){
        try{
            userRepository.clearTable();
        } 
        catch (Exception e){
        }
    }


    @Test
    public void addPostTest(){
        try {
            CatalogUser testUser = createTestUser(1);
            userRepository.addObject(testUser);
            List<CatalogUser> postList = userRepository.getObjects();
            assertEquals(postList.size() == 1, true);
        } catch (Exception e){
            System.out.println(e.toString());
            assertEquals(true, false);
        }
    }

    @Test
    public void lookUpPostByIdTest(){
        try {
            CatalogUser testUser = createTestUser(1);
            userRepository.addObject(testUser);
            List<CatalogUser> postList = userRepository.getObjectsById(testUser.getUserId());
            assertEquals(postList.size() == 1, true);
        } catch (Exception e){
            System.out.println(e.toString());
            assertEquals(true, false);
        }
    }

    @Test
    public void updatePostTest(){
        CatalogUser testUser = createTestUser(1);
        CatalogUser testUserUpdated = createTestUser(1);
        testUserUpdated.setUserAlias("updated Alias");
        try {
            userRepository.addObject(testUser);
            userRepository.replaceObject(testUser, testUserUpdated);
            List<CatalogUser> postList = userRepository.getObjectsById(testUserUpdated.getUserId());
            assertEquals("updated Alias", postList.get(0).getUserAlias());
        } catch (Exception e){
            assertEquals(true, false);
        }

    }

    @Test
    public void clearTableTest(){
        try{
            CatalogUser testUser = createTestUser(2);
            userRepository.addObject(testUser);
        } catch (Exception e){
        }
        try {
            userRepository.clearTable();
            List<CatalogUser> postList = userRepository.getObjects();
            assertEquals(postList.size() == 0, true);
        } catch (Exception f) {
            assertEquals(true, false);
        }
    }

    @Test
    public void removeEntryTest(){
        CatalogUser testUser = createTestUser(1);
        CatalogUser testUser2 = createTestUser(2);
        CatalogUser testUser3 = createTestUser(3);
        try{
            userRepository.addObject(testUser);
            userRepository.addObject(testUser2);
            userRepository.addObject(testUser3);
        } catch (Exception e){
        }
        try {
            userRepository.deleteObject(testUser);
            List<CatalogUser> postList = userRepository.getObjects();
            assertEquals(postList.size() == 2, true);
        } catch (Exception f) {
            assertEquals(true, false);
        }
    }



    private CatalogUser createTestUser(int userId) {
        CatalogUser user = new CatalogUser();
        user.setUserId(Integer.toString(userId));
        user.setUserName("test" + Integer.toString(userId));
        user.setUserAlias("testAlias" + Integer.toString(userId));
        user.setUserHashedPassword("testPassword" + Integer.toString(userId));
        user.setUserType("1");
        return user;
    }


}
