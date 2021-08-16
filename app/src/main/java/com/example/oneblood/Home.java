package com.example.oneblood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    DBHelper DB;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ListView listView =(ListView) findViewById(R.id.listview);
        DB = new DBHelper(this);

        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = DB.getdata();


        if(cursor.getCount() == 0){
            Toast.makeText(Home.this, "Nothing existed!", Toast.LENGTH_SHORT).show();

        }else{
            while (cursor.moveToNext()) {
                list.add("Name: "+cursor.getString(0)+"\n"+"Age:"+cursor.getString(1)+"\n"+"Blood Type:" +cursor.getString(2)+"\n"+"Address:" +cursor.getString(3)+"\n"+"Contact Number:" +cursor.getString(4)+"\n"+"Needed Before:" +cursor.getString(5));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
                listView.setAdapter(listAdapter);
            }
        }



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),Search.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:

                        return true;
                }
                return false;
            }
        });

    }
}