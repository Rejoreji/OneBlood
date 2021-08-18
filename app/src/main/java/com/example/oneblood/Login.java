package com.example.oneblood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    public static UserConstructor currentUser;



    EditText phone, password;
    Button login;
    TextView signup;
    DBHelper DB;
    private Object ArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone =(EditText) findViewById(R.id.username1);
        password =(EditText) findViewById(R.id.password1);
        login =(Button) findViewById(R.id.btnlogin);
        signup=(TextView) findViewById(R.id.signup);
        DB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = phone.getText().toString();
                String pass = password.getText().toString();


                if (user.equals("")||pass.equals(""))
                    Toast.makeText(Login.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkUserandPassword(user,pass);
                    if (checkuserpass == true){
                        Toast.makeText(Login.this, "Sign in Successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(),Home.class);
                        intent.putExtra("phone",phone.getText().toString().trim());
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Registration.class);
                startActivity(intent);
            }
        });


    }
}