package com.example.firstapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;



import java.util.ArrayList;

public class ProfileScreen extends AppCompatActivity {


    String[] friendlist = {"Sohail","Hussain","Sufyan","Ahmed","Hamza"};
    String[] followerslist = {"Huzaifa","Adam"};
    public ListView freindlist;
    public ListView followerlist;
    public ArrayList<String> friendstore;
    public ArrayList<String> followerstore;
    public ArrayAdapter<String> arrayAdapter;
    public ArrayAdapter<String> followerarrayAdapter;
    Integer totalfriends = friendlist.length;
    Integer totalfollower = followerslist.length;
    public ImageView postingback_btn2;
    public ImageView profile_pic_editor;
    public ImageButton user_profile_photo;
    private final  int GALLEY_REQ_CODE = 1000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        postingback_btn2 = (ImageView) findViewById(R.id.posting_back2);
        profile_pic_editor = (ImageView) findViewById(R.id.profile_image_editor);

        user_profile_photo = (ImageButton) findViewById(R.id.user_profile_photo);
        profile_pic_editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent igallaery = new Intent(Intent.ACTION_PICK);
                igallaery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(igallaery,GALLEY_REQ_CODE);
            }
        });

        postingback_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        freindlist = (ListView) findViewById(R.id.friend_listview);
        friendstore = new ArrayList<>();
        for(int i = 0; i <friendlist.length;i++){
            friendstore.add(friendlist[i]);
        }
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,friendstore);
        freindlist.setAdapter(arrayAdapter);
//
//        follower work
        followerlist = (ListView) findViewById(R.id.follower_listview);
        followerstore = new ArrayList<>();
        for(int j = 0; j <followerslist.length;j++){
            followerstore.add(followerslist[j]);
        }
        followerarrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,followerstore);
        followerlist.setAdapter(followerarrayAdapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode==GALLEY_REQ_CODE){
                user_profile_photo.setImageURI(data.getData());
            }
        }
    }
}