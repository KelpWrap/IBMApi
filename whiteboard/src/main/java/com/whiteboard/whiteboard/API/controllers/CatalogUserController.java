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
import com.whiteboard.whiteboard.CatalogUser;

@Component
@Path("/user")
@Produces("application/json")
public class CatalogUserController {
  @GET
  @Path("/{user-id}") 
  public Response getUser(@PathParam("user-id") String userId){return null;}

  @GET
  public List<CatalogUser> getUsers(@QueryParam("meta-data") List<String> userMetadata) {return null;}

  @PUT
  @Path("/{user-id}")
  public CatalogUser replaceUser(@PathParam("user-id") String userId, CatalogUser user) {return null;}
  @PATCH
  @Path("/{user-id}")
  public CatalogUser updateUser(@PathParam("user-id") String userId, CatalogUser user) {return null;}
  @DELETE
  @Path("/{user-id}")
  public Response deleteUser(@PathParam("user-id") String userId) {return null;}
}
