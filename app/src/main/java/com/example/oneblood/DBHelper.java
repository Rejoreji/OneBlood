package com.example.oneblood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "oneblood.db";
    public static final String CUSTOMER_DATA = "customerData";
    public static final String COLUMN_FULL_NAME = "fullName";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_BLOOD_GROUP = "bloodGroup";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_DONOR = "donor";
    public static final String COLUMN_DONEE = "donee";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_ID = "id";
    public static final String REQUEST = "request";
    public static final String COLUMN_PATIENT_PHONE = "patientPhone";

    public DBHelper(Context context) {
        super(context, "oneblood.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create table " + CUSTOMER_DATA + "(" + COLUMN_FULL_NAME + " TEXT, " + COLUMN_AGE + " INT, " + COLUMN_BLOOD_GROUP + " TEXT ," + COLUMN_EMAIL + " TEXT, " + COLUMN_ADDRESS + " TEXT," + COLUMN_PHONE + " TEXT primary key, " + COLUMN_PASSWORD + " TEXT, " + COLUMN_DONOR + " TEXT)");
        MyDB.execSQL("create table " + REQUEST + "(" + COLUMN_ID + " integer primary key autoincrement , " + COLUMN_DONEE + " TEXT, " + COLUMN_AGE + " TEXT, " + COLUMN_BLOOD_GROUP + " TEXT," + COLUMN_ADDRESS + " TEXT, " + COLUMN_PATIENT_PHONE + " TEXT, " + COLUMN_DATE + " TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        MyDB.execSQL("drop Table if exists " + CUSTOMER_DATA);
        MyDB.execSQL("drop Table if exists " + REQUEST);

    }

    public Boolean insertData(UserConstructor userConstructor) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FULL_NAME, userConstructor.getFullName());
        cv.put(COLUMN_AGE, userConstructor.getAge());
        cv.put(COLUMN_BLOOD_GROUP, userConstructor.getBloodgroup());
        cv.put(COLUMN_EMAIL, userConstructor.getEmail());
        cv.put(COLUMN_ADDRESS, userConstructor.getAddress());
        cv.put(COLUMN_PHONE, userConstructor.getPhone());
        cv.put(COLUMN_PASSWORD, userConstructor.getPassword());
        cv.put(COLUMN_DONOR, userConstructor.getDonor());
        long result = MyDB.insert(CUSTOMER_DATA, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }


    public boolean checkUser(String phone) {
        String[] column = {COLUMN_PHONE};
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String selection = COLUMN_PHONE + "=?";
        String[] selectionArgs = {phone};
        Cursor cursor = MyDB.query(CUSTOMER_DATA, column, selection, selectionArgs, null, null, null);
        int cursorcount = cursor.getCount();
        cursor.close();
        MyDB.close();

        if (cursorcount > 0) {
            return true;
        } else
            return false;

    }

    public boolean checkUserandPassword(String phone, String password) {
        String[] column = {COLUMN_FULL_NAME};
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String selection = COLUMN_PHONE + " =?" + " AND " + COLUMN_PASSWORD + " =?";
        String[] selectionArgs = {phone, password};
        Cursor cursor = MyDB.query(CUSTOMER_DATA, column, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        MyDB.close();

        if (cursorCount > 0) {
            return true;
        } else
            return false;

    }


    public Boolean onInsertRequest(RequestConstructor requestConstructor) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DONEE, requestConstructor.getDonee());
        cv.put(COLUMN_AGE, requestConstructor.getAge());
        cv.put(COLUMN_BLOOD_GROUP, requestConstructor.getBloodgroup());
        cv.put(COLUMN_ADDRESS, requestConstructor.getAddress());
        cv.put(COLUMN_PATIENT_PHONE, requestConstructor.getPatientPhone());
        cv.put(COLUMN_DATE, requestConstructor.getDate());
        long result = MyDB.insert(REQUEST, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public ArrayList<RequestConstructor>getAllData(){
        ArrayList<RequestConstructor> arrayList = new ArrayList<>();
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM "+REQUEST,null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String bloodgroup = cursor.getString(3);
            String address = cursor.getString(4);
            String phone = cursor.getString(5);
            String date = cursor.getString(6);

            RequestConstructor requestConstructor = new RequestConstructor(id,name, bloodgroup, address, phone, date,age);
            arrayList.add(requestConstructor);

        }
        return arrayList;
    }

    public ArrayList<UserConstructor>getDonorData(){
        ArrayList<UserConstructor> arrayList = new ArrayList<>();
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM "+CUSTOMER_DATA+ " WHERE donor IN (true) ",null);

        while (cursor.moveToNext()){
            String fullName = cursor.getString(0);
            int age = cursor.getInt(1);
            String bloodgroup = cursor.getString(2);
            String address = cursor.getString(4);
            String phone = cursor.getString(5);

            UserConstructor userConstructor = new UserConstructor(fullName, bloodgroup, address, phone,age);
            arrayList.add(userConstructor);

        }
        return arrayList;
    }

}
