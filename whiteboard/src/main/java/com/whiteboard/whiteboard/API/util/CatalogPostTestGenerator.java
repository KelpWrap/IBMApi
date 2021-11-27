package com.whiteboard.whiteboard.API.util;

import java.util.ArrayList;
import java.util.List;

import com.whiteboard.whiteboard.elements.CatalogMetadata;
import com.whiteboard.whiteboard.elements.CatalogPost;

public class CatalogPostTestGenerator {
    public static List<CatalogPost> generateTestPosts()
    {
      List<CatalogPost> posts = new ArrayList<CatalogPost>();
      for(int i = 0; i < 20; i++) {
        CatalogPost post = new CatalogPost();
        List<CatalogMetadata> metaData = new ArrayList<CatalogMetadata>();
        for(int k = 0; k < 10; k++) {
          CatalogMetadata item = new CatalogMetadata();
          item.setName("name"+k);
          item.setValue("value" + k);
          metaData.add(item);
        }
        post.setPostId(i);
        post.setMetaData(metaData);
        post.setPostBody("textBody"+i);
        post.setPostUserid("postUserid"+i);
        posts.add(post);
      }
      return posts;
    }

    public static CatalogPost generateTestPost(int postId){
        CatalogPost post = new CatalogPost();
        List<CatalogMetadata> metaData = new ArrayList<CatalogMetadata>();
        for(int k = 0; k < 10; k++) {
          CatalogMetadata item = new CatalogMetadata();
          item.setName("name"+k);
          item.setValue("value" + k);
          metaData.add(item);
        }
        post.setPostId(postId);
        post.setMetaData(metaData);
        post.setPostBody("textBody");
        post.setPostUserid("postUserid");
        return post;
    }
}
