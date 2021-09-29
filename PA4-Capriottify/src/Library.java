/*
 * AUTHOR: Nate Patanjo
 * FILE: Library.java
 * ASSIGNMENT: Programming Assignment 4 - Capriottify
 * COURSE: CSC 210; Spring 2021
 * PURPOSE: this class creates a library of the users songs. 
 * it does this using a treemap to keep everything in order.\
 * 
 * USAGE:
 * Ex. Input: Song Object containing -> Come Together
 * 								 		The Beatles
 * 			  Song Object containing -> This Time Tomorrow
 * 								 		The Kinks
 * 
 * Creates a TreeMap with title as key and Song Object
 * as value.
 * 
 * Ex. Library Object (TreeMap): 
 * 			  {Come Together | Come Together The Beatles
 * 			   This Time Tomorrow | This Time Tomorrow The Kinks}  
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Library {
	
	private Map<String, Song> songs;
	
	/*
	 * Constructs a new instance of the Library Class
	 */
	public Library() {
		this.songs = new TreeMap<String, Song>();
	}
	
	/*
	 * this returns the Song associated with the inputed
	 * title, if it exists
	 * 
	 * @return songs.get(title)
	 * this is the Song value in the map associated with
	 * the string title inputed
	 * 
	 * @return null
	 * this is a null return if the title does not exist
	 * in the map
	 * 
	 */
	public Song getSong(String title) {
		if (songs.containsKey(title)) {
			return songs.get(title);
		} else {
			return null;
		}
	}
	
	/*
	 * this method returns a list of all Songs in the
	 * Map
	 * 
	 * @return new ArrayList<>(songs.values())
	 * this is an ArrayList containing all the Song objects
	 * in the Map
	 */
	public List<Song> getAllSongs() {
		return new ArrayList<>(songs.values());
	}
	
	/*
	 * This method adds a song to the Library Object/
	 * treeMap
	 * 
	 */
	public void addSong(Song song) {
		 songs.put(song.getTitle(), song);
	}
	
	/*
	 * this method removes a song from the Library Object/
	 * treeMap
	 */
	public void removeSong(Song song) {
		songs.remove(song.getTitle());
	}
	
	/*
	 * this method creates a string of the library in alphabetical
	 * order. this is made easy by the use of a treeMap
	 * 
	 * @return retStr
	 * this is a string already spaced and indented containing
	 * the library as a string
	 */
	public String toString() {
		String retStr = "";
		for (String line : songs.keySet()) {
			retStr += line + " by " + songs.get(line).getArtist() + 
					", " + songs.get(line).getTimesPlayed() + " play(s)\n";
		}
		return retStr;
	}
}
