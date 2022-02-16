package com.example.maditrack.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.example.maditrack.MediTracklFolder.EmergencyContact;
import com.example.maditrack.activity.AddDoctorActivity;
import com.example.maditrack.adapter.DoctorAdapter;
import com.example.maditrack.database.EmergencyContactDAO;

import com.example.maditrack.R;

public class DoctorFragment extends Fragment {

    EmergencyContact emergencyContact = new EmergencyContact();
    private RecyclerView recyclerView;
    private DoctorAdapter adapter;
    private ArrayList<EmergencyContact> emergencyContactArrayList;
    private EmergencyContactDAO emergencyContactDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        emergencyContactDAO=new EmergencyContactDAO(getContext());
        loadDoctor(emergencyContactDAO);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View doctorView = inflater.inflate(R.layout.fragment_doctor, container, false);

        recyclerView = (RecyclerView)doctorView.findViewById(R.id.recyle_view_doctor);

        FloatingActionButton floatingActionButton =
                (FloatingActionButton) doctorView.findViewById(R.id.fab_add_doctor);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadDoctor(emergencyContactDAO);
        adapter=new DoctorAdapter(getActivity(),emergencyContactArrayList);
        recyclerView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AddDoctorActivity.class);

                intent.putExtra("Id",0);
                intent.putExtra("name","");
                intent.putExtra("number","");
                intent.putExtra("desc","");
                intent.putExtra("pref","");
                intent.putExtra("action","add");

                startActivity(intent);
            }
        });
        return doctorView;
    }

    public ArrayList<EmergencyContact> loadDoctor(EmergencyContactDAO emergencyContactDAO) {
        emergencyContactArrayList = new ArrayList<EmergencyContact>();
        Cursor cursor = emergencyContact.getDoctorContacts(emergencyContactDAO);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String number= cursor.getString(2);
            String contactType= cursor.getString(3);
            String description = cursor.getString(4);
            String preference = cursor.getString(5);


            EmergencyContact emergencyContact  = new EmergencyContact(id,name, number,contactType,description,preference);
            emergencyContactArrayList.add(emergencyContact);
        }
        return emergencyContactArrayList;
    }

    @Override public void onResume() {
        super.onResume();
        adapter.loadDoctor(emergencyContactDAO);
    }

}

