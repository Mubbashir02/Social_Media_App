package com.example.firstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import org.w3c.dom.Text;

public class RegisterScreen extends AppCompatActivity {



    public EditText email_register;
    public EditText password_register;
    public EditText confirm_password_register;
    public EditText phone_register;
    public TextView sign_move;
    public String register_email_value;
    public String register_password_value;
    public String confirm_register_password_value;
    public String register_phone_value;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    String passwordVal = "^" +
            //"(?=.*[0-9])" +         //at least 1 digit
            //"(?=.*[a-z])" +         //at least 1 lower case letter
            //"(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{4,}" +               //at least 4 characters
            "$";



    public Button Register_btn;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);



        email_register = (EditText) findViewById(R.id.email_signup);
        password_register = (EditText) findViewById(R.id.password_signup);
        phone_register = (EditText) findViewById(R.id.phone_signup);
        confirm_password_register = (EditText) findViewById(R.id.confirmpassword_signup);
        Register_btn = (Button) findViewById(R.id.register_btn);
        sign_move = (TextView)findViewById(R.id.register_move);
        auth =FirebaseAuth.getInstance();



        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register_email_value = email_register.getText().toString();
                register_password_value= password_register.getText().toString();
                confirm_register_password_value= confirm_password_register.getText().toString();
                register_phone_value = phone_register.getText().toString();

                if(register_email_value.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Email is empty", Toast.LENGTH_LONG).show();

                } else if (!register_email_value.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "Invalid Email Address Email Must Contain a-z , A-z & @", Toast.LENGTH_LONG).show();

                } else if(!register_password_value.matches(passwordVal)){
                    Toast.makeText(getApplicationContext(), "Password is weak", Toast.LENGTH_LONG).show();

                }
                else if (register_password_value.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "password is empty", Toast.LENGTH_LONG).show();

                }
                else if (register_phone_value.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "phone is empty", Toast.LENGTH_LONG).show();

                }
                else if (confirm_register_password_value.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Confirm Password is empty", Toast.LENGTH_LONG).show();

                }
                else if(!register_password_value.equals(confirm_register_password_value)){
                    Toast.makeText(getApplicationContext(), "Confirm password must be same to password ", Toast.LENGTH_LONG).show();
                }
                else{
                    FirebaseRegisterUser(register_email_value,register_password_value);
                    Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                    intent.putExtra("email",register_email_value);
                    intent.putExtra("password",register_password_value);
                    startActivity(intent);
                    Toast.makeText(RegisterScreen.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                }


            }
        });

        sign_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
            }
        });

    }

    private void FirebaseRegisterUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterScreen.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterScreen.this, "Data POST", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RegisterScreen.this, "Data Not Post", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}