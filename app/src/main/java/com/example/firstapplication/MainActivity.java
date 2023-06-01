package com.example.firstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.firstapplication.fragment.FeedFragment;
import com.example.firstapplication.fragment.PostFragment;
import com.example.firstapplication.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    SharedPreferences sharedPreferences;

    BottomNavigationView navigationView;


    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences("MainActivity",MODE_PRIVATE);




        navigationView = findViewById(R.id.bottomnavigator);
        navigationView.setItemIconTintList(null);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        FeedFragment feedFragment = new FeedFragment();
        ft.add(R.id.container,feedFragment);
        ft.commit();


        navigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if(item.getItemId()== R.id.myFeed){

                            FragmentManager manager = getSupportFragmentManager();
                            FragmentTransaction ft = manager.beginTransaction();

                            FeedFragment feedFragment = new FeedFragment();
                            ft.add(R.id.container,feedFragment);
                            ft.commit();

                        } else if (item.getItemId()== R.id.myprofile) {
                            FragmentManager manager = getSupportFragmentManager();
                            FragmentTransaction ft = manager.beginTransaction();

                            ProfileFragment profileFragment = new ProfileFragment();
                            ft.add(R.id.container,profileFragment);
                            ft.commit();

                        } else if (item.getItemId()== R.id.myPost) {
                            FragmentManager manager = getSupportFragmentManager();
                            FragmentTransaction ft = manager.beginTransaction();

                            PostFragment postfragment = new PostFragment();
                            ft.add(R.id.container,postfragment);
                            ft.commit();


                        } else if (item.getItemId()== R.id.mylogout) {

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                            startActivity(intent);
                            finish();

                        }

                        return true;
                    }
                }
        );
    }

}


