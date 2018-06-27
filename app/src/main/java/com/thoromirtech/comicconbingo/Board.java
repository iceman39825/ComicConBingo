package com.thoromirtech.comicconbingo;

import android.database.Cursor;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.Collections;

public class Board {
	Cell[][] filledInElements;

    public Board(int rowCount, int columnCount, DatabaseHelper db, Cell[][] elements){
        filledInElements = elements;

        Cursor cursor = db.getCellValues();
        ArrayList<String> cellValues = new ArrayList<>();
        while(cursor.moveToNext())
        {
            cellValues.add(cursor.getString(0));
        }

        Collections.shuffle(cellValues);
    	 for(int i=0; i < rowCount; i++)
    	 {
    		 for(int j=0; j < columnCount; j++)
    		 {
    				 elements[i][j].value = cellValues.get((i) * (rowCount) + j);
    		 }
    	 }

    	 elements[(rowCount-1)/2][(columnCount-1)/2].value = "Free";
         elements[(rowCount-1)/2][(columnCount-1)/2].status = Cell.SELECTED;
	 }
}
