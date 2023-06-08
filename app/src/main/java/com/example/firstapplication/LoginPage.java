package com.example.firstapplication;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    public EditText login_email;
    public EditText login_password;
    public Button login_btn;
    public TextView register_move;
    public String email_value;
    public String password_value;
//    public String fetch_email_check;
//    public String fetch_password_check;
    private FirebaseAuth auth;


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

        auth = FirebaseAuth.getInstance();

        sharedPreferences=getSharedPreferences("MainActivity",MODE_PRIVATE);
        shared_intent = new Intent(LoginPage.this, MainActivity.class);
        if(sharedPreferences.contains("firebase_fetch_email_check") && sharedPreferences.contains("firebase_fetch_password_check")){
            startActivity(shared_intent);
        }

//        Intent fetch_register = getIntent();
//        login_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                login_by_firbase(email_value,password_value);
//
//                fetch_email_check = fetch_register.getStringExtra("email");
//                fetch_password_check = fetch_register.getStringExtra("password");
//
//
//
//
//                if(login_email.getText().toString().equals("admin") && login_password.getText().toString().equals("123")){
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("fetch_email_check",email_value);
//                    editor.putString("fetch_password_check",password_value);
//                    editor.commit();
//                    startActivity(shared_intent);
////                    Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
//                }
//
//                else if(TextUtils.isEmpty(login_email.getText().toString()) && TextUtils.isEmpty(login_password.getText().toString())){
//                    Toast.makeText(getApplicationContext(), "Empty Value Found", Toast.LENGTH_LONG).show();
//
//                }
//                else{
//
//                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
//
//
//
//                }
//
//
//
//
//            }
//        });

        register_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterScreen.class);
                startActivity(intent);
            }
        });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                fetch_email_check = fetch_register.getStringExtra("email");
//                fetch_password_check = fetch_register.getStringExtra("password");

//                if(TextUtils.isEmpty(login_email.getText().toString()) && TextUtils.isEmpty(login_password.getText().toString())){
//                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
//
//                }
                email_value = login_email.getText().toString();
                password_value = login_password.getText().toString();
                login_by_firbase(email_value,password_value);
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

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(auth.getCurrentUser() != null){
//            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//            startActivity(intent);
//        }
//    }

    private void login_by_firbase(String email, String password) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("firebase_fetch_email_check",email);
        editor.putString("firebase_fetch_password_check",password);
        editor.commit();

        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        auth.signInWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginPage.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }
}