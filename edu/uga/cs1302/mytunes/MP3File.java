package edu.uga.cs1302.mytunes;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFileFormat;

public class MP3File {
    

    public String pathname, author, album, title;
    public int year;
    
    public MP3File() {
	pathname = "";
	author = "";
	year = 1900;
	album = "";
	title = "";
    }
    
    public MP3File(String pathname) {
	try {
	    FileInputStream fis        = new FileInputStream( pathname );
	    BufferedInputStream bis    = new BufferedInputStream(fis);
	    AudioFileFormat mpegFormat = AudioSystem.getAudioFileFormat(bis);
	    Map properties             = mpegFormat.properties();

	    if(((String) properties.get( "author" )).equals(null))
		author = "";
	    else
		author = ((String) properties.get( "author" ));
	                
	    if(((String) properties.get( "album" )).equals(null) )
		album = "";
	    else
		album = ((String) properties.get( "album"));
	    
	    if(((String) properties.get( "title" )).equals(null) )
		title = "";
	    else
		title = (String) properties.get( "title" );
	               
	    if(((String) properties.get( "date" )).equals(null) )
		year = 1900;
	    else {
		String temp  = ((String) properties.get( "date" ));
		year = Integer.parseInt(temp);
	    }
	}
	catch( ArrayIndexOutOfBoundsException oobe ) {
	    System.err.println( "Usage: java MP3File file.mp3" );
	}
	catch( Exception e ) {
	    System.out.println(e); 
	}

	
    }
    
    public String getPath() {
	return pathname;
    }
    
    public void setPath(String pathname) {
	if(!pathname.equals(null))
	    this.pathname = pathname;
    }
    
    public String getAuthor() {
	return author;
    }
    
    public void setAuthor(String author) {
	if(!author.equals(null))
	    this.author = author;
    }
    
    public String getAlbum() {
	return album;
    }
    
    public void setAlbum(String album) {
	if(!album.equals(null))
	    this.album = album;
    }
    
    public int getYear() {
	return year;
    }
    
    public void setYear (int year) {
	if(year < 2014 || year > 1900)
	    this.year = year;
    }
    
    public String getTitle() {
	return title;
    }
    
    public void setTitle(String title) {
	if(!title.equals(null))
	    this.title = title;
    }
    
    public String toString() {
	 String tempString = "Information about: " + pathname + "\n" + "-------------------------- " + "\n" +
	     "Author: " + getAuthor() + "\n" + "Album:  " + getAlbum() + "\n" + "Title:  " + getTitle() +
	     "\n" + "Year:   " + getYear();
	 return tempString;
    }
    
    public boolean equals(Object anObject) {
	if(anObject instanceof MP3File) {
	    if(album.equals(((MP3File)anObject).getAlbum()) && title.equals(((MP3File)anObject).getTitle()) &&
	       author.equals(((MP3File)anObject).getAuthor()) && year == ((MP3File)anObject).getYear())
		return true;
	    else
		return false;
	}
	else
	    return false;
    }
    
    //TOOK OUT THE PLAY METHOD
}
