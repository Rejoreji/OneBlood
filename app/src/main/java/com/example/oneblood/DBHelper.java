package com.example.oneblood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "oneblood.db";

    public DBHelper(Context context) {
        super(context, "oneblood.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table users(phone TEXT primary key, password TEXT)");
        MyDB.execSQL("create table customerData(fullName TEXT, email TEXT, address TEXT,phone TEXT primary key, password TEXT)");
        MyDB.execSQL("create table request(fullName TEXT, age TEXT, bloodGroup TEXT,address TEXT,phone TEXT, date TEXT)");


    }




    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists customerData");
        MyDB.execSQL("drop Table if exists request");

    }

    public Boolean insertLogin(String phone, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone",phone);
        contentValues.put("password",password);
        long result = MyDB.insert("users",null,contentValues);
        if (result==-1)return false;
        else
            return true;
    }

    public Boolean insertData(String fullName, String email, String address,String phone, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullName",fullName);
        contentValues.put("email",email);
        contentValues.put("address",address);
        contentValues.put("phone",phone);
        contentValues.put("password",password);
        long result = MyDB.insert("customerData",null,contentValues);
        if (result==-1)return false;
        else
            return true;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where phone =? ",new String[]{username});
        if (cursor.getCount()>0)
            return  true;
        else
            return false;

    }
    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where phone = ? and password = ?",new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }

    public Boolean onInsertRequest(String fullName,String age, String bloodGroup,String address, String phone , String date){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullName",fullName);
        contentValues.put("age",age);
        contentValues.put("bloodGroup",bloodGroup);
        contentValues.put("address",address);
        contentValues.put("phone",phone);
        contentValues.put("date", String.valueOf(date));
        long result = MyDB.insert("request",null,contentValues);
        if (result==-1)
            return false;
        else
            return true;

    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from request", null) ;

        return cursor;

    }
}
