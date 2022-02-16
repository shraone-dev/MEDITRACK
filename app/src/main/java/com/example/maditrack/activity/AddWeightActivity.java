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
import com.example.maditrack.MediTracklFolder.Weight;

import com.example.maditrack.R;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AddWeightActivity extends AppCompatActivity {
    static Calendar selectedDate = Calendar.getInstance();
    private static EditText DateEdit;
    private static EditText TimeEdit;
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    EditText weight;
    TextView weightUnit;
    Button weightSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_add);
        DateEdit = (EditText) findViewById(R.id.Date);
        weight = (EditText) findViewById(R.id.weight);
        weightUnit = (TextView) findViewById(R.id.weight_unit);
        TimeEdit = (EditText) findViewById(R.id.time);


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

        weightSave = (Button) findViewById(R.id.AddToWeight);
        weightSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    AddWeightValue(weight.getText().toString(), DateEdit.getText().toString(), TimeEdit.getText().toString());
                   /* Intent i = new Intent(getApplicationContext(), WeightFragment.class);
                    startActivity(i);*/
                    finish();
                }
            }
        });
    }

    private void AddWeightValue(String weight, String DateEdit, String TimeEdit) {
        Integer weightValue = Integer.valueOf(weight);
        StringBuffer dateTime = new StringBuffer(DateEdit);
        dateTime.append("_");
        dateTime.append(TimeEdit);
        String measuredDate = dateTime.toString();

        Measurement weightObj = new Weight(0, measuredDate, weightValue);
        weightObj.addMeasurement(weightObj, getApplicationContext());

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
        if (TextUtils.isEmpty(weight.getText().toString().trim())) {
            weight.requestFocus();
            weight.setError("please add weight value");
            isValid = false;
        }
        if ((Integer.valueOf(weight.getText().toString().trim()) <= 1) || (Integer.valueOf(weight.getText().toString().trim()) >= 150)) {
            weight.requestFocus();
            weight.setError("Weight value should be in range of 60-200");
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
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

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
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

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
