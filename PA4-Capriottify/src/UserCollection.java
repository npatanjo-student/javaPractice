/**
 * AUTHOR: Nate Patanjo
 * COURSE: CSC 210
 * FILE: UserCollection.java   Feb 17, 2021
 * ASSIGNMENT: PA4-Capriottify 
 * PURPOSE: This keeps track of all the different users
 * made in the program.
 * 
 * USAGE:
 * Ex UserCollection Object: {Nate, Jack, Jill} 
 */

import java.util.ArrayList;
import java.util.List;
public class UserCollection {
	
	private List<User> userList;
	/**
	 * Instantiates a new user collection.
	 */
	public UserCollection() {
		this.userList = new ArrayList<User>();
	}
	
	/**
	 * User exists. This checks to see if the inputed 
	 * User exists
	 *
	 * @param username the username
	 * this is the inputed username
	 * @return true, if successful
	 * false if user does not exist
	 */
	public boolean userExists(String username) {
		for(int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getName().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Login. This returns the user if the login is successful.
	 * if the login is not successful it return null
	 *
	 * @param username
	 * this is the attempted username
	 * @param password
	 * this is the attempted password
	 * @return the user
	 * this is the user logging in
	 * @return null
	 * returns null if the login is not a success
	 */
	public User login(String username, String password) {
		if (userExists(username)) {
			for(int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getName().equals(username)) {
					if(userList.get(i).attemptLogin(password)) {
						return userList.get(i);
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Adds the user. This adds a new user to the
	 * collection
	 *
	 * @param add
	 * this is the new user being added
	 */
	public void addUser(User add) {
		userList.add(add);
	}
	
	/**
	 * To string. this prints the correct thing
	 * when the object needs to get printed. 
	 *
	 * @return the string
	 * the correct printing
	 */
	public String toString() {
		String retStr = "{ ";
		for (int i = 0; i < userList.size(); i++) {
			String userName = userList.get(i).getName();
			int numberPly = userList.get(i).getPlaylists().size();
			retStr += userName + ": " + userName + ", " + numberPly +
					" playlists, ";
		}
		return retStr + "}";
	}

}
