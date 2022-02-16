package com.example.maditrack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import com.example.maditrack.MainActivity;
import com.example.maditrack.MediTracklFolder.Consumption;
import com.example.maditrack.database.Constant;
import com.example.maditrack.database.ConsumptionDAO;

import static android.R.attr.id;

import com.example.maditrack.R;

public class ReminderConsumptionActivity extends AppCompatActivity {

    private final ConsumptionDAO consumptionDAO = new ConsumptionDAO(this);
    EditText editMedicineName, editQuantity;
    Button btn_ok, btn_cancel;
    Calendar selectedDate = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();
    private int medicineID;
    private String returnquantity;
    private int quantity;
    //// Begin here
    private String medicineName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_consumption);

        Intent intent = getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        medicineName = intent.getExtras().getString(Constant.MedicineName);
        returnquantity = intent.getExtras().getString(Constant.MedicineConsumeQuantity);
        quantity = Integer.parseInt(returnquantity);
        editMedicineName = (EditText) findViewById(R.id.medicine_name);
        editMedicineName.setText(medicineName);
        editQuantity = (EditText) findViewById(R.id.quantity);
        editQuantity.setText(String.valueOf(quantity));

        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_ok = (Button) findViewById(R.id.btn_ok);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addConsumption(id, medicineID, quantity, calendar.getTime());
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addConsumption(id, medicineID, 0, calendar.getTime());
            }
        });
    }

    private void addConsumption(int id, int medicineID, int quantity, Date dateTime) {
        Consumption consumption = new Consumption(id, medicineID, quantity, dateTime);

        consumption.addConsumption(consumption, consumptionDAO);

        Toast.makeText(getApplicationContext(), "Consumption Added ", Toast.LENGTH_SHORT).show();

        //TODO   need to replace MainActivity with the Consumption Screen

        Intent i = new Intent(getApplicationContext(), ConsumptionActivity.class);
        startActivity(i);
    }
}
