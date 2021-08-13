package com.example.oneblood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "login.db";

    public DBHelper(Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table users(phone TEXT primary key, password TEXT)");
        MyDB.execSQL("create table customerData(Fullname TEXT, email TEXT, address TEXT,phone TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists customerData");

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

    public Boolean insertData(String Fullname, String email, String address,String phone, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Fullname",Fullname);
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
}
