package com.example.karan.bookreps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Karan on 4/18/2017.
 */

class DatabaseRequest extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "MyDB.db";
    public static final String DATA_TABLE_NAME = "Data";
    public static final String DATA_COLUMN_ID = "id";
    public static final String DATA_COLUMN_BNAME = "bookname";
    public static final String DATA_COLUMN_ANAME = "bookauthor";
    public static final String DATA_COLUMN_EMAIL = "emailid";

    SQLiteDatabase dbs=this.getWritableDatabase();


    public DatabaseRequest(Context context)
    {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table Data"
                + "(id integer primary key AUTOINCREMENT, bookname text,bookauthor text,emailid text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        dbs.execSQL("DROP TABLE IF EXISTS Data");
        onCreate(db);
    }

    public boolean insertdata(String s, String s1, String s2)
    {

        SQLiteDatabase dbs = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DATA_COLUMN_BNAME, s);
        contentValues.put(DATA_COLUMN_ANAME ,s1);
        contentValues.put(DATA_COLUMN_EMAIL, s2);


        dbs.insert("Data", null, contentValues);
        return true;

    }

    public ArrayList<Model2> getAllContacts()
    {
        ArrayList<Model2> array_list = new ArrayList<Model2>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from Data", null );
        res.moveToFirst();

        while(res.isAfterLast() == false)
        {
            Model2 m=new Model2();
            m.setBookNM(res.getString(res.getColumnIndex(DATA_COLUMN_BNAME)));
            m.setBookAuth(res.getString(res.getColumnIndex(DATA_COLUMN_ANAME)));
            m.setEmailuser(res.getString(res.getColumnIndex(DATA_COLUMN_EMAIL)));
            array_list.add(m);
            res.moveToNext();
        }
        return array_list;
    }

}
