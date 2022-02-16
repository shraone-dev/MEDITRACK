package com.example.maditrack.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import com.example.maditrack.MediTracklFolder.Medicine;
import com.example.maditrack.adapter.MedicineAdapter;
import com.example.maditrack.database.MedicineDAO;

import com.example.maditrack.R;

public class MedicineActivity extends AppCompatActivity {

    FloatingActionButton medicineadd;
    RecyclerView medicinerecyclerView;
    MedicineAdapter medicineAdapter;
    ArrayList<Medicine> medicineitem = new ArrayList<Medicine>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        medicineadd = (FloatingActionButton) findViewById(R.id.MedicineAdd);
        medicineadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicineActivity.this, MedicineAddActivity.class);
                startActivity(intent);
            }
        });
        medicinerecyclerView = (RecyclerView) findViewById(R.id.MedicineRecycleview);
        medicinerecyclerView.setLayoutManager(new LinearLayoutManager(this));
        medicinerecyclerView.setItemAnimator(new DefaultItemAnimator());

        loadMedicine();
        medicineAdapter = new MedicineAdapter(this, medicineitem);
        medicinerecyclerView.setAdapter(medicineAdapter);
    }

    public void loadMedicine() {
        MedicineDAO medicineDatabase = new MedicineDAO(this);
        medicineitem = new ArrayList<Medicine>();
        medicineDatabase.openDb();
        medicineitem.clear();

        Cursor cursor = medicineDatabase.getAllMedicine();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String medicinename = cursor.getString(1);
            String medicinedescription = cursor.getString(2);
            String medicinecatid = cursor.getString(3);
            int medicinereminderid = cursor.getInt(4);
            String medicineremind = cursor.getString(5);
            String medicinequantity = cursor.getString(6);
            String medicinedosage = cursor.getString(7);
            String medicinedateissued = cursor.getString(8);
            String medicineconsumequantity = cursor.getString(9);
            String medicienthreshold = cursor.getString(10);
            String mediceineexpire = cursor.getString(11);

            Medicine medicineItems = new Medicine(id, medicinename, medicinedescription, medicinecatid,medicinereminderid, medicineremind, medicinequantity, medicinedosage, medicinedateissued, medicineconsumequantity, medicienthreshold, mediceineexpire);

            medicineitem.add(medicineItems);
        }

        if (!(medicineitem.size() < 1)) {
            Log.v("MedicineAddActivity", "" + medicineitem.size());
            medicineAdapter = new MedicineAdapter(this, medicineitem);
            medicinerecyclerView.setAdapter(medicineAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadMedicine();
    }


}
