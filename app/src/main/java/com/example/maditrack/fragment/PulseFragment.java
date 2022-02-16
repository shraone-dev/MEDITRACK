package com.example.maditrack.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.example.maditrack.MediTracklFolder.Measurement;
import com.example.maditrack.MediTracklFolder.Pulse;
import com.example.maditrack.activity.AddPulseActivity;
import com.example.maditrack.adapter.PulseAdapter;

import com.example.maditrack.R;

public class PulseFragment extends Fragment {

    FloatingActionButton addPulse;
    RecyclerView pulseRecycleView;
    PulseAdapter pulseAdapter;
    ArrayList<Pulse> pulseitem = new ArrayList();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pulse, container, false);
        addPulse = (FloatingActionButton) view.findViewById(R.id.addPulse);
        addPulse.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddPulseActivity.class);
                startActivity(intent);
            }
        });

        pulseRecycleView = (RecyclerView) view.findViewById(R.id.pulserecycleviewid);
        pulseRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        pulseRecycleView.setItemAnimator(new DefaultItemAnimator());
        loadPulseOnResume();
        return view;
    }

    private void loadPulseOnResume() {
        Measurement showpulse = new Pulse();
        pulseitem = showpulse.showDisplayMeasurements(getActivity().getApplicationContext());

        if (!(pulseitem.size() < 1)) {

            pulseAdapter = new PulseAdapter(getActivity(), pulseitem);
            pulseRecycleView.setAdapter(pulseAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPulseOnResume();
    }
}
