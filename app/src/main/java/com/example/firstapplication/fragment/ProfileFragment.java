package com.example.firstapplication.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.firstapplication.MainActivity;
import com.example.firstapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    ImageButton user_profile_photo;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 22;


    FirebaseStorage storage;
    StorageReference storageReference;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == -1){
            if(requestCode==1000){
                user_profile_photo.setImageURI(data.getData());
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_profile, container, false);


        String[] friendlist = {"Sohail","Hussain","Sufyan","Ahmed","Hamza"};
        String[] followerslist = {"Huzaifa","Adam"};
        ListView freindlist;
        ListView followerlist;
        ArrayList<String> friendstore;
        ArrayList<String> followerstore;
        ArrayAdapter<String> arrayAdapter;
        ArrayAdapter<String> followerarrayAdapter;
        Integer totalfriends = friendlist.length;
        Integer totalfollower = followerslist.length;
        ImageView postingback_btn2;
        ImageView profile_pic_editor;
        final  int GALLEY_REQ_CODE = 1000;


        profile_pic_editor = (ImageView) view.findViewById(R.id.profile_image_editor);

        user_profile_photo = (ImageButton) view.findViewById(R.id.user_profile_photo);
        profile_pic_editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent igallaery = new Intent(Intent.ACTION_PICK);
                igallaery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(igallaery,GALLEY_REQ_CODE);


            }
        });
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        freindlist = (ListView) view.findViewById(R.id.friend_listview);
        friendstore = new ArrayList<>();
        for(int i = 0; i <friendlist.length;i++){
            friendstore.add(friendlist[i]);
        }
        arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,friendstore);
        freindlist.setAdapter(arrayAdapter);
//
//        follower work
        followerlist = (ListView) view.findViewById(R.id.follower_listview);
        followerstore = new ArrayList<>();
        for(int j = 0; j <followerslist.length;j++){
            followerstore.add(followerslist[j]);
        }
        followerarrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,followerstore);
        followerlist.setAdapter(followerarrayAdapter);





        return view;
    }
}