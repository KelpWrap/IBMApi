package com.whiteboard.whiteboard.helpers;

import java.util.List;
import java.util.Scanner;

import com.whiteboard.whiteboard.dbAdapter.DbAdapter;



public class CatalogPostHelper {
    public CatalogPostHelper() {
    }

    public void addContent(Scanner input, DbAdapter dbConnector, String alias) {
        System.out.println("Enter your note! Finish with enter");
        while (!input.hasNext())
            ;
        String body = "";
        if (input.hasNext())
            body = input.nextLine();
        Content content = new Content(alias, body);
        dbConnector.addContentToDb(content);
    }

    public void deleteContent(Scanner userInput, DbAdapter dbConnector, User user) {
        System.out.println("Type Id of content to delete");
        List<Content> deletableContent;
        if (user.getUserLevel() < 2) {
            deletableContent = dbConnector.getContentByUser(user);
        } else {
            deletableContent = dbConnector.getAllContent();
        }
        for (Content content : deletableContent) {
            System.out.println(content.toString());
        }
        while (!userInput.hasNext())
            ;
        if (userInput.hasNext()) {
            int contentId = Integer.parseInt(userInput.nextLine());
            if (deletableContent.stream().anyMatch(c -> c.getId() == contentId)) {
                dbConnector.deleteContent(contentId);
            }
            else {
                System.out.println("No deletable content with this Id found.");
            }
        }
    }

}
