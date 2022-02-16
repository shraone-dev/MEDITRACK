package com.example.maditrack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.maditrack.MediTracklFolder.EmergencyContact;
import com.example.maditrack.R;
import com.example.maditrack.database.Constant;
import com.example.maditrack.database.EmergencyContactDAO;

public class AddNOKActivity extends AppCompatActivity {
    private final EmergencyContactDAO emergencyContactDAO = new EmergencyContactDAO(this);
    EmergencyContact emergencyContact;
    private EditText editName, editNumber, editDesc;
    private CheckBox editCheckbox;
    private String contactType = "NextOfKin";
    private String preferences = "No";
    private String action = "";
    private int contactID = 0;
    private ArrayList<EmergencyContact> emergencyContactArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Intent intent = getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EmergencyContactDAO emergencyContactDAO;
        emergencyContactDAO = new EmergencyContactDAO(this);
        contactID = intent.getExtras().getInt("Id");
        final String contactName = intent.getExtras().getString("name");
        final String contactNumber = intent.getExtras().getString("number");
        final String description = intent.getExtras().getString("desc");
        final String preference = intent.getExtras().getString("pref");
        action = intent.getExtras().getString("action");


        editName = (EditText) findViewById(R.id.input_name);
        editNumber = (EditText) findViewById(R.id.input_number);
        editDesc = (EditText) findViewById(R.id.input_desc);
        editCheckbox = (CheckBox) findViewById(R.id.checkbox);

        editName.setText(contactName);
        editNumber.setText(contactNumber);
    }

    private void delete(int id, EmergencyContactDAO emergencyContactDAO) {
        emergencyContact = new EmergencyContact();
        emergencyContact.deleteContact(id, emergencyContactDAO);

        Toast.makeText(getApplicationContext(), Constant.NotificationMsg_ContactDeleted, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(), ICETabLayoutActivity.class);
        startActivity(i);
    }

    private void update(int id, String name, String number, String desc, Boolean preferences, EmergencyContactDAO emergencyContactDAO, String action) {
        String emergency = "";
        if (preferences) {
            emergency = "YES";
        } else {
            emergency = "NO";
        }
        EmergencyContact emergencyContact = new EmergencyContact(id, name, number, contactType, desc, emergency);

        if (action != null && !action.trim().isEmpty() && action.equals("add")) {
            emergencyContact.addContact(emergencyContact, emergencyContactDAO);

            Toast.makeText(getApplicationContext(), Constant.NotificationMsg_ContactAdded, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            boolean result = emergencyContact.updateContact(emergencyContact, emergencyContactDAO);

            if (result) {
                editName.setText(name);
                editNumber.setText(number);
                editDesc.setText(desc);

                if (emergency.equalsIgnoreCase("YES")) {
                    editCheckbox.setChecked(true);
                }

                Toast.makeText(getApplicationContext(), Constant.NotificationMsg_ContactUpdated, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), ICETabLayoutActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), Constant.ErrorMsg_RecordNotUpdated, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appointment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            if (isValid()) {
                update(contactID, editName.getText().toString(), editNumber.getText().toString(), editDesc.getText().toString(), editCheckbox.isChecked(), emergencyContactDAO, action);
            }
        }
        if (id == R.id.action_delete) {
            delete(contactID, emergencyContactDAO);
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isValid() {
        boolean isValid = true;
        if (TextUtils.isEmpty(editName.getText().toString().trim())) {
            editName.setError("This field can't be empty!");
            isValid = false;
        }
        if (TextUtils.isEmpty(editName.getText().toString().trim())) {
            editName.setError("This field can't be empty!");
            isValid = false;
        }

        return isValid;
    }
}

