package com.thoromirtech.comicconbingo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper  extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "CellValues.db";
    public static final String TABLE_NAME = "CellValues";
    public static final String ID_COLUMN = "Id";
    public static final String VALUE_COLUMN = "Value";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                      + VALUE_COLUMN +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public String getCellValue(int randomInt)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor foo = db.rawQuery("SELECT " + VALUE_COLUMN + " FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = " + randomInt, null);
        return foo.getString(0);
    }
}
