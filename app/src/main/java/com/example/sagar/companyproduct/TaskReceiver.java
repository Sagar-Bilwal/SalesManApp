package com.example.sagar.companyproduct;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by SAGAR on 01-05-2018.
 */

public class TaskReceiver extends BroadcastReceiver
{
    String CHANNEL_ID="TASK";
    @SuppressLint("ResourceAsColor")
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        AlarmManager alarmManager=new AlarmManager();

        Intent intent2=new Intent(context,AlarmManager.class);
        context.startService(intent2);
        //alarmManager.onStartCommand(intent2,0,1);
        NotificationChannel mChannel;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            CharSequence name = "TakeNotes";
            String description = "reminderChannel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(description);
            // Register the channel with the system; you can't change the importance

            if (manager != null) {
                manager.createNotificationChannel(mChannel);
            }
            // or other notification behaviors after this

        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setPriority(NotificationManager.IMPORTANCE_HIGH);
        }
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("You Have a task today"));
        builder.setSmallIcon(R.drawable.background);
        builder.setColor(R.color.colorPrimaryDark);
        builder.setContentTitle("You Have a Meeting Today");
        builder.setAutoCancel(true);


        //builder.setDeleteIntent(pendingIntent3)
        Intent intent1 = new Intent(context,MainUserActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,2,intent1,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setSmallIcon(R.drawable.background);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();

        if (manager != null) {
            manager.notify(0,notification);
        }


    }
}
