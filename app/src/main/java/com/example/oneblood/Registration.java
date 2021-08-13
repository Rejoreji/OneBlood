package com.example.oneblood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText fullname,mail,phone,password,address;
    Button signup;
    TextView signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fullname=(EditText)findViewById(R.id.fullname);
        mail=(EditText)findViewById(R.id.email);
        address =(EditText)findViewById(R.id.address);
        phone=(EditText)findViewById(R.id.phonenumber);
        password=(EditText)findViewById(R.id.password);
        signup=(Button)findViewById(R.id.btnregister);
        signin=(TextView)findViewById(R.id.signin);
        DB=new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullname.getText().toString();
                String email = mail.getText().toString();
                String homeaddress = address.getText().toString();
                String telphone = phone.getText().toString();
                String pass = password.getText().toString();

                if (name.equals("")||homeaddress.equals("")||email.equals("")|| telphone.equals("")||pass.equals("")){
                    Toast.makeText(Registration.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuser = DB.checkusername(telphone);
                    if(checkuser==false){
                        Boolean insert = DB.insertLogin(telphone,pass) & DB.insertData(name,email,homeaddress,telphone,pass);

                        if(insert == true){
                            Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Registration.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Registration.this, "User Already Exist! Please login", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}