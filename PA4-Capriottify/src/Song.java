/*
 * AUTHOR: Nate Patanjo
 * FILE: Song.java
 * ASSIGNMENT: Programming Assignment 4 - Capriottify
 * COURSE: CSC 210; Spring 2021
 * PURPOSE: This class creates an object -Song. the Song object 
 * consists of two Strings: title and artist.
 * 
 * USAGE:
 * Ex. Input:  title --> "Come Together" 
 * 		  	   artist -> "The Beatles"
 * 
 * Ex. Object: Come Together
 * 			   The Beatles
 * 
 */
public class Song {
	
	private String title;
	private String artist;
	private int playedCounter;
	
	/*
	 * constructs a new instance of the Song class with
	 * the specified song title, artist, and play counter.
	 * 
	 * @param title
	 * this is the title of the song.
	 * 
	 * @param artist
	 * this is the artist of the title song
	 */
	public Song(String title, String artist) {
		this.title = title;
		this.artist = artist;
		this.playedCounter = 0;
	}
	
	/*
	 * a getter for the title of the song. 
	 * 
	 * @return this.title
	 * this returns the current instance of the song title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/*
	 * a getter for the artist of the song.
	 * 
	 * @return this.artist
	 * this returns the current instance of the song artist
	 */
	public String getArtist() {
		return this.artist;
	}
	
	/*
	 * a getter for the number of times the song was played
	 * 
	 * @return this.playedCounter
	 * this returns a counter for the number of times this instance of
	 * song was played
	 */
	public int getTimesPlayed() {
		return this.playedCounter;
	}
	
	/*
	 * this plays the song by printing out the song description and
	 * also adding to the counter
	 */
	public void play() {
		System.out.println(title + " by " + artist + ", " + playedCounter + " play(s)");
		this.playedCounter++;
	}
	
	/*
	 * this is a toString method that returns the description of the song
	 * 
	 * @return title + artist + ", " + playedCounter + " play(s)"
	 * this is the description of the song
	 */
	public String toString() {
		return title + artist + ", " + playedCounter + " play(s)"; 
		
	}
	
}
