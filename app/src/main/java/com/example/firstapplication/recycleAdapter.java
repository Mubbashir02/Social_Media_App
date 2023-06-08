package com.example.firstapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        public ImageView share;
        public ImageView three_dots;

        public MyViewHolder(final View view){
            super(view);

            nametext = view.findViewById(R.id.feed_user);
            contenttext = view.findViewById(R.id.feed_content);
            date_timetext = view.findViewById(R.id.feed_datetime);
            no_likes_text = view.findViewById(R.id.no_likes);
            share = view.findViewById(R.id.share);
            three_dots = view.findViewById(R.id.threedots);

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

        myViewHolder.three_dots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog((Activity) v.getContext());
            }
        });

        myViewHolder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String body = "Your body here";
                String sub = "Your Subject";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
                myIntent.putExtra(Intent.EXTRA_TEXT,body);
                v.getContext().startActivity(Intent.createChooser(myIntent, "Share Using"));


            }
        });


    }
    private void showDialog(Activity activity) {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout editLayout = dialog.findViewById(R.id.layoutEdit);
        LinearLayout shareLayout = dialog.findViewById(R.id.layoutShare);
        LinearLayout uploadLayout = dialog.findViewById(R.id.layoutUpload);
        LinearLayout printLayout = dialog.findViewById(R.id.layoutPrint);

        editLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(v.getContext(),"Edit is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        shareLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(v.getContext(),"Share is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        uploadLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(v.getContext(),"Upload is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        printLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(v.getContext(),"Print is Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }




}
