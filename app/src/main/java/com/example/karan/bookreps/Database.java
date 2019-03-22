package com.example.karan.bookreps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDB.db";
    public static final String DATA_TABLE_NAME = "Data";
    public static final String DATABASE_TABLE_NAME = "DataSell";
    public static final String DATABASER_TABLE_NAME = "DataRequest";

    public static final String DATA_COLUMN_ID = "id";
    public static final String DATA_COLUMN_NAME = "name";
    public static final String DATA_COLUMN_EMAIL = "email";
    public static final String DATA_COLUMN_PASSWORD = "password";
    public static final String DATA_COLUMN_PHONE = "phone";

    public static final String DATABASE_COLUMN_ID = "id";
    public static final String DATA_COLUMN_ISBN = "isbn";
    public static final String DATA_COLUMN_BOOKTITLE = "title";
    public static final String DATA_COLUMN_AUTHOR = "author";
    public static final String DATA_COLUMN_EDITION = "edition";
    public static final String DATA_COLUMN_CONDITION = "condition";
    public static final String DATA_COLUMN_DESCRIPTION = "description";
    public static final String DATA_COLUMN_ORIGINAL_PRICE = "oprice";
    public static final String DATA_COLUMN_OFFERED_PRICE = "ofprice";
    public static final String DATA_COLUMN_CONTACT_NO = "cntctno";

    public static final String DATABASER_COLUMN_ID = "id";
    public static final String DATA_COLUMN_BNAME = "bookname";
    public static final String DATA_COLUMN_ANAME = "bookauthor";
    public static final String DATA_COLUMN_EMAILR = "emailid";



    SQLiteDatabase dbs=this.getWritableDatabase();

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0)
    {
        arg0.execSQL("create table"+DATA_TABLE_NAME
                + "(id integer primary key AUTOINCREMENT, name text,email text,password text, phone text)");

        arg0.execSQL("create table"+DATABASE_TABLE_NAME
                + "(id integer primary key AUTOINCREMENT, isbn text,title text,author text, edition text," +
                "condition text,description text,oprice text,ofprice text,cntctno text)");
        arg0.execSQL("create table"+DATABASER_TABLE_NAME
                + "(id integer primary key AUTOINCREMENT, bookname text,bookauthor text,emailid text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        dbs.execSQL("DROP TABLE IF EXISTS Data");
        onCreate(db);
    }


    public boolean insertContact(String name,  String email,
                                 String password,String phone) {
        SQLiteDatabase dbsd = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DATA_COLUMN_NAME, name);
        contentValues.put(DATA_COLUMN_EMAIL, email);
        contentValues.put(DATA_COLUMN_PASSWORD, password);
        contentValues.put(DATA_COLUMN_PHONE, phone);


        dbsd.insert("Data", null, contentValues);
        return true;
    }

    public boolean insertBookDetails(String isbn,  String title,
                                 String author,String edition,String condition,String description,String oprice,String ofprice,String cntctno ) {
        SQLiteDatabase dbs1 = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DATA_COLUMN_ISBN, isbn);
        contentValues.put(DATA_COLUMN_BOOKTITLE, title);
        contentValues.put(DATA_COLUMN_AUTHOR, author);
        contentValues.put(DATA_COLUMN_EDITION, edition);
        contentValues.put(DATA_COLUMN_CONDITION, condition);
        contentValues.put(DATA_COLUMN_DESCRIPTION, description);
        contentValues.put(DATA_COLUMN_ORIGINAL_PRICE,oprice);
        contentValues.put(DATA_COLUMN_OFFERED_PRICE, ofprice);
        contentValues.put(DATA_COLUMN_CONTACT_NO, cntctno);


        dbs1.insert("DataSell", null, contentValues);
        return true;
    }

    public boolean insertdata(String s, String s1, String s2)
    {

        SQLiteDatabase dbs = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DATA_COLUMN_BNAME, s);
        contentValues.put(DATA_COLUMN_ANAME ,s1);
        contentValues.put(DATA_COLUMN_EMAIL, s2);


        dbs.insert("DataRequest", null, contentValues);
        return true;

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
            m.setEmailuser(res.getString(res.getColumnIndex(DATA_COLUMN_EMAILR)));
            array_list.add(m);
            res.moveToNext();
        }
        return array_list;
    }


    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Data where id=" + id + "",
                null);
        return res;
    }

    public String checkLogin(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Data", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            if (res.getString(res.getColumnIndex(DATA_COLUMN_EMAIL)).matches(name)
                    && res.getString(res.getColumnIndex(DATA_COLUMN_PASSWORD)).matches(password)) {
                return res.getString(res.getColumnIndex(DATA_COLUMN_ID));
            } else {
                res.moveToNext();
            }
        }
        return "";
    }

    public String checkNumber(String name, String phone) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Data", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            if (res.getString(res.getColumnIndex(DATA_COLUMN_EMAIL)).matches(name)
                    && res.getString(res.getColumnIndex(DATA_COLUMN_PHONE)).matches(phone)) {
                return res.getString(res.getColumnIndex(DATA_COLUMN_ID));
            } else {
                res.moveToNext();
            }
        }
        return "";
    }

//    public void updateContact(String s, String s1) {
//    }
//
////    public Cursor rawQuery(Object p0) {
////    }
//
////    public boolean updateContact (String name, String newName)
////    {
////        SQLiteDatabase db = this.getWritableDatabase();
////        ContentValues contentValues = new ContentValues();
////        contentValues.put("name", newName);
//////		      contentValues.put("phone", phone);
//////		      contentValues.put("email", email);
//////		      contentValues.put("street", street);
//////		      contentValues.put("place", place);
////        db.update("contacts", contentValues, "name = ? ", new String[] { name } );
////        return true;
////    }

}

