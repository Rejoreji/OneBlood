package com.example.oneblood;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    DBHelper DB;
    TextView text;
    ListView listView;
    ArrayList<RequestConstructor> arrayList;
    ImageView icon;

    private BottomNavigationView bottomNavigationView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView =(ListView) findViewById(R.id.listview);
        text=(TextView)findViewById(R.id.text1);
        icon=(ImageView)findViewById(R.id.icon);

        arrayList = new ArrayList<>();

        String namefromintent = getIntent().getStringExtra("phone");

        text.setText(" Welcome "+namefromintent);

        DB = new DBHelper(this);

        loadDataInListView();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.search:
                        String namefromintent = getIntent().getStringExtra("phone");
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

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Home.this, "Successfully Logged Out", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private void loadDataInListView() {

        arrayList=DB.getAllData();

        myAdapter= new MyAdapter(this,arrayList);
        listView.setAdapter(myAdapter);


    }


}