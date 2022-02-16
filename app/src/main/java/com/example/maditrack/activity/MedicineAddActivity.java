package com.example.maditrack.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.maditrack.MediTracklFolder.Medicine;
import com.example.maditrack.MediTracklFolder.Reminder;
import com.example.maditrack.Validate.InputFilterMinMax;
import com.example.maditrack.adapter.MedicineAdapter;
import com.example.maditrack.database.CategoryDAO;
import com.example.maditrack.database.Constant;
import com.example.maditrack.database.MedicineDAO;
import com.example.maditrack.database.ReminderDAO;
import com.example.maditrack.service.ReminderService;

import com.example.maditrack.R;

public class MedicineAddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String MEDICINE = "Medicine";
    static EditText medicinedateissued;
    EditText medicinename, medicinedescription, medicinereminderid, medicinequantity, medicinedosage, medicineconsumequantity, medicienthreshold, mediceineexpire;
    SwitchCompat Medicineremind;
    Button medicinesave;
    Spinner medicinecatid;
    TextView remindername;
    View mview;
    RecyclerView medicinerecyclerView;
    MedicineAdapter medicineAdapter;
    ArrayList<Medicine> medicineitem = new ArrayList<Medicine>();
    private String spinnerValueSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_add);

        mview = getLayoutInflater().inflate(R.layout.activity_medicine, null);

        medicineAdapter = new MedicineAdapter(this, medicineitem);
        remindername = (TextView) findViewById(R.id.remindername);
        medicinename = (EditText) findViewById(R.id.AddMedicineName);
        medicinedescription = (EditText) findViewById(R.id.AddMedicineDescription);
        medicinecatid = (Spinner) findViewById(R.id.AddMedicineCatId);
        medicinequantity = (EditText) findViewById(R.id.AddMedicineQuantity);
        medicinedosage = (EditText) findViewById(R.id.AddMedicineDosage);
        medicineconsumequantity = (EditText) findViewById(R.id.AddMedicineConsumeQuality);
        medicienthreshold = (EditText) findViewById(R.id.AddMedicineThreshold);

        mediceineexpire = (EditText) findViewById(R.id.AddMedicineExpire);
        mediceineexpire.setFilters(new InputFilter[]{new InputFilterMinMax("1", "24")});

        Medicineremind = (SwitchCompat) findViewById(R.id.AddMedicineRemind);
        Medicineremind.setOnCheckedChangeListener(onCheckedChanged());

        medicinecatid.setOnItemSelectedListener(this);
        loadSpinnerData(medicinecatid);

        medicinedateissued = (EditText) findViewById(R.id.AddMedicineDateIssued);
        medicinedateissued.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTruitonDatePickerDialog(v);
            }
        });

        medicinesave = (Button) findViewById(R.id.MedicineSave);
        medicinesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    save(medicinename.getText().toString(), medicinedescription.getText().toString(), spinnerValueSelected, Medicineremind.isChecked(), medicinequantity.getText().toString(), medicinedosage.getText().toString(), medicinedateissued.getText().toString(), medicineconsumequantity.getText().toString(), medicienthreshold.getText().toString(), mediceineexpire.getText().toString());
                    finish();
                }
            }
        });
    }

    private void save(String name, String description, String catName, boolean remind, String quantity, String dosage, String dataissued, String consumequantity, String threshold, String expirefactor) {

        MedicineDAO medicineDatabase = new MedicineDAO(this);
        ReminderDAO reminderDAO = new ReminderDAO(this);
        CategoryDAO categoryDAO = new CategoryDAO(this);
        medicineDatabase.openDb();

        String reminderId = String.valueOf(reminderDAO.getLastReminderId());
        String categoryId = String.valueOf(categoryDAO.getCategoryIdByName(catName));
        String remindSwitch = "";
        remindSwitch = remind ? "YES" : "NO";

        long result = medicineDatabase.medicineAdd(name, description, categoryId, reminderId, remindSwitch, quantity, dosage, dataissued, consumequantity, threshold, expirefactor);

        if (result > 0) {
            if(remind) {
                setReminder(name, consumequantity, reminderDAO);
            }
            setDisplay();
        } else {
            Toast.makeText(getApplicationContext(), "Unable to insert", Toast.LENGTH_SHORT).show();
        }
        medicineDatabase.close();
    }

    private void setDisplay() {
        medicinename.setText("");
        medicinedescription.setText("");
        medicinecatid.setTag(spinnerValueSelected);
        Medicineremind.setText("");
        medicinequantity.setText("");
        medicinedosage.setText("");
        medicinedateissued.setText("");
        medicineconsumequantity.setText("");
        medicienthreshold.setText("");
        mediceineexpire.setText("");
    }

    private void setReminder(String name, String consumequantity, ReminderDAO reminderDAO) {
            Reminder reminder = reminderDAO.getLastReminder();
            String dateTimeString = reminder.getstartDateTime();

            Intent service = new Intent(this, ReminderService.class);
            service.putExtra(Constant.COLUMN_ID, reminder.getId());
            service.putExtra(Constant.REMINDERDATETIME, dateTimeString);
            service.putExtra(Constant.FREQUENCY, reminder.getFrequency());
            service.putExtra(Constant.INTERVAL, reminder.getInterval());
            service.putExtra(Constant.MedicineName, name);
            service.putExtra(Constant.MedicineConsumeQuantity, consumequantity);
            service.putExtra("Message", "Medicine Reminder");
            service.putExtra("Type",MEDICINE);
            service.setAction(ReminderService.CREATE);
            startService(service);
    }

    protected void onResume() {
        super.onResume();
    }

    private CompoundButton.OnCheckedChangeListener onCheckedChanged() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()) {
                    case R.id.AddMedicineRemind:
                        setButtonState(isChecked);
                        break;
                }
            }
        };
    }

    private void setButtonState(boolean state) {
        if (state) {
            mview.setEnabled(true);
                    Intent i = new Intent(this,AddReminderActivity.class);
            startActivity(i);
            Toast.makeText(MedicineAddActivity.this, "Button enabled!", Toast.LENGTH_SHORT).show();
        } else {
            mview.setEnabled(false);
            Toast.makeText(MedicineAddActivity.this, "Button disabled!", Toast.LENGTH_SHORT).show();
        }
    }

    public void showTruitonDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void loadSpinnerData(Spinner medicinecatid) {

        MedicineDAO medicineDatabase = new MedicineDAO(getApplicationContext());
        ArrayList<Medicine> itemObjectList = medicineDatabase.getAllSpinnerdata();
        Log.v("result", Integer.toString(itemObjectList.size()));
        ArrayAdapter<Medicine> dataAdapter = new ArrayAdapter<Medicine>(this,
                android.R.layout.simple_spinner_item, itemObjectList);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        medicinecatid.setAdapter(dataAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        spinnerValueSelected = parent.getItemAtPosition(position).toString();

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean isValid() {
        boolean isValid = true;
        if (TextUtils.isEmpty(medicinename.getText().toString().trim())) {
            medicinename.requestFocus();
            medicinename.setError(Constant.ErrorMsg_PleaseEnterLocation);
            isValid = false;
        }

        if (TextUtils.isEmpty(medicinedescription.getText().toString().trim())) {
            medicinedescription.requestFocus();
            medicinedescription.setError(Constant.ErrorMsg_PleaseEnterDescription);
            isValid = false;
        }

        if (TextUtils.isEmpty(medicinedateissued.getText().toString().trim())) {
            medicinedateissued.requestFocus();
            medicinedateissued.setError(Constant.ErrorMsg_PleaseEnterTime);
            isValid = false;
        }

        if (TextUtils.isEmpty(medicinequantity.getText().toString().trim())) {
            medicinequantity.requestFocus();
            medicinequantity.setError(Constant.ErrorMsg_PleaseEnterDate);
            isValid = false;
        }

        if (TextUtils.isEmpty(medicinedosage.getText().toString().trim())) {
            medicinedosage.requestFocus();
            medicinedosage.setError(Constant.ErrorMsg_PleaseEnterTime);
            isValid = false;
        }
        if (TextUtils.isEmpty(medicineconsumequantity.getText().toString().trim())) {
            medicineconsumequantity.requestFocus();
            medicineconsumequantity.setError(Constant.ErrorMsg_PleaseEnterTime);
            isValid = false;
        }
        if (TextUtils.isEmpty(medicienthreshold.getText().toString().trim())) {
            medicienthreshold.requestFocus();
            medicienthreshold.setError(Constant.ErrorMsg_PleaseEnterTime);
            isValid = false;
        }
        if (TextUtils.isEmpty(mediceineexpire.getText().toString().trim())) {
            mediceineexpire.requestFocus();
            mediceineexpire.setError(Constant.ErrorMsg_PleaseEnterTime);
            isValid = false;
        }
        return isValid;
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            medicinedateissued.setText(day + "/" + (month + 1) + "/" + year);
        }
    }


}
