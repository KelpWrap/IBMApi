package com.whiteboard.whiteboard.API.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;

import com.whiteboard.whiteboard.CatalogMetaDataPost;
import com.whiteboard.whiteboard.CatalogPost;
import com.whiteboard.whiteboard.API.util.CatalogPostTestGenerator;

@Component
@Path("/post")
@Produces("application/json")
public class CatalogPostController {
  @GET
  @Path("/{post-id}") 
  public CatalogPost getPost(@PathParam("post-id") String postId){
    return CatalogPostTestGenerator.generateTestPost(postId);
  }

  @GET
  public List<CatalogPost> getPosts(@QueryParam("meta-data") List<String> postMetadata) {
    List<CatalogMetaDataPost> metaData = postMetadata.stream().map(c -> {
      String[] nameValue = c.split("=");
      CatalogMetaDataPost item = new CatalogMetaDataPost();
      item.setName(nameValue[0]);
      item.setValue(nameValue[1]);
      return item;
    }).collect(Collectors.toList());

    metaData.stream().forEach(c-> System.out.println(c.toString()));

    List<CatalogPost> returnImages = CatalogPostTestGenerator.generateTestPosts();;
    return returnImages;
  }

  @PUT
  @Path("/{post-id}")
  public CatalogPost replacePost(@PathParam("post-id") String postId, CatalogPost post) {
    System.out.println(post.toString());
    post.setPostBody(post.getPostBody()+" replaced");
    return post;
  }
  
  @PATCH
  @Path("/{post-id}")
  public CatalogPost updatePost(@PathParam("post-id") String postId, CatalogPost post) {
    System.out.println(post.toString());
    post.setPostBody(post.getPostBody()+" updated");
    CatalogMetaDataPost val = post.getMetaData().get(0);
    val.setName("patch");
    val.setValue("patch-value");
    List<CatalogMetaDataPost> theList = new ArrayList<CatalogMetaDataPost>();
    theList.add(val);
    post.setMetaData(theList);
    return post;
  }

  @DELETE
  @Path("/{post-id}")
  public Response deletePost(@PathParam("post-id") String postId) {return null;}
}