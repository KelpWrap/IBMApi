package com.whiteboard.whiteboard.API.controllers;

import java.util.ArrayList;
import java.util.List;
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
import com.whiteboard.whiteboard.CatalogPost;

@Component
@Path("/post")
@Produces("application/json")
public class CatalogPostController {
  @GET
  @Path("/{post-id}") 
  public Response getPost(@PathParam("post-id") String postId){return null;}

  @GET
  public List<CatalogPost> getPosts(@QueryParam("meta-data") List<String> postMetadata) {return null;}

  @PUT
  @Path("/{post-id}")
  public CatalogPost replacePost(@PathParam("post-id") String postId, CatalogPost post) {return null;}
  @PATCH
  @Path("/{post-id}")
  public CatalogPost updatePost(@PathParam("post-id") String postId, CatalogPost post) {return null;}
  @DELETE
  @Path("/{post-id}")
  public Response deletePost(@PathParam("post-id") String postId) {return null;}
}