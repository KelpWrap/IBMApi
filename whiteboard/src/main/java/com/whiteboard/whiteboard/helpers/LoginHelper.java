package com.whiteboard.whiteboard.helpers;

import java.util.List;
import java.util.Scanner;




public class LoginHelper {
    public LoginHelper(){}
    public List<User> logIn(Scanner input, DbConnector dbConnector){
        dbConnector.connect();
        System.out.println("Enter your username");
		while(!input.hasNext());
		String username = "";
		if (input.hasNext()) username = input.nextLine();
		System.out.println("Enter your password");
		while(!input.hasNext());
		String password = "";
		if (input.hasNext()) password = input.nextLine();
		User user = new User(username, password);
		return dbConnector.getUserDataFromDb(user);
    }
	public void createNewUser(Scanner input, DbConnector dbConnector){
        dbConnector.connect();
        System.out.println("Enter the new Users username");
		while(!input.hasNext());
		String username = "";
		if (input.hasNext()) username = input.nextLine();
		System.out.println("Enter the new Users Alias");
		while(!input.hasNext());
		String alias = "";
		if (input.hasNext()) alias = input.nextLine();
		User user = new User(username, alias, 1);
		dbConnector.addUserToDb(user);
		System.out.println("New User added! Please log in with the new username and change your password");
    }
}
