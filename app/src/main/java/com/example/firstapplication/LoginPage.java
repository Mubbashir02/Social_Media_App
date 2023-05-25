package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        login_email = (EditText) findViewById(R.id.email_signin);
        login_password = (EditText) findViewById(R.id.password_signin);
        login_btn = (Button) findViewById(R.id.login_btn);
        register_move = (TextView) findViewById(R.id.register_move);
        register_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterScreen.class);
                startActivity(intent);
            }
        });
        Intent fetch_register = getIntent();


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_value = login_email.getText().toString();
                password_value = login_password.getText().toString();
                fetch_email_check = fetch_register.getStringExtra("email");
                fetch_password_check = fetch_register.getStringExtra("password");

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
//                if(email_value.equals(fetch_email_check) && password_value.equals(fetch_password_check)){
//                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                    startActivity(intent);
//                    Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
//
//                }

            }
        });


    }
}