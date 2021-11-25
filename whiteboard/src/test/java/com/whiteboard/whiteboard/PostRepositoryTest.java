package com.whiteboard.whiteboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.whiteboard.whiteboard.dbAdapter.DbAdapter;
import com.whiteboard.whiteboard.dbAdapter.repositories.PostRepository;
import com.whiteboard.whiteboard.elements.CatalogPost;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PostRepositoryTest {
    @Autowired DbAdapter dbAdapter;
    @Autowired PostRepository postRepository;

    @BeforeEach
    public void beforeTest(){
        try{
            dbAdapter.initDb();
        } 
        catch (Exception e){
            System.out.println(e.toString());
            assertEquals(true, false);
        }
    }

    @AfterEach
    public void afterTest(){
        try{
            postRepository.clearTable();
        } 
        catch (Exception e){
            System.out.println(e.toString());
            assertEquals(true, false);
        }
    }

    
    @Test
    public void addPostTest(){
        try {
            CatalogPost testPost = createTestPost(1, 1);
            postRepository.addObject(testPost);
            List<CatalogPost> postList = postRepository.getObjects();
            assertEquals(postList.size() == 1, true);
        } catch (Exception e){
            System.out.println(e.toString());
            assertEquals(true, false);
        }
    }

    @Test
    public void lookUpPostByIdTest(){
        try {
            CatalogPost testPost = createTestPost(1, 1);
            postRepository.addObject(testPost);
            List<CatalogPost> postList = postRepository.getObjectsById(testPost.getPostId());
            assertEquals(postList.size() == 1, true);
        } catch (Exception e){
            System.out.println(e.toString());
            assertEquals(true, false);
        }
    }

    @Test
    public void updatePostTest(){
        CatalogPost testPost = createTestPost(1, 1);
        CatalogPost testPostUpdated = createTestPost(1, 1);
        testPostUpdated.setPostBody("updated Body Test");
        try {
            postRepository.addObject(testPost);
            postRepository.replaceObject(testPost, testPostUpdated);
            List<CatalogPost> postList = postRepository.getObjectsById(testPostUpdated.getPostId());
            assertEquals("updated Body Test", postList.get(0).getPostBody());
        } catch (Exception e){
            assertEquals(true, false);
        }

    }

    @Test
    public void clearTableTest(){
        try{
            CatalogPost testPost = createTestPost(2, 2);
            postRepository.addObject(testPost);
        } catch (Exception e){
        }
        try {
            postRepository.clearTable();
            List<CatalogPost> postList = postRepository.getObjects();
            assertEquals(postList.size() == 0, true);
        } catch (Exception f) {
            assertEquals(true, false);
        }
    }

    @Test
    public void removeEntryTest(){
        CatalogPost testPost = createTestPost(1, 3);
        CatalogPost testPost2 = createTestPost(2, 2);
        CatalogPost testPost3 = createTestPost(3, 2);
        try{
            postRepository.addObject(testPost);
            postRepository.addObject(testPost2);
            postRepository.addObject(testPost3);
        } catch (Exception e){
        }
        try {
            postRepository.deleteObject(testPost);
            List<CatalogPost> postList = postRepository.getObjects();
            assertEquals(postList.size() == 2, true);
        } catch (Exception f) {
            assertEquals(true, false);
        }
    }



    private CatalogPost createTestPost(int i, int userId) {
        CatalogPost post = new CatalogPost();
        post.setPostId(Integer.toString(i));
        post.setPostUserid("user" + Integer.toString(userId));
        post.setPostBody("testBody " + Integer.toString(i));
        return post;
    }


}
