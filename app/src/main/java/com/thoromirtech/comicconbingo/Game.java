package com.thoromirtech.comicconbingo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Canvas;

import com.thoromirtech.comicconbingo.Board;
import com.thoromirtech.comicconbingo.Cell;

public class Game extends View {
	int rowCount = 5;
	int columnCount = 5;
	private int height;
	private int width;
	private Paint paintTool;
	private Cell[][] elements = null;
	private Context context;
	boolean gameOver = false;
	
	public Game(Context context, DatabaseHelper db) {
		super(context);
		this.context = context;
		this.setBackgroundColor(Color.BLACK);
		paintTool = new Paint();
        this.paintTool.setColor(Color.WHITE);
        this.paintTool.setAntiAlias(true);
        this.paintTool.setStyle(Style.STROKE);
        this.paintTool.setStrokeWidth(1);
        
        height = this.getHeight();
        width = this.getWidth();

        int cellHeight = height/rowCount;
        int cellWidth = width/columnCount;
        
        elements = new Cell[rowCount][columnCount];

        for(int i=0; i < rowCount; i++){
        	for(int j=0; j < columnCount; j++){
        		elements[i][j] = new Cell(cellHeight * j, cellWidth * i);
        	}
        }
        Board newBoard = new Board(rowCount, columnCount, db, elements);
        elements = newBoard.filledInElements;
	}
	
	 @Override
     protected void onDraw(Canvas canvas) {
             for (int i = 0; i < elements.length; i++) {
                     for (int j = 0; j < elements[i].length; j++) {
                             elements[i][j].draw(canvas, getResources(), j, i, this.getWidth()/elements.length, this.getHeight()/elements[0].length);
                     }
             }
             int cellHeight = this.getHeight()/rowCount;
             int cellWidth = this.getWidth()/columnCount;

             for (int i = 0; i <= columnCount; i++) {
                     canvas.drawLine(cellWidth * i, 0, cellWidth * i, this.getHeight(), paintTool);
             }
             for (int i = 0; i <= rowCount; i++) {
                     canvas.drawLine(0, cellHeight * i, this.getWidth(), cellHeight * i, paintTool);
             }
             super.onDraw(canvas);
     }
	 
	 @Override
     public boolean onTouchEvent(MotionEvent event) {
		 if(!gameOver){
             int x_aux = (int) (event.getX() / (this.getWidth() / rowCount));
             int y_aux = (int) (event.getY() / (this.getHeight() / columnCount));
             if(elements[y_aux][x_aux].status == Cell.NOT_SELECTED){
                 elements[y_aux][x_aux].status = Cell.SELECTED;
                 //Log.d("Bingo","Touched on x: "+String.valueOf(x_aux)+", y: "+String.valueOf(y_aux));
            	 //Toast.makeText(context, "You have selected "+ String.valueOf(elements[y_aux][x_aux].number), Toast.LENGTH_SHORT).show();
            	 this.invalidate();
            	 if(!isGameOver()){
                	 this.invalidate();
                 }
             }
		 }
		 return false;
     }
	 
	 private boolean isGameOver(){
         if(markTheLine()){
             NewGameDialogFragment dialog = new NewGameDialogFragment();
             final Activity activity = (Activity) context;
             dialog.show(activity.getFragmentManager(), "");

             gameOver = true;
         }

         return gameOver;
	 }

	 public boolean markTheLine(){
		 int selectedCountInALine;
		 for(int i=0;i<rowCount;i++){
			 selectedCountInALine = 0;
			 for(int j=0;j<columnCount;j++){
				 if(elements[i][j].status != Cell.NOT_SELECTED){
					 selectedCountInALine++;
				 }
			 }
			 if(selectedCountInALine == columnCount){
				 for(int j=0;j<columnCount;j++){
					 elements[i][j].status = Cell.COMPLETED_A_LINE;
				 }
                 return true;
             }

			 selectedCountInALine = 0;
			 for(int j=0;j<columnCount;j++){
				 if(elements[j][i].status != Cell.NOT_SELECTED){
					 selectedCountInALine++;
				 }
			 }
			 if(selectedCountInALine == columnCount){
				 for(int j=0;j<columnCount;j++){
					 elements[j][i].status = Cell.COMPLETED_A_LINE;
				 }
                 return true;
             }

		 }
		 
		 selectedCountInALine = 0;
		 for(int i=0;i<rowCount;i++){
			 if(elements[i][i].status != Cell.NOT_SELECTED){
				 selectedCountInALine++;
			 }
		 }
		 if(selectedCountInALine == rowCount){
			 for(int i=0;i<rowCount;i++){
				 elements[i][i].status = Cell.COMPLETED_A_LINE;
			 }
             return true;
         }
		 
		 selectedCountInALine = 0;
		 for(int i=0;i<rowCount;i++){
			 if(elements[rowCount-(i+1)][i].status != Cell.NOT_SELECTED){
				 selectedCountInALine++;
			 }
		 }
		 if(selectedCountInALine == rowCount){
			 for(int i=0;i<rowCount;i++){
				 elements[rowCount-(i+1)][i].status = Cell.COMPLETED_A_LINE;
			 }
             return true;
         }
		 
		 return false;
	 }
}
