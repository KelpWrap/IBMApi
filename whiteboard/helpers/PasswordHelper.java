package com.whiteboard.whiteboard.helpers;

import java.util.List;
import java.util.Scanner;



public class PasswordHelper {
    public PasswordHelper(){}
    public void changePassword(Scanner input, String username){
        dbConnector.connect();
		System.out.println("Enter your current password");
		while(!input.hasNext());
		String password = "";
		if (input.hasNext()) password = input.nextLine();
		User user = new User(username, password);
        List<User> userList = dbConnector.getUserDataFromDb(user);
		if(userList.size() != 1){
            System.out.println("User not Found");
        } else {
            System.out.println("Enter new password");
            while(!input.hasNext());
            String newPassword = "";
            if (input.hasNext()) password = input.nextLine();
            User updatedUser = userList.get(0);
            updatedUser.setPassword(newPassword);
            dbConnector.updateUser(updatedUser);
            System.out.println("Password has been changed");
        }
        dbConnector.disconnect();
        return;
    }
    
}
