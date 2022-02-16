package com.example.maditrack.activity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.example.maditrack.MediTracklFolder.Person;
import com.example.maditrack.database.PersonDAO;

import com.example.maditrack.R;

public class PersonActivity extends AppCompatActivity {

    Person person = new Person();
    Calendar currentCal = Calendar.getInstance();
    Calendar selectedDate = Calendar.getInstance();
    private EditText personname, persondate, personidno, personaddress, personpostalcode, personheight;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private String[] mBloodTypeArray;
    private Spinner mBloodtypeSpinner;
    private String spinnerString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_main);

        personname = (EditText) findViewById(R.id.et_name);
        persondate = (EditText) findViewById(R.id.et_dob);
        personidno = (EditText) findViewById(R.id.et_idno);
        personaddress = (EditText) findViewById(R.id.et_address);
        personpostalcode = (EditText) findViewById(R.id.et_postal);
        personheight = (EditText) findViewById(R.id.et_height);

        mBloodtypeSpinner = (Spinner) findViewById(R.id.bloodtype_spinner);
        mBloodTypeArray = getResources().getStringArray(R.array.blood_types);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mBloodTypeArray);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBloodtypeSpinner.setAdapter(dataAdapter);

        mBloodtypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final PersonDAO personDAO = new PersonDAO(this);
        loadPerson(personDAO);

        persondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener =
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                selectedDate = calendar;
                                persondate.setText(dateFormatter.format(calendar.getTime()));
                            }
                        };
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(PersonActivity.this, onDateSetListener,
                                currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH),
                                currentCal.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_person, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save_person) {
            if (isValid()) {
                save(personname.getText().toString().trim(), personidno.getText().toString().trim(), persondate.getText().toString().trim(), personaddress.getText().toString().trim(), spinnerString, personpostalcode.getText().toString().trim(), personheight.getText().toString().trim());
                finish();
                Toast.makeText(getApplicationContext(), "Successfully added your details", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isValid()
    {
        boolean isValid = true;
        if (TextUtils.isEmpty(personname.getText().toString().trim())) {
            personname.requestFocus();
            personname.setError("Please enter name.");
            isValid = false;
        }
        if (TextUtils.isEmpty(personidno.getText().toString().trim())) {
            personidno.requestFocus();
            personname.setError("Please enter Id no.");
            isValid = false;
        }
        return isValid;
    }

    private void save(String name, String idno, String dateofbirth, String address, String spinnerString, String postalcode, String height)
    {
        PersonDAO personDAO = new PersonDAO(this);
        long result = personDAO.addPerson(name, idno, dateofbirth, address, spinnerString, postalcode, height);

        if (result > 0) {
            loadPerson(personDAO);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Unable to insert", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadPerson(PersonDAO personDAO) {
        Cursor cursor = person.getperson(personDAO);

        while (cursor.moveToNext())
        {
            String name = cursor.getString(1);
            String dateofbirth = cursor.getString(2);
            String idNo = cursor.getString(3);
            String address = cursor.getString(4);
            String postalcode = cursor.getString(5);
            String height = cursor.getString(6);
            String bloodtype = cursor.getString(7);

            personname.setText(name);
            persondate.setText(dateofbirth);
            personidno.setText(idNo);
            personaddress.setText(address);
            personpostalcode.setText(postalcode);
            personheight.setText(height);
            mBloodtypeSpinner.setTag(bloodtype);
        }
    }
}