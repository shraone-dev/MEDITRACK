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
import com.example.maditrack.MediTracklFolder.Pressure;

import com.example.maditrack.R;

@RequiresApi(api = Build.VERSION_CODES.N)
public class PressureDisplayEditUpdateActivity extends AppCompatActivity {
    static EditText editdate;
    static EditText edittime;
    static Calendar selectedDate = Calendar.getInstance();
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    EditText editsystolic;
    EditText editdiastolic;
    Button updateSave;
    private int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure_display_edit_update);


        Intent intent = getIntent();
        id = intent.getExtras().getInt("measurementId");
        final String systolic = intent.getExtras().getString("systolic");
        final String diastolic = intent.getExtras().getString("Diastolic");
        final String MeasuredDateTime = intent.getExtras().getString("MeasuredOn");

        String[] splitedDateTime = MeasuredDateTime.split("_");
        final String measuredDate = splitedDateTime[0];
        final String measuredTime = splitedDateTime[1];


        editsystolic = (EditText) findViewById(R.id.systolic);
        editdiastolic = (EditText) findViewById(R.id.diastolic);
        editdate = (EditText) findViewById(R.id.Date);
        edittime = (EditText) findViewById(R.id.time);

        editsystolic.setText(systolic);
        editdiastolic.setText(diastolic);
        editdate.setText(measuredDate);
        edittime.setText(measuredTime);

        updateSave = (Button) findViewById(R.id.updatePressureToDb);
        updateSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    update(id, editsystolic.getText().toString(), editdiastolic.getText().toString(), editdate.getText().toString(), edittime.getText().toString());
                    finish();
                }
            }
        });
        editdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editdate.setText("");
                showDatePickerDialog(v);
            }
        });
        edittime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittime.setText("");
                showTimePickerDialog(v);
            }
        });
    }

    private void update(int id, String systolic, String diastolic, String editdate, String edittime) {
        Integer valueOfSystolic = Integer.valueOf(systolic);
        Integer valueOfDiastolic = Integer.valueOf(diastolic);
        StringBuffer dateTime = new StringBuffer(editdate);
        dateTime.append("_");
        dateTime.append(edittime);
        String editedMeasuredDate = dateTime.toString();

        Measurement editpressure = new Pressure(id, editedMeasuredDate, valueOfSystolic, valueOfDiastolic);
        editpressure.updateMeasurement(editpressure, getApplicationContext());
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
        if (TextUtils.isEmpty(editsystolic.getText().toString().trim())) {
            editsystolic.requestFocus();
            editsystolic.setError("please add systolic value");
            isValid = false;
        }
        if ((Integer.valueOf(editsystolic.getText().toString().trim()) <= 80) || (Integer.valueOf(editsystolic.getText().toString().trim()) >= 200)) {
            editsystolic.requestFocus();
            editsystolic.setError("Systolic value should be in range of 80-200");
            isValid = false;
        }

        if (TextUtils.isEmpty(editdiastolic.getText().toString().trim())) {
            editdiastolic.requestFocus();
            editdiastolic.setError("Please add diastolic value");
            isValid = false;
        }
        if ((Integer.valueOf(editdiastolic.getText().toString().trim()) <= 60) || (Integer.valueOf(editdiastolic.getText().toString().trim()) >= 150)) {
            editdiastolic.requestFocus();
            editdiastolic.setError("Diastolic value should be in range of 60-150");
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
