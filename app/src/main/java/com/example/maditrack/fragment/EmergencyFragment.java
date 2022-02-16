package com.example.maditrack.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.example.maditrack.MediTracklFolder.EmergencyContact;
import com.example.maditrack.adapter.EmergencyAdapter;
import com.example.maditrack.database.EmergencyContactDAO;

import com.example.maditrack.R;

public class EmergencyFragment extends Fragment {

    private RecyclerView recyclerView;
    private EmergencyAdapter adapter;
    private ArrayList<EmergencyContact> emergencyContactArrayList;
    EmergencyContact emergencyContact=new EmergencyContact();
    private EmergencyContactDAO emergencyContactDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        emergencyContactDAO=new EmergencyContactDAO(getContext());
        loadEmergencyContact(emergencyContactDAO);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View emergencyView = inflater.inflate(R.layout.fragment_emergency, container, false);

        recyclerView = (RecyclerView)emergencyView.findViewById(R.id.recyle_view_emergency);

        final EmergencyContactDAO emergencyContactDAO;
        emergencyContactDAO=new EmergencyContactDAO(getContext());

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadEmergencyContact(emergencyContactDAO);
        adapter=new EmergencyAdapter(getActivity(),emergencyContactArrayList);
        recyclerView.setAdapter(adapter);



        return emergencyView;
    }

    public ArrayList<EmergencyContact> loadEmergencyContact(EmergencyContactDAO emergencyContactDAO) {

        emergencyContactArrayList = new ArrayList<EmergencyContact>();
        Cursor cursor = emergencyContact.getEmergencyContacts(emergencyContactDAO);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String number = cursor.getString(2);
            String contactType = cursor.getString(3);
            String description = cursor.getString(4);
            String preference = cursor.getString(5);


            EmergencyContact emergencyContact = new EmergencyContact(id, name, number, contactType, description, preference);
            emergencyContactArrayList.add(emergencyContact);
        }
        return emergencyContactArrayList;

    }

    @Override public void onResume() {
        super.onResume();
        adapter.loadEmergencyContact(emergencyContactDAO);
        //tvEmpty.setVisibility(memberListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
    }


    }
