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
import com.example.maditrack.MediTracklFolder.Weight;
import com.example.maditrack.activity.AddWeightActivity;
import com.example.maditrack.adapter.WeightAdapter;

import com.example.maditrack.R;

public class WeightFragment extends Fragment {

    FloatingActionButton addWeight;
    RecyclerView weightRecycleView;
    WeightAdapter weightAdapter;
    ArrayList<Weight> weightitem = new ArrayList();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight, container, false);

        addWeight = (FloatingActionButton) view.findViewById(R.id.addWeight);
        addWeight.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddWeightActivity.class);
                startActivity(intent);
            }
        });

        weightRecycleView = (RecyclerView) view.findViewById(R.id.weightrecycleviewid);
        weightRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        weightRecycleView.setItemAnimator(new DefaultItemAnimator());
        setWeightOnResume();

        return view;
    }

    private void setWeightOnResume() {
        Measurement showweight = new Weight();
        weightitem = showweight.showDisplayMeasurements(getActivity().getApplicationContext());

        if (!(weightitem.size() < 1)) {

            weightAdapter = new WeightAdapter(getActivity(), weightitem);
            weightRecycleView.setAdapter(weightAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setWeightOnResume();
    }

}
