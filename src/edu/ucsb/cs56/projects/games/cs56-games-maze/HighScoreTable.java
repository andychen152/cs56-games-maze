package edu.ucsb.cs56.projects.games.cs56_games_maze;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.File;
import java.lang.Exception;
import java.io.*;

/**
   Class where the JTable for the High Score table is created.
   @author Victor Porter
   @version 2/26/2015 for proj 2, cs56, S13
*/

public class HighScoreTable{

	private HighScoreSaver scoreSaver;
	private ArrayList<MazeHighScore> highScores;

    public HighScoreTable() {

    scoreSaver = new HighScoreSaver("ABCDEF.ser");


    try{
    	if(scoreSaver.hasEmptyFile() != true) { highScores = scoreSaver.getHighScoreList();}
    }catch(IOException e){
    	System.err.println("read file error");
    	e.printStackTrace();
    }



	//initialize JFrame: set visible and size
	JFrame frame = new JFrame("High Scores");
	frame.setVisible(true);
	frame.setSize(300,300);

	int size = highScores.size();

	String rowData[][] = new String[size][size];
	String columnNames[] = { "Name", "Time", "Score"};

	for(int count = 0; count < size; count++){
		MazeHighScore currentHighScore = highScores.get(count);
		rowData[count][0] = currentHighScore.getName();
		
		long millis = currentHighScore.getTime();
		long second = (millis / 1000) % 60;
		long minute = (millis / (1000 * 60)) % 60;

		String time = String.format("%02d:%02d:%02d", minute, second, millis % 10);
		
		rowData[count][1] = time;
		rowData[count][2] = time;
	}
	JTable table = new JTable(rowData, columnNames);

	JScrollPane scrollPane = new JScrollPane(table);
    frame.add(scrollPane, BorderLayout.CENTER);
    frame.setSize(400, 250);
    frame.setVisible(true);

    }





}
