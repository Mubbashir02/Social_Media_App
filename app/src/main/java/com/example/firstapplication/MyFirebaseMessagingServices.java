package com.example.firstapplication;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingServices extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        if(message.getNotification() !=null){
            String title = message.getNotification().getTitle();
            Log.d("Title_Message", title);
            String body = message.getNotification().getBody();

            NotificationHelper.displayNotification(getApplicationContext(),title,body);
        }
    }
}
