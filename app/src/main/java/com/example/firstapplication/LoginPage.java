package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    public EditText login_email;
    public EditText login_password;
    public Button login_btn;
    public TextView register_move;
    public String email_value;
    public String password_value;
    public String fetch_email_check;
    public String fetch_password_check;


//    ---------for shared preferences----

    SharedPreferences sharedPreferences;
    Intent shared_intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login_email = (EditText) findViewById(R.id.email_signin);
        login_password = (EditText) findViewById(R.id.password_signin);
        login_btn = (Button) findViewById(R.id.login_btn);
        register_move = (TextView) findViewById(R.id.register_move);
        email_value = login_email.getText().toString();
        password_value = login_password.getText().toString();

        sharedPreferences=getSharedPreferences("MainActivity",MODE_PRIVATE);
        shared_intent = new Intent(LoginPage.this, MainActivity.class);
        if(sharedPreferences.contains("fetch_email_check") && sharedPreferences.contains("fetch_password_check")){
            startActivity(shared_intent);
        }

        Intent fetch_register = getIntent();
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetch_email_check = fetch_register.getStringExtra("email");
                fetch_password_check = fetch_register.getStringExtra("password");


                if(login_email.getText().toString().equals("admin") && login_password.getText().toString().equals("123")){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("fetch_email_check",email_value);
                    editor.putString("fetch_password_check",password_value);
                    editor.commit();
                    startActivity(shared_intent);
                    Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(login_email.getText().toString()) && TextUtils.isEmpty(login_password.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Empty Value Found", Toast.LENGTH_LONG).show();

                }
                else{

                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();



                }




            }
        });

        register_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterScreen.class);
                startActivity(intent);
            }
        });


//        login_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                fetch_email_check = fetch_register.getStringExtra("email");
//                fetch_password_check = fetch_register.getStringExtra("password");
//
//
//
//
//                if(TextUtils.isEmpty(login_email.getText().toString()) && TextUtils.isEmpty(login_password.getText().toString())){
//                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
//
//                }
//                else{
//
//                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                    startActivity(intent);
//                    Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
//                }
////                if(email_value.equals(fetch_email_check) && password_value.equals(fetch_password_check)){
////                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
////                    startActivity(intent);
////                    Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
////                }
////                else{
////                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
////
////                }
//
//            }
//        });


    }
}