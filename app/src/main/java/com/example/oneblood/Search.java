package com.example.oneblood;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Date;
import java.util.Calendar;

public class Search extends AppCompatActivity {

    Spinner spinnerBlood, inputBGroup;
    EditText cal, inputDate ;
    Calendar currentDate;
    int day,month,year;
    TextInputLayout inputName, inputAge,inputAddress, inputPhone;
    Button inputButton;
    DBHelper DB;



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
                String name = inputName.getEditText().getText().toString();
                String age = inputAge.getEditText().getText().toString();
                String blood = inputBGroup.getSelectedItem().toString();
                String homeaddress = inputAddress.getEditText().getText().toString();
                String telphone = inputPhone.getEditText().getText().toString();
                String  date =inputDate.getText().toString();

                if (name.equals("")||age.equals("")||blood.equals("")|| homeaddress.equals("")|| telphone.equals("")||date.equals("")){
                    Toast.makeText(Search.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                }
                else{

                        Boolean insert =DB.onInsertRequest(name,age,blood,homeaddress,telphone,date);

                        if(insert == true){
                            Toast.makeText(Search.this, "Successfully posted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Home.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Search.this, "Request Failed", Toast.LENGTH_SHORT).show();
                        }

                }
            }
        });



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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.blood_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBlood.setAdapter(adapter);





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
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);

                        return true;
                }
                return false;
            }
        });




    }

}