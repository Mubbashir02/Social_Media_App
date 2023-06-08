package com.example.firstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;


public class MainActivity extends AppCompatActivity {


    SharedPreferences sharedPreferences;

    BottomNavigationView navigationView;

    public static final String CHANNEL_ID = "firstNotification";
    public static final String CHANNEL_NAME = "first Notification";
    private static final String CHANNEL_DESC = "first Notification Description";
    public FirebaseAuth auth;
    public UsersToken user;
    public DatabaseReference dbUser;
    public String token;
    FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();






    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

//    private void displayNotification(){
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,CHANNEL_ID).setSmallIcon(R.drawable.profile)
//                .setContentTitle("Hurray").setContentText("Your First Message").setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
//        notificationManagerCompat.notify(1,mBuilder.build());
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(auth.getCurrentUser() == null){
//            Intent intent = new Intent(getApplicationContext(),LoginPage.class);
//            startActivity(intent);
//        }
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();


        sharedPreferences=getSharedPreferences("MainActivity",MODE_PRIVATE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        mUser.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<GetTokenResult> task) {
                if (task.isSuccessful()) {
                    token = task.getResult().getToken();
                    Log.d("TAG_Token", token);
                    savetoken(token);
                } else {
                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Not Completed Message", Toast.LENGTH_SHORT).show();
                        }
                        // Get new FCM registration token
                        token = task.getResult();


                    }
                });

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
//                            displayNotification();

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

                            exit_dialog(MainActivity.this);




                        }

                        return true;
                    }
                }
        );
    }

    public void savetoken(String token_str) {
        String email = auth.getCurrentUser().getEmail();
        user = new UsersToken(email,token_str);
        dbUser = FirebaseDatabase.getInstance().getReference("users");
        try{
            dbUser.child(auth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("problem12", e.getMessage());

        }


    }
    public void exit_dialog(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                        startActivity(intent);
                        finish();
                        activity.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


}


