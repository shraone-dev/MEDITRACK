package com.example.maditrack.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
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
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

import com.example.maditrack.MediTracklFolder.Measurement;
import com.example.maditrack.MediTracklFolder.Pressure;

import com.example.maditrack.R;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AddPressureActivity extends AppCompatActivity {

    static Calendar selectedDate = Calendar.getInstance();
    private static EditText DateEdit;
    private static EditText TimeEdit;
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    Button pressureSave;
    EditText systolic;
    EditText diastolic;
    TextView systolicunit;
    TextView diastolicunit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressureadd);

        DateEdit = (EditText) findViewById(R.id.Date);
        systolic = (EditText) findViewById(R.id.systolic);
        diastolic = (EditText) findViewById(R.id.diastolic);
        TimeEdit = (EditText) findViewById(R.id.time);
        systolicunit = (TextView) findViewById(R.id.systolic_unit);
        diastolicunit = (TextView) findViewById(R.id.diastolic_unit);

        DateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
        TimeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });

        pressureSave = (Button) findViewById(R.id.AddPressureToDb);
        pressureSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    AddPressureValue(systolic.getText().toString(), diastolic.getText().toString(), DateEdit.getText().toString(), TimeEdit.getText().toString());
                   /* Intent i = new Intent(getApplicationContext(), PressureFragment.class);
                    startActivity(i);*/

                    finish();
                }
            }
        });
    }


    private void AddPressureValue(String systolic, String diastolic, String DateEdit, String TimeEdit) {
        Integer systolicValue = Integer.valueOf(systolic);
        Integer diastolicValue = Integer.valueOf(diastolic);
        StringBuffer dateTime = new StringBuffer(DateEdit);
        dateTime.append("_");
        dateTime.append(TimeEdit);
        String measuredDate = dateTime.toString();

        Measurement pressure1 = new Pressure(0, measuredDate, systolicValue, diastolicValue);
        pressure1.addMeasurement(pressure1, getApplicationContext());

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
        if (TextUtils.isEmpty(systolic.getText().toString().trim())) {
            systolic.requestFocus();
            systolic.setError("please add systolic value");
            isValid = false;
        }

        if ((Integer.valueOf(systolic.getText().toString().trim()) <= 80) || (Integer.valueOf(systolic.getText().toString().trim()) >= 200)) {
            systolic.requestFocus();
            systolic.setError("Systolic value should be in range of 80-200");
            isValid = false;
        }

        if (TextUtils.isEmpty(diastolic.getText().toString().trim())) {
            diastolic.requestFocus();
            diastolic.setError("Please add diastolic value");
            isValid = false;
        }
        if ((Integer.valueOf(diastolic.getText().toString().trim()) <= 60) || (Integer.valueOf(diastolic.getText().toString().trim()) >= 150)) {
            diastolic.requestFocus();
            diastolic.setError("Diastolic value should be in range of 60-150");
            isValid = false;
        }
        if (TextUtils.isEmpty(DateEdit.getText().toString().trim())) {
            DateEdit.requestFocus();
            DateEdit.setError("please add date");
            isValid = false;
        }
        if (TextUtils.isEmpty(TimeEdit.getText().toString().trim())) {
            TimeEdit.requestFocus();
            TimeEdit.setError("please add time");
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
            DateEdit.setText(dateFormatter.format(selectedDate.getTime()));
        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            Calendar calender = Calendar.getInstance();
            int hour = calender.get(Calendar.HOUR_OF_DAY);
            int minute = calender.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            int hour = hourOfDay;
            int min = minute;
            //TimeEdit.setText(timeFormatter.format(currentCal.getTime()));
            TimeEdit.setText(hour + ":" + minute);
        }
    }
}
