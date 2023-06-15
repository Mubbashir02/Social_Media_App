package com.example.firstapplication.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.firstapplication.Firebase.Post;
import com.example.firstapplication.HomeScreen;
import com.example.firstapplication.MainActivity;
import com.example.firstapplication.MapsActivity;
import com.example.firstapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PostFragment extends Fragment {

    public static final int PICK_IMAGE = 1;
    public static final int PICK_VIDEO = 1;
    public Button video_selector;
    public Button submit_btn;
    public EditText content_post;
    public String content;
    public ImageView image_uploader;
    public ImageView video_uploader;
    public ImageView profile_changer;
    public ImageView map_show;
    ArrayList<String> content_array = new ArrayList<String>();
    final  int GALLEY_REQ_CODE = 1000;


    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == -1){
            if(requestCode==1000){
                profile_changer.setImageURI(data.getData());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_post, container, false);

        profile_changer = (ImageView) view.findViewById(R.id.imageView3);
        map_show = (ImageView) view.findViewById(R.id.maps_icon);

        image_uploader = (ImageView) view.findViewById(R.id.imagep) ;

        video_uploader = (ImageView) view.findViewById(R.id.videoep);
        submit_btn = (Button) view.findViewById(R.id.submit_btn);
        content_post = (EditText) view.findViewById(R.id.content_post);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = content_post.getText().toString();
                if(content.isEmpty()){
                    Toast.makeText(getActivity().getApplicationContext(), "Please Add Content", Toast.LENGTH_LONG).show();

                }
                else{
                    Fragment fragment = new Fragment();
                    Date d=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
                    String currentDateTimeString = sdf.format(d);

                    Log.d("date_check", currentDateTimeString.toString());
                    content_array.add(content);
                    Log.d("Pass Content", content_array.toString());
                    DatabaseReference mDatabase;
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Date date = new Date();
                    FirebaseDatabase contentdata = FirebaseDatabase.getInstance();
                    Post post_data = new Post(mDatabase.push().getKey().toString(),content,String.valueOf(date.getTime()));

                    mDatabase.child("posts").push().setValue(post_data);
                    mDatabase.child("posts").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (!task.isSuccessful()) {
                                Log.e("firebase_post", "Error getting data", task.getException());
                            }
                            else {
                                Log.d("firebase_post2", String.valueOf(task.getResult().getValue()));
                            }

                        }
                    });

                    content_post.getText().clear();

//                    message_ref.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            String value = snapshot.getValue(String.class);
//                            Log.d("get_value_data_message", value);
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                            Log.w("debug_message", "Failed to read value.", error.toException());
//
//
//                        }
//                    });


    //                    Intent intent = new Intent(getActivity().getApplicationContext(), FeedFragment.class);
    //                    Bundle bundle = new Bundle();
    //                    bundle.putString("final_content",content_post.getText().toString());
    //                    bundle.putString("date_time",currentDateTimeString);
    //                    fragment.setArguments(bundle);
//                    FragmentTransaction transection=getFragmentManager().beginTransaction();
//                    FeedFragment feedfragmnet=new FeedFragment();
//                    Bundle bundle=new Bundle();
//                    bundle.putStringArrayList("contant_array",content_array);
//
//                    feedfragmnet.setArguments(bundle);
//                    transection.replace(R.id.post_fragment, feedfragmnet);
//                    transection.commit();
                    Toast.makeText(getActivity().getApplicationContext(), "Succesfully Added", Toast.LENGTH_LONG).show();

//                    startActivity(intent);


                    Toast.makeText(getActivity().getApplicationContext(), "Post Successfully", Toast.LENGTH_LONG).show();
                }


            }
        });



        image_uploader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent igallaery = new Intent(Intent.ACTION_PICK);
                igallaery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(igallaery,GALLEY_REQ_CODE);

//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_VIDEO);



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

        Spinner dropdown = view.findViewById(R.id.spinner1);
        String[] items = new String[]{"Friends", "Followers", "Public"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        map_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map_intent = new Intent(getActivity().getApplicationContext(), MapsActivity.class);
                startActivity(map_intent);

            }
        });





        return view;
    }

}