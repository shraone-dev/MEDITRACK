package com.example.maditrack.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

import com.example.maditrack.MediTracklFolder.Measurement;
import com.example.maditrack.MediTracklFolder.Pulse;

import com.example.maditrack.R;

@RequiresApi(api = Build.VERSION_CODES.N)
public class PulseDisplayEditUpdateActivity extends AppCompatActivity {
    static EditText editdate;
    static EditText edittime;
    static Calendar selectedDate = Calendar.getInstance();
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    EditText editpulse;
    Button updateSave;
    private int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulse_display_edit_update);


        Intent intent = getIntent();
        id = intent.getExtras().getInt("measurementId");
        final String pulse = intent.getExtras().getString("pulse");
        final String MeasuredDateTime = intent.getExtras().getString("MeasuredOn");

        String[] splitedDateTime = MeasuredDateTime.split("_");
        final String measuredDate = splitedDateTime[0];
        final String measuredTime = splitedDateTime[1];

        editpulse = (EditText) findViewById(R.id.pulse);
        editdate = (EditText) findViewById(R.id.Date);
        edittime = (EditText) findViewById(R.id.time);

        editpulse.setText(pulse);
        editdate.setText(measuredDate);
        edittime.setText(measuredTime);

        updateSave = (Button) findViewById(R.id.updateToPulseDb);
        updateSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    update(id, editpulse.getText().toString(), editdate.getText().toString(), edittime.getText().toString());
                    finish();
                }
            }
        });
        editdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
        edittime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });

    }

    private void update(int id, String pulse, String editdate, String edittime) {
        Integer valueOfPulse = Integer.valueOf(pulse);
        StringBuffer dateTime = new StringBuffer(editdate);
        dateTime.append("_");
        dateTime.append(edittime);
        String editedMeasuredDate = dateTime.toString();

        Measurement editpulse = new Pulse(id, editedMeasuredDate, valueOfPulse);
        editpulse.updateMeasurement(editpulse, getApplicationContext());
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private boolean isValid() {
        boolean isValid = true;
        if (TextUtils.isEmpty(editpulse.getText().toString().trim())) {
            editpulse.requestFocus();
            editpulse.setError("please add pulse value");
            isValid = false;
        }
        if ((Integer.valueOf(editpulse.getText().toString().trim()) <= 60) || (Integer.valueOf(editpulse.getText().toString().trim()) >= 200)) {
            editpulse.requestFocus();
            editpulse.setError("Pulse value should be in range of 60-200");
            isValid = false;
        }
        if (TextUtils.isEmpty(editdate.getText().toString().trim())) {
            editdate.requestFocus();
            editdate.setError("please add date");
            isValid = false;
        }
        if (TextUtils.isEmpty(edittime.getText().toString().trim())) {
            edittime.requestFocus();
            edittime.setError("please add time");
            isValid = false;
        }
        return isValid;
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar calender = Calendar.getInstance();
            int year = calender.get(Calendar.YEAR);
            int month = calender.get(Calendar.MONTH);
            int day = calender.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            selectedDate = calendar;
            editdate.setText(dateFormatter.format(selectedDate.getTime()));
        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar calender = Calendar.getInstance();
            int hour = calender.get(Calendar.HOUR_OF_DAY);
            int minute = calender.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            int hour = hourOfDay;
            int min = minute;
            edittime.setText(hour + ":" + minute);
        }
    }

}
