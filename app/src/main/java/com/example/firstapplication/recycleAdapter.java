package com.example.firstapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder> {

    private ArrayList<User> userlist;

    public recycleAdapter(ArrayList<User> userlist){

        this.userlist = userlist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView nametext;
        private TextView contenttext;
        private TextView date_timetext;
        private TextView no_likes_text;

        public MyViewHolder(final View view){
            super(view);

            nametext = view.findViewById(R.id.feed_user);
            contenttext = view.findViewById(R.id.feed_content);
            date_timetext = view.findViewById(R.id.feed_datetime);
            no_likes_text = view.findViewById(R.id.no_likes);

        }
    }

    @NonNull
    @Override
    public recycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view_item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new MyViewHolder(view_item);
    }

    @Override
    public void onBindViewHolder(@NonNull recycleAdapter.MyViewHolder myViewHolder, int position) {

        String name = userlist.get(position).getUsername();
        myViewHolder.nametext.setText(name);
        String content = userlist.get(position).getContent();
        myViewHolder.contenttext.setText(content);
        String date_time = userlist.get(position).getDate_time();
        myViewHolder.date_timetext.setText(date_time);
        String no_likes = userlist.get(position).getNo_likes();
        myViewHolder.no_likes_text.setText(no_likes);

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

}
