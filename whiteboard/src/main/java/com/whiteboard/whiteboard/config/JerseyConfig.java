package com.whiteboard.whiteboard.config;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import com.whiteboard.whiteboard.API.controllers.CatalogPostController;
import com.whiteboard.whiteboard.API.controllers.CatalogUserController;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/whiteboard")
public class JerseyConfig extends ResourceConfig {
  @PostConstruct
  public void init() {
    configEndPoints();
  }
 
  private void configEndPoints(){    
    register(CatalogPostController.class);
    register(CatalogUserController.class);
  }
}