package com.example.oneblood;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class Search extends AppCompatActivity {

    Spinner spinnerBlood, inputBGroup;
    EditText cal, inputDate ;
    Calendar currentDate;
    int day,month,year;
    TextInputLayout inputName, inputAge,inputAddress, inputPhone;
    Button inputButton;
    DBHelper DB;
    ListView listView;
    ArrayList<UserConstructor> arrayList;
    DonorAdapter donorAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        inputName =(TextInputLayout)findViewById(R.id.name);
        inputAge =(TextInputLayout)findViewById(R.id.age);
        inputBGroup=(Spinner)findViewById(R.id.bloodtype);
        inputAddress=(TextInputLayout)findViewById(R.id.address);
        inputPhone=(TextInputLayout)findViewById(R.id.phonenumber);
        inputDate=(EditText)findViewById(R.id.date);
        inputButton=(Button)findViewById(R.id.btnpost);
        DB=new DBHelper(this);

        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestConstructor requestConstructor = new RequestConstructor();

                if (inputName.equals("") || inputAge.equals("") || inputBGroup.equals("") || inputAddress.equals("") || inputPhone.equals("") || inputDate.equals("")) {
                    Toast.makeText(Search.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        requestConstructor = new RequestConstructor(-1, inputName.getEditText().getText().toString(),
                                inputBGroup.getSelectedItem().toString(), inputAddress.getEditText().getText().toString(),
                                inputPhone.getEditText().getText().toString(), inputDate.getText().toString(),
                                Integer.parseInt(inputAge.getEditText().getText().toString()));
                    } catch (Exception e) {
                        Toast.makeText(Search.this, "Error" + e, Toast.LENGTH_SHORT).show();

                    }
                    DBHelper DB = new DBHelper(Search.this);
                    Boolean success = DB.onInsertRequest(requestConstructor);
                    Toast.makeText(Search.this, "Successfully Posted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Search.class);
                    startActivity(intent);

                }
            }
        });

        //ListView in Search


        listView =(ListView)findViewById(R.id.donorListView);
        arrayList = new ArrayList<>();

        loadDataInListView();
        




        cal =(EditText) findViewById(R.id.date);
        currentDate = Calendar.getInstance();
        day = currentDate.get(Calendar.DAY_OF_MONTH);
        month = currentDate.get(Calendar.MONTH);
        year = currentDate.get(Calendar.YEAR);

        month= month+1;
        cal.setText(day+"/"+month+"/"+year);
        cal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(Search.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        cal.setText(dayOfMonth+"/"+month+"/"+year);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });




        spinnerBlood=(Spinner)findViewById(R.id.bloodtype);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.search);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.search:

                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        String namefromintent = getIntent().getStringExtra("phone");
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);

                        return true;
                }
                return false;
            }
        });




    }

    private void loadDataInListView() {

        arrayList=DB.getDonorData();

        donorAdapter= new DonorAdapter(this,arrayList);
        listView.setAdapter(donorAdapter);
    }

}