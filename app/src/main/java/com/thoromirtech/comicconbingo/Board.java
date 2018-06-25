package com.thoromirtech.comicconbingo;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collections;

public class Board {
	String[][] elements;

public Board(int rowCount, int columnCount, DatabaseHelper db){
	 elements = new String[rowCount][columnCount];

    Cursor cursor = db.getCellValues();
    ArrayList<String> cellValues = new ArrayList<>();
    while(cursor.moveToNext())
    {
        cellValues.add(cursor.getString(0));
    }

    Collections.shuffle(cellValues);
	 for(int i=0; i < rowCount; i++){
		 for(int j=0; j < columnCount; j++){
				 elements[i][j] = cellValues.get((i) * (rowCount) + j);
			 }
		 }
	 }
 }
