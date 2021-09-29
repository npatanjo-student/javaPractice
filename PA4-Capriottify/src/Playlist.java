/*
 * AUTHOR: Nate Patanjo
 * FILE: Playlist.java
 * ASSIGNMENT: Programming Assignment 4 - Capriottify
 * COURSE: CSC 210; Spring 2021
 * PURPOSE: this class creates a playlist object.
 * this is user specific and stored as an ArrayList.
 * A group of songs can be added initially or just one.
 * 
 * USAGE:
 * Ex. Input:  String Playlist 1
 * Ex. Object: songs List --> {}
 * 
 * Ex. Input (addSong): Come Together
 * Ex. Object: songs List --> {Come Together The Beatles}
 */
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {
	
	private String plyName;
	private List<Song> songs;
	
	/**
	 * Instantiates a new playlist. Without pre-exsisting
	 * songs
	 *
	 * @param name
	 * this is the name of the new playlist
	 */
	public Playlist(String name) {
		this.plyName = name;
		this.songs = new ArrayList<Song>();
	}
	
	/**
	 * Instantiates a new playlist. With pre-exsisting
	 * songs
	 *
	 * @param name the name
	 * @param contents the contents
	 */
	public Playlist(String name, List<Song> contents) {
		this.plyName = name;
		this.songs = contents;
	}
	
	/**
	 * Gets the name of the playlist.
	 *
	 * @return plyName
	 * the name of the playlist
	 */
	public String getName() {
		return plyName;
	}
	
	/**
	 * Adds the song so the playlist.
	 *
	 * @param song
	 * the song that will be added to the playlist
	 */
	public void addSong(Song song) {
		songs.add(song);
	}
	
	/**
	 * Play. Play the songs in the playlist in order
	 */
	public void play() {
		
		for (int i = 0; i < songs.size(); i++) {
			songs.get(i).play();
		}
	}
	
	/**
	 * Shuffle. shuffles the songs in the playlist so they
	 * are not in order
	 */
	public void shuffle() {
		Collections.shuffle(this.songs);
	}
	
	/**
	 * Removes the song. removes the passed song from the 
	 * playlist
	 *
	 * @param song
	 * the song that needs to be removed
	 */
	public void removeSong(Song song) {
		songs.remove(song);
	}

}
