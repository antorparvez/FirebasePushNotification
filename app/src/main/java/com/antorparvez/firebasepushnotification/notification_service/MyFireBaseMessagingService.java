package com.antorparvez.firebasepushnotification.notification_service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.antorparvez.firebasepushnotification.MainActivity;
import com.antorparvez.firebasepushnotification.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFireBaseMessagingService extends FirebaseMessagingService {


    String title,message,photo;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        title = remoteMessage.getData().get("Title");
        message = remoteMessage.getData().get("Message");
        photo = remoteMessage.getData().get("Photo");

        goToToast();


/*
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle(title)
                        .setContentText(message);
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());*/

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);



        }

        Intent intent = new Intent(this, MainActivity.class);


        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);




        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notificationBuilder.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setVibrate(new long[]{1000, 1000})
                .setSound(defaultSoundUri)
                .setContentText(message).setContentIntent(pendingIntent);

        notificationManager.notify(/*notification id*/0, notificationBuilder.build());




    }

    private void goToToast() {

        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
    }


}
