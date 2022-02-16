package com.example.maditrack.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import com.example.maditrack.MainActivity;
import com.example.maditrack.MediTracklFolder.Appointment;
import com.example.maditrack.adapter.AppointmentRecyclerViewAdapter;
import com.example.maditrack.database.AppointmentDAO;

import com.example.maditrack.R;

public class AppointmentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppointmentRecyclerViewAdapter adapter;
    private ArrayList<Appointment> appointmentArrayList;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyle_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadAppointment(this);

        adapter = new AppointmentRecyclerViewAdapter(this, appointmentArrayList);
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(onAddingListener(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

    public void loadAppointment(Context context)
    {
        final AppointmentDAO appointmentDAO;
        appointmentDAO = new AppointmentDAO(context);
        appointmentArrayList = appointmentDAO.getAllAppointments();

        if (!(appointmentArrayList.size() < 1))
            recyclerView.setAdapter(adapter);
    }

    private View.OnClickListener onAddingListener(final Activity activity) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SaveAppointmentActivity.class);

                intent.putExtra("Id",0);
                intent.putExtra("location","");
                intent.putExtra("date","");
                intent.putExtra("time","");
                intent.putExtra("description","");
                intent.putExtra("action","add");

                activity.startActivity(intent);
            }
        };
    }
}
