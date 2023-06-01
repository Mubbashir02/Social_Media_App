package com.example.firstapplication.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firstapplication.Date_time;
import com.example.firstapplication.Likes;
import com.example.firstapplication.R;
import com.example.firstapplication.User;
import com.example.firstapplication.recycleAdapter;
import com.example.firstapplication.utils.SpacingitemDecoration;

import java.util.ArrayList;


public class FeedFragment extends Fragment {

    private ArrayList<User> userlist;
    public ArrayList<String> contentlist;
    String[] static_content = {"a member of the British House of Lords who votes for a particular motion.","nothing would content her apart from going off to Barcelona","nothing would content her apart from going off to Barcelona"};
    private ArrayList<Date_time> date_timeslist;
    private ArrayList<Likes> no_like_list;
    public String final_content;
    public String date_time;

    private RecyclerView recyclerView;

    public FeedFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_feed, container, false);

        recyclerView = view.findViewById(R.id.recycle1);
        userlist = new ArrayList<>();
        contentlist = new ArrayList<String>();
        date_timeslist = new ArrayList<>();
        no_like_list = new ArrayList<>();

        Log.d("total_user", String.valueOf(static_content.length));

        setUserInfo();
        setAdapter();

        SpacingitemDecoration spacing = new SpacingitemDecoration(20) ;
        recyclerView.addItemDecoration(spacing);

        return view;
    }
    private void setAdapter() {
        for(int i=0; i<static_content.length;i++){

            recycleAdapter adapter = new recycleAdapter(userlist);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }

    }

    private void setUserInfo() {

        for(int i=0; i<static_content.length;i++){
            userlist.add(new User(getString(R.string.profile_name),static_content[i],"2h","5"));
        }




    }
}