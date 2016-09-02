package edu.uga.cs1302.mytunes;


import java.awt.*;
import java.awt.event.*;
import java.util.ListIterator;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 * This class creats a JFrame object that essentially runs the MyTunes program.
 * @author Dylan Diener
 *
 */
public class MyTunesControls extends JFrame
{
    private JButton play, stopPlay, cancelFilter;
    private JPanel buttonPanel;
    private JTextField txtFilter;
    private MP3Collection newColl;
    private JTable table;
    private TableModel model;
    private TableRowSorter<TableModel> sorter;
    
    /**
     * This creates the MyTunesControls object that draws from the collection in the parameter
     * to create a JFrame that displays all of the files in the collection, sorts all of them
     * upon user command, and gives the user the option to start and stop each .mp3
     * @param collection This is the collection that the MyTunes program draws from.
     */
    MyTunesControls(MP3Collection collection){
	//the collection in the constructor parameters is given a variable
	newColl = collection;
	
        //a JPanel is constructed with set dimensions for the stop, play, and cancel
	//filter buttons, as well as the space for the text field.
	buttonPanel = new JPanel();
	//buttonPanel.setPreferredSize(new Dimension(600, 400));
	
	//the play, stop, and cancel filter objects are created
	play = new JButton("Play");
	stopPlay = new JButton("Stop");
	cancelFilter = new JButton("Cancel Filter");
	//the text filter object is created
	txtFilter = new JTextField(20);
	
	//each of the created objects' listeners are created
	TextFieldListener textListener = new TextFieldListener();
	PlayButtonListener playListener = new PlayButtonListener();
	StopButtonListener stopListener = new StopButtonListener();
	CancelButtonListener cancelListener = new CancelButtonListener();
	
	//each of the created objects' listeners are assigned appropriately
	play.addActionListener(playListener);
	stopPlay.addActionListener(stopListener);
	txtFilter.addActionListener(textListener);
	cancelFilter.addActionListener(cancelListener);
	
	//each of the buttons are assigned to the pane created for them
	buttonPanel.add(play);
	buttonPanel.add(stopPlay);
	buttonPanel.add(txtFilter);
	buttonPanel.add(cancelFilter);
	
	//column and row assigners are given for later assignment
	String[] columns = { "Title", "Author", "Album", "Year" };
	Object[][] rows = newColl.getTableData();
	//a model is created wiht the two created arrays and a table is created using the model
	model = new DefaultTableModel( rows, columns ); 
	table = new JTable(model);
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	//a scroll pane is created for the table
	JScrollPane scrollPane = new JScrollPane(table);
	//scrollPane.setPreferredSize(new Dimension(300, 100));
	//a sorter is created for the table
        sorter = new TableRowSorter<TableModel>( model );
        table.setRowSorter( sorter );
	//a container is created to organize all of the created fields
        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.add(buttonPanel);
        c.add(scrollPane);
        setVisible(true);
    }
    /**
     * This is a button listener class for the play button
     * @author Dylan Diener
     *
     */
    private class PlayButtonListener implements ActionListener {
	/**
	 * This method executes when the play button is pressed.
	 */
	public void actionPerformed(ActionEvent event) {
	    int tableRow = table.getSelectedRow();
	    
	    if(tableRow == -1)
		return;
	    
	    else {
		newColl.stopPlay();
		newColl.startPlay(tableRow);
	    }
	    
	}
    }
    /**
     * This is a button listener class for the stop button
     * @author Dylan Diener
     *
     */
    private class StopButtonListener implements ActionListener {
	/**
	 * This method occurs when the stop button is pressed.
	 */
	public void actionPerformed(ActionEvent event) {
	    newColl.stopPlay();
	}
    }
    /**
     * This is a listener class created for the text field
     * @author Dylan Diener
     *
     */
    private class TextFieldListener implements ActionListener {
	/**
	 * This method exectues when the enter key is pressed in the text field.
	 */
	public void actionPerformed(ActionEvent event) {
	    String expr = txtFilter.getText();
	    
	    sorter.setRowFilter( RowFilter.regexFilter(expr));
	    
	    sorter.setSortKeys(null);
	}
    }
    /**
     * A class created for the cancel button as a listener
     * @author Dylan Diener
     *
     */
    private class CancelButtonListener implements ActionListener {
	/**
	 * This method executes when the cancel button is clicked.
	 */
	public void actionPerformed(ActionEvent event) {
	    sorter.setSortKeys(null);
	}
    }
    
}