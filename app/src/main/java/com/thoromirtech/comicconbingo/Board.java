package com.thoromirtech.comicconbingo;

import java.util.Random;

public class Board {
	String[][] elements;

public Board(int rowCount, int columnCount, DatabaseHelper db){
	 elements = new String[rowCount][columnCount];
	 Random randomGenerator = new Random();

	 for(int i=0; i < rowCount; i++){
		 for(int j=0; j < columnCount; j++){
			 	int randomInt = randomGenerator.nextInt(1);
				 elements[i][j] = db.getCellValue(randomInt);
			 }
		 }
	 }
 }
