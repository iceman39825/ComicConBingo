package com.thoromirtech.comicconbingo;

import android.graphics.Color;
import android.graphics.Point;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

public class Cell extends Point{
	
	public static final int NOT_SELECTED = 0;
	public static final int SELECTED = 1;
	public static final int COMPLETED_A_LINE = 2;
	
	String value;
	int status;
	
	public Cell(int x, int y) {	
		super(x, y);
		status = NOT_SELECTED;
	}
	
	public void draw(Canvas g, Resources res, int x, int y, int w, int h) {
		TextPaint paintTool = new TextPaint();
        paintTool.setAntiAlias(true);
        paintTool.setTextSize(60);
        paintTool.setStyle(Style.STROKE);
        paintTool.setColor(Color.WHITE);
        //Log.d("Bingo","Touched on x: "+String.valueOf(x)+", y: "+String.valueOf(y));
        if(status == 1){
            paintTool.setColor(Color.BLUE);
        }else if(status == 2){
            paintTool.setColor(Color.RED);
        }
		Layout.Alignment alignment = Layout.Alignment.ALIGN_CENTER;
		float spacingMultiplier = 1;
		float spacingAddition = 0;
		boolean includePadding = false;
		StaticLayout myStaticLayout = new StaticLayout(this.value, paintTool, w-50, alignment, spacingMultiplier, spacingAddition, includePadding);
		g.save();
		g.translate((x*w)+(w/10), (y*h)+(2*h/3) - 105);
		myStaticLayout.draw(g);
		g.restore();
        //g.drawText(String.valueOf(this.value), (x*w)+(w/10), (y*h)+(2*h/3), paintTool);
	}
}
