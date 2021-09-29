/**
 * AUTHOR: Nate Patanjo
 * COURSE: CSC 210
 * FILE: User.java   Feb 17, 2021
 * ASSIGNMENT: PA4-Capriottify 
 * PURPOSE: This class creates the User Object that stores
 * information about every user created. It stores the Users
 * name, password, and saved playlists
 * 
 * USAGE:
 * Ex. Input (logging in): name --> Nate
 * 			  			   password --> xyx
 * Ex. Input (adding playlist): Playlist1
 * 
 * Ex. Object: Nate 
 *             xyx
 *             playlists {Playlist1}
 * 
 * 
 */

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String name;
	private String currentPassword;
	private List<Playlist> playlists;
	
	/**
	 * Instantiates a new user. Constructs a new
	 * User Object
	 *
	 * @param name
	 * this is the user name
	 * 
	 * @param password
	 * this is the user password
	 */
	public User(String name, String password) {
		this.name = name;
		this.currentPassword = password;
		this.playlists = new ArrayList<Playlist>();
	}
	
	/**
	 * Gets the name of the user
	 *
	 * @return name
	 * the users name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Attempt login. this method attempts to login 
	 * with the current password. If the password is 
	 * correct it returns true, else it returns false
	 *
	 * @param password
	 * this is the password that is checked
	 * 
	 * @return true, if successful
	 * this is if it is the right password
	 * false if it is not the correct password
	 */
	public boolean attemptLogin(String password) {
		if (currentPassword.equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Adds the playlist. this adds a new playlist
	 * to the playlists list
	 *
	 * @param newPlayList
	 * the new play list that will get added
	 */
	public void addPlaylist(Playlist newPlayList) {
		this.playlists.add(newPlayList);
	}
	
	/**
	 * Gets the playlists.
	 *
	 * @return the playlists
	 * gets the list of playlists
	 */
	public List<Playlist> getPlaylists() {
		return this.playlists;
	}
	
	/**
	 * Select playlist and play the selected playlist
	 *
	 * @param name
	 * this is the name of the playlist that will
	 * get played
	 */
	public void selectPlaylist(String name) {
		for (int i = 0; i < playlists.size(); i++) {
			if (playlists.get(i).getName().equals(name)) {
				playlists.get(i).play();
			}
		}
	}
	
	/**
	 * To string. this is the playlist print
	 *
	 * @return the string
	 * the string that will get printed when you print the
	 * playlist
	 */
	public String toString() {
		return name + ", " + playlists.size() + " playlists";
		
	}
	
	

}
