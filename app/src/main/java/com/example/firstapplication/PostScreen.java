package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PostScreen extends AppCompatActivity {



    public static final int PICK_IMAGE = 1;
    public static final int PICK_VIDEO = 1;
    public Button video_selector;
    public Button submit_btn;
    public ImageView postingback_btn;
    public EditText content_post;
    public String content;
    public ImageView image_uploader;
    public ImageView video_uploader;
//    public String[] content_array ;
    ArrayList<String> content_array = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_screen);

        image_uploader = (ImageView) findViewById(R.id.imagep) ;

        video_uploader = (ImageView) findViewById(R.id.videoep);
        submit_btn = (Button) findViewById(R.id.submit_btn);
        content_post = (EditText) findViewById(R.id.content_post);


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = content_post.getText().toString();
                if(content.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Add Content", Toast.LENGTH_LONG).show();

                }
                else{
                    Date d=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
                    String currentDateTimeString = sdf.format(d);
                    Log.d("date_check", currentDateTimeString.toString());
                    content_array.add(content);
                    Intent intent = new Intent(getApplicationContext(),HomeScreen.class);
                    intent.putExtra("final_content",content_post.getText().toString());
                    intent.putExtra("date_time",currentDateTimeString);
                    startActivity(intent);
                    Log.d("Pass Content", content);

                    Toast.makeText(getApplicationContext(), "Post Successfully", Toast.LENGTH_LONG).show();
                }


            }
        });
        postingback_btn = (ImageView) findViewById(R.id.posting_back);
        postingback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


        image_uploader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_VIDEO);
            }
        });
        video_uploader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Video"), PICK_VIDEO);
            }
        });

        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Friends", "Followers", "Public"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


    }


}