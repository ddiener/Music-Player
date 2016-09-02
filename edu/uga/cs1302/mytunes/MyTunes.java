package edu.uga.cs1302.mytunes;

import javax.swing.JFrame;

public class MyTunes {

    public static void main(String[] args) {
	String pathname = args[0];
	MP3Collection collection = new MP3Collection(pathname);
	
	JFrame frame = new JFrame("MyTunes");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	MyTunesControls panel = new MyTunesControls(collection);
	frame.add(panel);
	
	frame.pack();
	frame.setVisible(true);
    }

}
