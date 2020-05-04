package com.example.databaseproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class databaseManager extends SQLiteOpenHelper {

    public databaseManager(Context context){
        //this tells it which app its a part of
        super(context, "GroceryDB", null, 1);

    }
    //used to create a database, builds the database
    public void onCreate(SQLiteDatabase db){

        //construct sql database by making sql commands, information being put in database
        String sql = "create table GroceryTable(";
        sql += "id integer primary key autoincrement, ";
        sql += "category text, item text)";
        db.execSQL(sql);
    }
    //inserts information into the database
    public void insert(String category, String item){

        //gives us write access to the database
        SQLiteDatabase db = getWritableDatabase();
        String sql = "insert into GroceryTable values(";
        sql += "null, '"+category+"','"+item+"')";
        db.execSQL(sql);
        db.close();

    }
    public void delete(String item){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from GroceryTable where country ='"+item+"'";
        db.execSQL(sql);
        db.close();

    }

    //updating database
    public void updateByItem(String category, String item){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update GroceryTable set category = '"+category+"'";
        sql += "where item = '"+item+"'";
        db.execSQL(sql);
        db.close();
    }
    //creates an array list of all titles in database
    public ArrayList<String> getItem(){

        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        //select everything from movie table
        String sql = "select * from GroceryTable";
        Cursor cursor = db.rawQuery(sql,null);

        //retrieving all rows of the table looping through with the cursor
        //every time the loop repeats the cursor should move to the next row
        while(cursor.moveToNext()){
            String item = cursor.getString(2);
            list.add(item);
        }
        db.close();
        return list;
    }

    //gets the first thing in the database
    //cant return 2 things in java so an array has to be created
    public String [] get(String itemTitle) {
        SQLiteDatabase db = getWritableDatabase();
        //* means to grab everything
        String sql = "select * from GroceryTable where item = '"+itemTitle+"'"; //quotes needed w/string
        //refers to what we just retrieved
        Cursor cursor = db.rawQuery(sql, null);
        //grabs whats on the first row, can grab as many things aas possible in the database
        //you have to advance the cursor to grab from each row
        String[] entry = new String[2];
        if (cursor.moveToFirst()) {
            String category = cursor.getString(1);
            String item = cursor.getString(2);
            entry[0] = category;
            entry[1] = item;
        }
        db.close();
        return entry;
    }

    //if new version exist this will update database
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
