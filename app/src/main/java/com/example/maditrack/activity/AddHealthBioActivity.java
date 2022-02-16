package com.example.maditrack.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.example.maditrack.MediTracklFolder.HealthBio;
import com.example.maditrack.R;
import com.example.maditrack.database.Constant;
import com.example.maditrack.database.HealthBioDAO;

public class AddHealthBioActivity extends AppCompatActivity {

    private final HealthBioDAO healthBioDAO = new HealthBioDAO(this);
    EditText editCondition, editStartDate, editConditionType;
    Button btn_save;
    HealthBio healthBio;
    Calendar currentCal = Calendar.getInstance();
    Calendar selectedDate = Calendar.getInstance();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private String action = "";
    private int healthId = 0;
    private String text;
    private String[] mConditionTypeArray;
    private Spinner mConditiontypeSpinner;
    private String spinnerString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_healthbio);

        Intent intent = getIntent();

        healthId = intent.getExtras().getInt("Id");
        final String condition = intent.getExtras().getString("condition");
        final String startdate = intent.getExtras().getString("startdate");

        action = intent.getExtras().getString("action");

        editCondition = (EditText) findViewById(R.id.et_condition);
        editStartDate = (EditText) findViewById(R.id.et_start_date);


        editCondition.setText(condition);
        editStartDate.setText(startdate);


        getDatePicker(startdate);


        mConditiontypeSpinner = (Spinner) findViewById(R.id.condition_type_spinner);
        mConditionTypeArray = getResources().getStringArray(R.array.condition_type);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mConditionTypeArray);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mConditiontypeSpinner.setAdapter(dataAdapter);
        mConditiontypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void getDatePicker(String date) {
        editStartDate.setText(date);
        editStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener =
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                selectedDate = calendar;
                                editStartDate.setText(dateFormatter.format(calendar.getTime()));
                            }
                        };
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(AddHealthBioActivity.this, onDateSetListener,
                                currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH),
                                currentCal.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    private void delete(int id, HealthBioDAO appointmentDAO) {
        healthBio = new HealthBio();
        healthBio.DeleteAppointmentById(id, appointmentDAO);

        Toast.makeText(getApplicationContext(), Constant.NotificationMsg_HealthBioDeleted, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(), HealthBioActivity.class);
        startActivity(i);
    }

    private void update(int id, String condition, String startdate, String spinnerString, HealthBioDAO healthBioDAO, String action) {

        HealthBio healthBio = new HealthBio(id, condition, startdate, spinnerString);

        if (action != null && !action.trim().isEmpty() && action.equals("add")) {
            healthBio.addHealthBio(healthBio, healthBioDAO);

            Toast.makeText(getApplicationContext(), Constant.NotificationMsg_HealthBioAdded, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), HealthBioActivity.class);
            startActivity(i);
        }
        else {
            boolean result = healthBio.UpdateHealthBioById(healthBio, healthBioDAO);

            if (result) {
                editCondition.setText(spinnerString);
                editStartDate.setText(startdate);

                Toast.makeText(getApplicationContext(), Constant.NotificationMsg_HealthBioUpdated, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), HealthBioActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), Constant.ErrorMsg_RecordNotUpdated, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.healthbio_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save_healthBio) {
            if (isValid()) {
                update(healthId, editCondition.getText().toString(), editStartDate.getText().toString(), spinnerString, healthBioDAO, action);
                finish();
            }
        }
        if (id == R.id.action_delete) {
            delete(healthId, healthBioDAO);
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isValid() {
        boolean isValid = true;
        if (TextUtils.isEmpty(editCondition.getText().toString().trim())) {
            editCondition.requestFocus();
            editCondition.setError(Constant.ErrorMsg_pleaseenter_condtion);
            isValid = false;
        }

        if (TextUtils.isEmpty(editStartDate.getText().toString().trim())) {
            editStartDate.requestFocus();
            editStartDate.setError(Constant.ErrorMsg_PleaseEnterDate);
            isValid = false;
        }

        return isValid;
    }
}