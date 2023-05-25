package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    private ArrayList<User> userlist;
    private ArrayList<Content> contentlist;
    private ArrayList<Date_time> date_timeslist;
    private ArrayList<Likes> no_like_list;
    public String final_content;
    public String date_time;




    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        recyclerView = findViewById(R.id.recycle1);
        userlist = new ArrayList<>();
        contentlist = new ArrayList<>();
        date_timeslist = new ArrayList<>();
        no_like_list = new ArrayList<>();

        Intent intent = getIntent();
        final_content = intent.getStringExtra("final_content");

        date_time = intent.getStringExtra("date_time");

        setUserInfo();

        setAdapter();


    }
    private void setAdapter() {

        recycleAdapter adapter = new recycleAdapter(userlist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setUserInfo() {

            userlist.add(new User(getString(R.string.profile_name),final_content,date_time,"5"));


    }

}