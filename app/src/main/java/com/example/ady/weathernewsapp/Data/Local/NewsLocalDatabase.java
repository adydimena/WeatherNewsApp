package com.example.ady.weathernewsapp.Data.Local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ady on 1/12/2018.
 */

public class NewsLocalDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_BASE ="NEWS.DB";
    public static final String TABLE_NAME = "NEWS_TABLE";
    public static final String COL_1 = "DESCRIPTION";
    public static final String COL_2 = "AUTHOR";
    public static final String COL_3 = "TITLE";
    public static final String COL_4 = "IMAGE_URL";
    public static final String TAG = NewsLocalDatabase.class.getSimpleName();

    public NewsLocalDatabase(Context context) {

        super(context, DATABASE_BASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +
                " (" + COL_1+ " TEXT, " +
               COL_2 + " TEXT, " +
               COL_3 + " TEXT, " +
               COL_4 + " TEXT PRIMARY KEY )");

    //    sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +
    //    " (DESCRIPTION TEXT," +
    //            " AUTHOR TEXT," +
    //            " TITLE TEXT," +
    //            " IMAGE_URL TEXT PRIMARY KEY)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData (String DESCRIPTION, String AUTHOR, String TITLE,
                               String IMAGE_URL ){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,DESCRIPTION);
        contentValues.put(COL_2,AUTHOR);
        contentValues.put(COL_3,TITLE);
        contentValues.put(COL_4,IMAGE_URL);

        long row  = database.insert(TABLE_NAME,null,contentValues);
        if (row == -1)
            return false;
        else
            return true;
    }
    public Cursor getallColumn(String ColunmName)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String Query = "SELECT " +ColunmName+  " FROM "+ TABLE_NAME;
        Cursor results = database.rawQuery(Query,null);
        return  results;
    }
    public Cursor getallData()
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String Query = "SELECT * FROM "+ TABLE_NAME;
        Cursor results = database.rawQuery(Query,null);
        return  results;
    }
    public boolean isDataExists (String fieldValue){
        Cursor cursor = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String Query = "Select "+ COL_4 + " from " + TABLE_NAME + " where " + COL_4 + " = \"" + fieldValue+ "\""; // works only if field name is on double quotes

        cursor = database.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;

    }
    public boolean deleterow (String PrimaryKey){
        Cursor cursor = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String Query = "delete from " + TABLE_NAME + " where " + COL_4 + " = \"" + PrimaryKey+ "\""; // works only if field name is on double quotes

        cursor = database.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

}
