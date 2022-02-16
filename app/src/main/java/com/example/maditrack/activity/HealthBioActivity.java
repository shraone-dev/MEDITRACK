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
import com.example.maditrack.MediTracklFolder.HealthBio;
import com.example.maditrack.adapter.HealthBioRecyclerViewAdapter;
import com.example.maditrack.database.HealthBioDAO;

import com.example.maditrack.R;

public class HealthBioActivity extends AppCompatActivity {

    HealthBio healthbio = new HealthBio();
    private RecyclerView recyclerView;
    private HealthBioRecyclerViewAdapter adapter;
    private ArrayList<HealthBio> healthArrayList;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_bio_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyle_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadHealthBio(this);

        adapter = new HealthBioRecyclerViewAdapter(this, healthArrayList);
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(onAddingListener(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    public void loadHealthBio(Context context) {
        final HealthBioDAO healthBioDAO;
        healthBioDAO = new HealthBioDAO(context);
        healthArrayList = healthbio.getHealthbio(healthBioDAO);

        if (!(healthArrayList.size() < 1))
            recyclerView.setAdapter(adapter);
    }

    private View.OnClickListener onAddingListener(final Activity activity) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AddHealthBioActivity.class);

                intent.putExtra("Id", 0);
                intent.putExtra("condition", "");
                intent.putExtra("date", "");
                intent.putExtra("conditiontype", "");
                intent.putExtra("action", "add");

                activity.startActivity(intent);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        loadHealthBio(getApplicationContext());
    }
}