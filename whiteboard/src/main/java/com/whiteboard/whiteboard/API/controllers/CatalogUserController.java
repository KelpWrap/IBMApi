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

import com.whiteboard.whiteboard.CatalogMetaDatum;
import com.whiteboard.whiteboard.CatalogUser;
import com.whiteboard.whiteboard.API.util.CatalogUserTestGenerator;

@Component
@Path("/users")
@Produces("application/json")
public class CatalogUserController {
  @GET
  @Path("/{user-id}") 
  public CatalogUser getUser(@PathParam("user-id") String userId){
    return CatalogUserTestGenerator.generateTestUser(userId);
  }

  @GET
  public List<CatalogUser> getUsers(@QueryParam("meta-data") List<String> userMetadata) {
    List<CatalogMetaDatum> metaData = userMetadata.stream().map(c -> {
      String[] nameValue = c.split("=");
      CatalogMetaDatum item = new CatalogMetaDatum();
      item.setName(nameValue[0]);
      item.setValue(nameValue[1]);
      return item;
    }).collect(Collectors.toList());

    metaData.stream().forEach(c-> System.out.println(c.toString()));

    List<CatalogUser> returnImages = CatalogUserTestGenerator.generateTestUsers();;
    return returnImages;
  }

  @PUT
  @Path("/{user-id}")
  public CatalogUser replaceUser(@PathParam("user-id") String userId, CatalogUser user) {
    System.out.println(user.toString());
    user.setUserId(user.getUserId()+" replaced");
    return user;
  }
  @PATCH
  @Path("/{user-id}")
  public CatalogUser updateUser(@PathParam("user-id") String userId, CatalogUser user) {
    System.out.println(user.toString());
    user.setUserId(user.getUserId()+" updated");
    CatalogMetaDatum val = user.getMetaData().get(0);
    val.setName("patch");
    val.setValue("patch-value");
    List<CatalogMetaDatum> theList = new ArrayList<CatalogMetaDatum>();
    theList.add(val);
    user.setMetaData(theList);
    return user;
  }
  @DELETE
  @Path("/{user-id}")
  public Response deleteUser(@PathParam("user-id") String userId) {return null;}
}
