package com.example.maditrack.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.maditrack.service.ReminderService;

public class ReminderSetter extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, ReminderService.class);
        service.setAction(ReminderService.CREATE);
        context.startService(service);
    }
}
