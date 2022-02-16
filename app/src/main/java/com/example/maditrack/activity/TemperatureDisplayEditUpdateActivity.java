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
import com.example.maditrack.MediTracklFolder.Temperature;

import com.example.maditrack.R;

@RequiresApi(api = Build.VERSION_CODES.N)
public class TemperatureDisplayEditUpdateActivity extends AppCompatActivity {
    static EditText editdate;
    static EditText edittime;
    static Calendar selectedDate = Calendar.getInstance();
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    EditText edittemperature;
    Button updateSave;
    private int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_display_edit_update);


        Intent intent = getIntent();
        id = intent.getExtras().getInt("measurementId");
        final String temperature = intent.getExtras().getString("temperature");
        final String MeasuredDateTime = intent.getExtras().getString("MeasuredOn");

        String[] splitedDateTime = MeasuredDateTime.split("_");
        final String measuredDate = splitedDateTime[0];
        final String measuredTime = splitedDateTime[1];


        edittemperature = (EditText) findViewById(R.id.temperature);
        editdate = (EditText) findViewById(R.id.Date);
        edittime = (EditText) findViewById(R.id.time);

        edittemperature.setText(temperature);
        editdate.setText(measuredDate);
        edittime.setText(measuredTime);

        updateSave = (Button) findViewById(R.id.updateToTemperatureDb);
        updateSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    update(id, edittemperature.getText().toString(), editdate.getText().toString(), edittime.getText().toString());
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

    private void update(int id, String temperature, String editdate, String edittime) {
        Float valueOfTemperature = Float.valueOf(temperature);
        StringBuffer dateTime = new StringBuffer(editdate);
        dateTime.append("_");
        dateTime.append(edittime);
        String editedMeasuredDate = dateTime.toString();

        Measurement edittemperature = new Temperature(id, editedMeasuredDate, valueOfTemperature);
        edittemperature.updateMeasurement(edittemperature, getApplicationContext());
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
        if (TextUtils.isEmpty(edittemperature.getText().toString().trim())) {
            edittemperature.requestFocus();
            edittemperature.setError("please add temperature value");
            isValid = false;
        }
        if ((Float.valueOf(edittemperature.getText().toString().trim()) <= 95) || (Float.valueOf(edittemperature.getText().toString().trim()) >= 110)) {
            edittemperature.requestFocus();
            edittemperature.setError("Temperature value should be in range of 60-200");
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
