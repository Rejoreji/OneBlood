package com.example.oneblood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {

    EditText fullname, mail, phone, password, address, age;
    Spinner bloodGroup;
    Button signup;
    TextView signin;
    DBHelper DB;
    CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        fullname =  findViewById(R.id.fullname);
        age = findViewById(R.id.age);
        bloodGroup = findViewById(R.id.regbloodtype);
        mail =  findViewById(R.id.email);
        address =  findViewById(R.id.address);
        phone =  findViewById(R.id.phonenumber);
        password =  findViewById(R.id.password);
        check =  findViewById(R.id.checkBox);

        signup =  findViewById(R.id.btnregister);
        signin =  findViewById(R.id.signin);
        DB = new DBHelper(Registration.this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserConstructor userConstructor = new UserConstructor();


                if (fullname.equals("") || age.equals("") || bloodGroup.equals("") || mail.equals("") || address.equals("") || phone.equals("") || password.equals("")) {
                    Toast.makeText(Registration.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                } else {

                    Boolean checkuser = DB.checkUser(phone.getText().toString());
                    if (checkuser == false) {

                        try {
                            userConstructor = new UserConstructor(fullname.getText().toString(), password.getText().toString(),
                                    bloodGroup.getSelectedItem().toString(), mail.getText().toString(), address.getText().toString(),
                                    phone.getText().toString(), Integer.parseInt(age.getText().toString()), check.isChecked());
                        } catch (Exception e) {
                            Toast.makeText(Registration.this, "Error" + e, Toast.LENGTH_SHORT).show();

                        }


                        DBHelper DB = new DBHelper(Registration.this);
                        Boolean success = DB.insertData(userConstructor);
                        Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Registration.this, "User Already Exist! Please login", Toast.LENGTH_SHORT).show();

                    }
                }
            }


        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }
}