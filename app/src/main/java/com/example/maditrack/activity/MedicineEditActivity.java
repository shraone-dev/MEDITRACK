package com.example.maditrack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.maditrack.MediTracklFolder.Medicine;
import com.example.maditrack.database.Constant;
import com.example.maditrack.database.MedicineDAO;

import com.example.maditrack.R;

public class MedicineEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    static EditText updatemedicinedateissued;
    EditText medicinename;
    EditText medicinedescription;
    EditText medicinequantity;
    EditText medicinedosage;
    EditText medicineconsumequantity;
    EditText medicienthreshold;
    EditText mediceineexpire;
    SwitchCompat Medicineremind;
    Button medicineupdate, medicinedelete;
    Spinner medicinecatid;
    private String spinnerValueSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_edit);

        Intent intent = getIntent();

        final int id = intent.getExtras().getInt("Id");
        final String name = intent.getExtras().getString("MedicineName");
        final String description = intent.getExtras().getString("MedicineDescription");
        final String catid = intent.getExtras().getString("MedicineCatId");
        final String remind = intent.getExtras().getString("MedicineRemind");
        final String quantity = intent.getExtras().getString("MedicineQuantity");
        final String dosage = intent.getExtras().getString("MedicineDosage");
        final String dateissued = intent.getExtras().getString("MedicineDateissued");
        final String consumequantity = intent.getExtras().getString("MedicineConsumequantity");
        final String threshold = intent.getExtras().getString("MedicineThreshold");
        final String expire = intent.getExtras().getString("MedicineExpire");


        medicinename = (EditText) findViewById(R.id.EditMedicineName);
        medicinedescription = (EditText) findViewById(R.id.EditMedicineDescription);
        medicinecatid = (Spinner) findViewById(R.id.EditMedicineCatId);
        medicinequantity = (EditText) findViewById(R.id.EditMedicineQuantity);
        medicinedosage = (EditText) findViewById(R.id.EditMedicineDosage);
        medicineconsumequantity = (EditText) findViewById(R.id.EditMedicineConsumeQuality);
        medicienthreshold = (EditText) findViewById(R.id.EditMedicineThreshold);
        mediceineexpire = (EditText) findViewById(R.id.EditMedicineExpire);
        Medicineremind = (SwitchCompat) findViewById(R.id.EditMedicineRemind);
        updatemedicinedateissued = (EditText) findViewById(R.id.EditMedicineDateIssued);

        medicinecatid.setOnItemSelectedListener(this);
        loadSpinnerData(medicinecatid);

        medicinename.setText(name);
        medicinedescription.setText(description);
        medicinecatid.setSelection(Integer.parseInt(catid));
        medicinequantity.setText(quantity);
        medicinedosage.setText(dosage);
        medicineconsumequantity.setText(consumequantity);
        medicienthreshold.setText(threshold);
        mediceineexpire.setText(expire);
        Medicineremind.setChecked(remind.equalsIgnoreCase("yes"));
        updatemedicinedateissued.setText(dateissued);

        medicineupdate = (Button) findViewById(R.id.EditMedicineUpdate);
        medicineupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    update(id, medicinename.getText().toString(), medicinedescription.getText().toString(), spinnerValueSelected,/* medicinereminderid.getText().toString(),*/ Medicineremind.getText().toString(), medicinequantity.getText().toString(), medicinedosage.getText().toString(), updatemedicinedateissued.getText().toString(), medicineconsumequantity.getText().toString(), medicienthreshold.getText().toString(), mediceineexpire.getText().toString());
                    finish();
                }
            }
        });

        medicinedelete = (Button) findViewById(R.id.EditMedicineDelete);
        medicinedelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(id);
                finish();
            }
        });
    }

    private void update(int id, String newmedicinename, String newmedicinedescription, String newmedicinecatid, /*String newmedicinereminderid,*/ String newmedicineremind, String newmedicinequantity, String newmedicinedosage, String newmedicinedataissued, String newmedicineconsumequantity, String newmedicinethreshold, String newmedicineexpirefactor) {
        MedicineDAO medicineDatabase = new MedicineDAO(this);
        medicineDatabase.openDb();
        long result = medicineDatabase.medicineUpdate(id, newmedicinename, newmedicinedescription, newmedicinecatid, "2", newmedicineremind, newmedicinequantity, newmedicinedosage, newmedicinedataissued, newmedicineconsumequantity, newmedicinethreshold, newmedicineexpirefactor);

        if (result >= 0)
        {
            medicinename.setText(newmedicinename);
            medicinedescription.setText(newmedicinedescription);
            Medicineremind.setText(newmedicineremind);
            medicinequantity.setText(newmedicinequantity);
            medicinedosage.setText(newmedicinedosage);
            updatemedicinedateissued.setText(newmedicinedataissued);
            mediceineexpire.setText(newmedicineexpirefactor);
            medicineconsumequantity.setText(newmedicineconsumequantity);
            medicienthreshold.setText(newmedicinethreshold);

            Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Not Updated", Toast.LENGTH_SHORT).show();
        }
        medicineDatabase.close();
    }

    private void delete(int id) {
        MedicineDAO medicineDatabase = new MedicineDAO(this);
        medicineDatabase.openDb();

        long result = medicineDatabase.medicineDelete(id);
        Log.v("ResultValues", Long.toString(result));
        if (result > 0) {
            this.finish();
        } else
        {
            Toast.makeText(getApplicationContext(), "Not Updated", Toast.LENGTH_SHORT).show();
        }
        medicineDatabase.close();
    }

    private void loadSpinnerData(Spinner medicinecatid) {
        MedicineDAO medicineDatabase = new MedicineDAO(getApplicationContext());
        ArrayList<Medicine> itemObjectList = medicineDatabase.getAllSpinnerdata();
        Log.v("result", Integer.toString(itemObjectList.size()));
        ArrayAdapter<Medicine> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemObjectList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        medicinecatid.setAdapter(dataAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerValueSelected = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "You selected: " + spinnerValueSelected, Toast.LENGTH_LONG).show();
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

        if (TextUtils.isEmpty(updatemedicinedateissued.getText().toString().trim())) {
            updatemedicinedateissued.requestFocus();
            updatemedicinedateissued.setError(Constant.ErrorMsg_PleaseEnterTime);
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
}
