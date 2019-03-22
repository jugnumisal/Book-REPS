package com.example.karan.bookreps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Karan on 4/17/2017.
 */

class DatabaseSell extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBS.db";
    public static final String DATA_TABLE_NAME = "Datasell";
    public static final String DATA_COLUMN_ID = "id";


    //	   public static final String CONTACTS_COLUMN_ID = "id";

    public static final String DATA_COLUMN_ISBN = "isbn";
    public static final String DATA_COLUMN_BOOKTITLE = "title";
    public static final String DATA_COLUMN_AUTHOR = "author";
    public static final String DATA_COLUMN_EDITION = "edition";
    public static final String DATA_COLUMN_CONDITION = "condition";
    public static final String DATA_COLUMN_DESCRIPTION = "description";
    public static final String DATA_COLUMN_ORIGINAL_PRICE = "oprice";
    public static final String DATA_COLUMN_OFFERED_PRICE = "ofprice";
    public static final String DATA_COLUMN_CONTACT_NO = "cntctno";


    SQLiteDatabase dbs=this.getWritableDatabase();

    public DatabaseSell(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0)
    {
        arg0.execSQL("create table data "
                + "(id integer primary key AUTOINCREMENT, isbn text,title text,author text, edition text,condition text,description text,oprice text,ofprice text,cntctno text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbsb, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        dbs.execSQL("DROP TABLE IF EXISTS Data");
        onCreate(dbsb);
    }


    public boolean insertContact(String isbn,  String title,
                                 String author,String edition,String condition,String description,String oprice,String ofprice,String cntctno ) {
        SQLiteDatabase dbs1 = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("isbn", isbn);
        contentValues.put("title", title);
        contentValues.put("author", author);
        contentValues.put("edition", edition);
        contentValues.put("condition", condition);
        contentValues.put("description", description);
        contentValues.put("oprice",oprice);
        contentValues.put("ofprice", ofprice);
        contentValues.put("cntctno", cntctno);


        dbs1.insert("Data", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Data where id=" + id + "",
                null);
        return res;
    }


    public ArrayList<Model1> getAllCotacts()
    {
        ArrayList<Model1> array_list = new ArrayList<Model1>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from Data", null );
        res.moveToFirst();

        while(res.isAfterLast() == false)
        {
            Model1 m=new Model1();
            m.setISBN(res.getString(res.getColumnIndex(DATA_COLUMN_ISBN)));
            m.setTitle(res.getString(res.getColumnIndex(DATA_COLUMN_BOOKTITLE)));
            m.setAuthor(res.getString(res.getColumnIndex(DATA_COLUMN_AUTHOR)));
            m.setEdition(res.getString(res.getColumnIndex(DATA_COLUMN_EDITION)));
            m.setCondition(res.getString(res.getColumnIndex(DATA_COLUMN_CONDITION)));
            m.setDescription(res.getString(res.getColumnIndex(DATA_COLUMN_DESCRIPTION)));
            m.setOriginal_price(res.getString(res.getColumnIndex(DATA_COLUMN_ORIGINAL_PRICE)));
            m.setOffere_price(res.getString(res.getColumnIndex(DATA_COLUMN_OFFERED_PRICE)));
            m.setPhone_number(res.getString(res.getColumnIndex(DATA_COLUMN_CONTACT_NO)));
            array_list.add(m);
            res.moveToNext();
        }
        return array_list;
    }


}
