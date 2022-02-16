package com.example.maditrack.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import com.example.maditrack.MediTracklFolder.Medicine;
import com.example.maditrack.Model.MedicineConsumption;
import com.example.maditrack.adapter.ConsumptionAdapter;
import com.example.maditrack.database.ConsumptionDAO;

import com.example.maditrack.R;

public class ConsumptionActivity extends AppCompatActivity {

    private List<Medicine> medicineList=new ArrayList<>();
    private List<MedicineConsumption> consumptionList=new ArrayList<>();
    private RecyclerView recyclerView;
    private ConsumptionAdapter consumptionAdapter;
    ConsumptionDAO consumptionDAO;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption);
        loadConsumption(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyle_view_consumption);

        recyclerView.setHasFixedSize(true);

        consumptionAdapter = new ConsumptionAdapter(this, medicineList, consumptionList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(consumptionAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.consumption_menu, menu);
        return true;
    }

    public void loadConsumption(Context context) {
        consumptionDAO=new ConsumptionDAO(context);
        consumptionList=consumptionDAO.getConsumption();
        if(consumptionList.size()>0){
            String medicineName=consumptionList.get(position).getMedicineName();

            //recyclerView.setAdapter(consumptionAdapter);
           // setText(medicineName);
        }


    }

}
