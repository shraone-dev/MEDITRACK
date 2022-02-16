package com.example.maditrack.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.example.maditrack.database.Constant;

public class ReminderService extends IntentService {

    public static final String CREATE = "CREATE";
    public static final String CANCEL = "CANCEL";
    private static final String TAG = "ReminderService";
    public static final String MEDICINE = "Medicine";
    public static final String APPOINTMENT = "Appointment";
    private IntentFilter matcher;

    public ReminderService() {
        super(TAG);
        matcher = new IntentFilter();
        matcher.addAction(CREATE);
        matcher.addAction(CANCEL);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String action = intent.getAction();
        String type = intent.getStringExtra("Type");
        int reminderID = 0, frequency = 0, interval = 0;
        String reminderTime = "", medicineName = "", consumeQty = "", reminderMessage = "", appointmentDate = "", appointmentLocation = "", appointmentDesc = "", appointmentMsg = "";

        if (type.equalsIgnoreCase(MEDICINE)) {
            reminderID = intent.getIntExtra(Constant.COLUMN_ID, 0);
            reminderTime = intent.getStringExtra(Constant.REMINDERDATETIME);
            frequency = intent.getIntExtra(Constant.FREQUENCY, 0);
            interval = intent.getIntExtra(Constant.INTERVAL, 0);
            medicineName = intent.getStringExtra(Constant.MedicineName);
            consumeQty = intent.getStringExtra(Constant.MedicineConsumeQuantity);
            reminderMessage = intent.getStringExtra("Message");
        } else if (type.equalsIgnoreCase(APPOINTMENT))
        {
            appointmentDate = intent.getStringExtra(Constant.APPOINTMENTDATE);
            appointmentLocation = intent.getStringExtra(Constant.LOCATION);
            appointmentMsg = intent.getStringExtra(Constant.MESSAGE);
        }

        if (matcher.matchAction(action)) {
            if (CREATE.equals(action)) {
                if (type.equalsIgnoreCase(MEDICINE)) {
                    executeMedicineReminder(CREATE, reminderID, reminderMessage, reminderTime, frequency, interval, medicineName, consumeQty);
                }
                else if(type.equalsIgnoreCase(APPOINTMENT))
                {
                    executeAppointmentReminder(CREATE, appointmentMsg, appointmentDate, appointmentLocation);
                }
            }
            if (CANCEL.equals(action)) {
                if (type.equalsIgnoreCase(MEDICINE)) {
                    executeMedicineReminder(CANCEL, reminderID, reminderMessage, reminderTime, frequency, interval, medicineName, consumeQty);
                }
                else if(type.equalsIgnoreCase(APPOINTMENT))
                {
                    executeAppointmentReminder(CANCEL, appointmentMsg, appointmentDate, appointmentLocation);
                }
            }
        }
    }

    private void executeMedicineReminder(String action, int reminderID, String msg, String reminderTime, int frequency, int interval, String mediName, String consumeQty) {
        Intent intent;
        PendingIntent pi;
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = getReminderDateTime(reminderTime);

        int input = (int) (new Date()).getTime();
        intent = new Intent(this, ReminderReceiver.class);
        intent.putExtra("ID", input);
        intent.putExtra("Message", msg);
        intent.putExtra(Constant.MedicineName, mediName);
        intent.putExtra(Constant.MedicineConsumeQuantity, consumeQty);

        pi = PendingIntent.getBroadcast(this, input, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (CREATE.equals(action)) {
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
            int i = 1;
            while(i < frequency ){
                calendar.add(Calendar.HOUR_OF_DAY, interval);
                am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pi);
                i++;
            }
        } else if (CANCEL.equals(action)) {
            am.cancel(pi);
            pi.cancel();
        }
    }

    private void executeAppointmentReminder(String action, String msg, String reminderDate, String appointmentLocation) {
        Intent intent;
        PendingIntent pi;
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = getReminderDateTime(reminderDate);
        int input = (int) (new Date()).getTime();

        intent = new Intent(this, ReminderReceiver.class);
        intent.putExtra(Constant.COLUMN_ID, input);
        intent.putExtra(Constant.MESSAGE, msg);
        intent.putExtra(Constant.LOCATION, appointmentLocation);

        pi = PendingIntent.getBroadcast(this, input, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (CREATE.equals(action)) {
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        } else if (CANCEL.equals(action)) {
            am.cancel(pi);
            pi.cancel();
        }
    }

    @NonNull
    private Calendar getReminderDateTime(String reminderTime) {
        Date reminderDateTime = new Date();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
        try {
            reminderDateTime = df.parse(reminderTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reminderDateTime);
        return calendar;
    }
}
