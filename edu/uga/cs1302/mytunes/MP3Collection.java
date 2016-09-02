package edu.uga.cs1302.mytunes;

import java.io.File;
import java.util.ListIterator;

public class MP3Collection {

    
    public SimpleArrayList<MP3File> arrayList;
    public MP3Player player;
    
    /**
     * This creates a new MP3Collection object with an empty array list.
     */
    public MP3Collection() {
	arrayList = new SimpleArrayList<MP3File>();
	player = new MP3Player();
    }
    
    /**
     * This creates a new MP3Collection object that has a simple array list of contents of the
     * directory specified as the argument, or if such a directory does not exist, then 
     * the "Music" folder of the current directory is used. 
     * @param directoryPathname this is the directory to be referenced for the array list
     */
    public MP3Collection(String directoryPathname) {
	//creates a new MP3Player object & a new simple array list object
	player = new MP3Player();
	arrayList = new SimpleArrayList<MP3File>();
	
	//this file object is created to see if the argument is a directory or if it even exists
	File fileCheck = new File(directoryPathname);
	String actualDirectory;
	
	//this checks if the pathname exists and is a directory, if it does not exist, then
	//the pathname "./Music" is used for the rest of the purposes.
	if(fileCheck.exists() && fileCheck.isDirectory() == true) 
	    actualDirectory = directoryPathname;
	else {
	    System.out.println("The given pathname is not valid! Using the Music "
			       + "folder of the current directory instead.");
	    actualDirectory = "./Music";
	}
	
	//this object and subsequent file array stores the files of the directory
	File newFile = new File(actualDirectory);
	File[] fileArray = newFile.listFiles();
	
	//adds each file of the file array to the MP3Collection's simple array list
	for(int i = 0; i < fileArray.length; i++) {
	    MP3File newMP3 = new MP3File(fileArray[i].getAbsolutePath());
	    arrayList.add(newMP3);
	}
	
	
    }
    
    /**
     * This method creates an object array for the JPanel to read in values from. Each row
     * represents a different file, and each column represents each attribute of the file.
     * @return returns an organized object array with all of the objects data
     */
    public Object[][] getTableData() {
	//creates an iterator to navigate the simple array list
	ListIterator<MP3File> iter = arrayList.listIterator(0);
	//the object array that is to be returned
	Object[][] returnedArray = new Object[arrayList.size()][4];
	
	//this takes the tags from each MP3File object 
	for(int n = 0; n < arrayList.size(); n++) {
	    MP3File fileTemp = iter.next();
	    returnedArray[n][0] = fileTemp.getTitle();
	    returnedArray[n][1] = fileTemp.getAuthor();
	    returnedArray[n][2] = fileTemp.getAlbum();
	    returnedArray[n][3] = fileTemp.getYear();
	}
	
	return returnedArray;
    }
    
    /**
     * This just returns an integer value representing the size of the internal simple array list.
     * @return the size of the array list
     */
    public int size() {
	return arrayList.size();
    }
    
    /**
     * This returns an MP3File object in the simple object array that is located at the
     * index given as the argument in the method's parameters. It throws an exception 
     * if the index is, in fact, out of bounds.
     * @param index this is the integer value the user wishes to obtain the object info from
     * @return returns the MP3File object at that index in the collection
     * @throws IndexOutOfBoundsException is thrown if the argument is out of range
     */
    public MP3File getFile(int index) throws IndexOutOfBoundsException {
	//checks to see if the index is within bounds
	if(index >= arrayList.size())
	    throw new IndexOutOfBoundsException();
	else{
	    ListIterator<MP3File> iter = arrayList.listIterator(index);
	    return iter.next();
	}

    }
    
    /**
     * This method starts playing an MP3 file in the collection at a given index
     * @param index the index of the desired MP3 file to be played
     * @throws IndexOutOfBoundsException is thrown if the index in the method 
     * parameters is not within range of the collection.
     */
    public void startPlay(int index) throws IndexOutOfBoundsException {
	//checks if given index is within bounds
	if(index >= arrayList.size())
	    throw new IndexOutOfBoundsException();
	
	//creates an iterator to find the MP3 file, get its pathname, and play it.
	ListIterator<MP3File> iter = arrayList.listIterator(index);
	String pathName = iter.next().getPath();
	player.play(pathName);
    }
    
    /**
     * This method stops the player of a collection from playing a selected file.
     */
    public void stopPlay() {
	player.stop();
    }
}
