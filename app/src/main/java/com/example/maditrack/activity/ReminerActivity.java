package com.example.maditrack.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.maditrack.MediTracklFolder.Reminder;
import com.example.maditrack.database.ReminderDAO;

import com.example.maditrack.R;

public class ReminerActivity extends AppCompatActivity {

    Reminder reminder = new Reminder();
    ReminderDAO reminderDAO;
    private RecyclerView reminderrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        reminderDAO = new ReminderDAO(getApplicationContext());

       /* Cursor cursor = reminder.getReminder(reminderDAO);

        ArrayList<String> reminderTimeList = new ArrayList<String>();
        while (cursor.moveToNext()) {
            //int id = cursor.getInt(0);
            String reminderTime = cursor.getString(2);
            reminderTimeList.add(reminderTime);
        }

        for (String remiderTime: reminderTimeList) {

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String currentTime = sdf.format(cal.getTime());

            if(currentTime == remiderTime) {
                NotifyUser();
            }
        }
*/
    }

    private void NotifyUser() {

        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, ReminderNotificationReceiver.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject").setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentIntent(pIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);
    }
}
