package com.example.maditrack.activity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.example.maditrack.MediTracklFolder.Reminder;
import com.example.maditrack.database.ReminderDAO;

import static android.R.attr.id;

import com.example.maditrack.R;

public class AddReminderActivity extends AppCompatActivity {

    EditText editLocation, editDate, editTime, editDescription;
    private EditText frequency;
    private EditText interval, reminderId;
    private Button save;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private int hour =0, min = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        editTime = (EditText) findViewById(R.id.reminder_time);
        frequency = (EditText) findViewById(R.id.frequency);
        interval = (EditText) findViewById(R.id.interval);
        save = (Button) findViewById(R.id.submit_area);

        View.OnClickListener timeClickListener = getTimePicker("");
        editTime.setOnClickListener(timeClickListener);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(frequency.getText().toString().trim(), interval.getText().toString().trim(), editTime.getText().toString());
            }
        });
    }

    @NonNull
    private View.OnClickListener getTimePicker(String time) {
        editTime.setText(time);
        return new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final EditText editText = (EditText) v;
                TimePickerDialog.OnTimeSetListener timeSetListener =
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                        calendar.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
                                editText.setText(timeFormatter.format(calendar.getTime()));
                            }
                        };
                Calendar timeCalendar = Calendar.getInstance();
                try {
                    timeCalendar.setTime(timeFormatter.parse(editText.getText().toString()));
                } catch (ParseException e) {

                }
                TimePickerDialog timePickerDialog =
                        new TimePickerDialog(AddReminderActivity.this, timeSetListener, timeCalendar.get(Calendar.HOUR_OF_DAY), timeCalendar.get(Calendar.MINUTE), false);
                timePickerDialog.show();
            }
        };
    }

    private void save(String frequency, String interval, String time) {
        int frequencyNumber = Integer.parseInt(frequency);
        int intervalNumber = Integer.parseInt(interval);

        String reminderTime[] = time.split(":");

        hour = Integer.parseInt(reminderTime[0]);
        min = Integer.parseInt(reminderTime[1]);
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);

        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Log.v("Input Date", "" + date);
        Reminder reminder = new Reminder(id, frequencyNumber, intervalNumber, df.format(date));
        ReminderDAO reminderDAO = new ReminderDAO(this);

        boolean result = reminderDAO.addReminder(reminder);

        if (result) {
            Toast.makeText(getApplicationContext(), "Added Reminder", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Unable to insert", Toast.LENGTH_SHORT).show();
        }
    }
}

