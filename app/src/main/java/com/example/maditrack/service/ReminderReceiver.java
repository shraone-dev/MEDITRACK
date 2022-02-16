package com.example.maditrack.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.example.maditrack.activity.AppointmentActivity;
import com.example.maditrack.activity.ReminderConsumptionActivity;
import com.example.maditrack.database.Constant;

import com.example.maditrack.R;

public class ReminderReceiver extends WakefulBroadcastReceiver {
    private NotificationManager notificationManager;
    private Notification notification;
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        String medicineName = intent.getExtras().getString(Constant.MedicineName);
        String quantity = intent.getExtras().getString(Constant.MedicineConsumeQuantity);
        String aptLocation = intent.getExtras().getString(Constant.LOCATION);

        if(medicineName != null){
            Intent popupIntent = new Intent(context, ReminderConsumptionActivity.class);

            popupIntent.putExtra(Constant.MedicineName, medicineName);
            popupIntent.putExtra(Constant.MedicineConsumeQuantity, quantity);

            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, intent.getIntExtra("ID", 10), popupIntent, PendingIntent.FLAG_UPDATE_CURRENT);

             notification = new NotificationCompat.Builder(context)
                    .setContentTitle("Medicine " + medicineName)
                    .setContentText(intent.getStringExtra("Message "))
                    .setSound(sound)
                    .setContentIntent(pendingIntent)
                    .addAction(0, Constant.MEDICINE_REMINDER_MESSAGE, pendingIntent)
                    .setSmallIcon(R.drawable.ic_medication)
                    .setAutoCancel(true)
                    .build();
        }
        else
        {
            Intent popupIntent = new Intent(context, AppointmentActivity.class);
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, intent.getIntExtra("ID", 10), popupIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            notification = new NotificationCompat.Builder(context)
                    .setContentTitle("Appointment: " + aptLocation)
                    .setContentText(intent.getStringExtra(Constant.MESSAGE))
                    .setSound(sound)
                    .setContentIntent(pendingIntent)
                    .addAction(0, Constant.NOTIFICATION_MESSAGE, pendingIntent)
                    .setSmallIcon(R.drawable.ic_medication)
                    .setAutoCancel(true)
                    .build();
        }
        notificationManager.notify(intent.getIntExtra("ID", 20), notification);
    }
}
