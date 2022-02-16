package com.example.maditrack.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.maditrack.MediTracklFolder.EmergencyContact;
import com.example.maditrack.database.EmergencyContactDAO;

import com.example.maditrack.R;

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.ViewHolder> {

    Context context;
    private List<EmergencyContact> emergencyContact;
    private ArrayList<EmergencyContact> emergencyContactList;
    private Activity activity;

    public EmergencyAdapter(Activity activity, List<EmergencyContact> emergencyContact) {
        this.emergencyContact = emergencyContact;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.card_view_emergency, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final EmergencyAdapter.ViewHolder viewHolder, int position) {

        viewHolder.name.setText(emergencyContact.get(position).getName());
        viewHolder.number.setText(String.valueOf(emergencyContact.get(position).getNumber()));
        viewHolder.description.setText(emergencyContact.get(position).getDesc());
        viewHolder.btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + viewHolder.number.getText().toString()));
                    if (ActivityCompat.checkSelfPermission(viewHolder.context,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    viewHolder.context.startActivity(callIntent);
                } catch (ActivityNotFoundException activityException) {
                }
            }
        });
        viewHolder.btn_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + viewHolder.number.getText().toString()));
                intent.putExtra("sms_body", "Please reach out to xxx");
                viewHolder.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != emergencyContact ? emergencyContact.size() : 0);
    }

    public void loadEmergencyContact(EmergencyContactDAO emergencyContactDAO) {

        emergencyContactList = new ArrayList<EmergencyContact>();
        EmergencyContact emergencyContact = new EmergencyContact();
        Cursor cursor = emergencyContact.getEmergencyContacts(emergencyContactDAO);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String number = cursor.getString(2);
            String contactType = cursor.getString(3);
            String description = cursor.getString(4);
            String preference = cursor.getString(5);


            emergencyContact = new EmergencyContact(id, name, number, contactType, description, preference);
            emergencyContactList.add(emergencyContact);
        }
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        Button btn_call, btn_msg;
        private TextView name;
        private TextView number;
        private TextView description;
        private View container;
        private Context context;

        public ViewHolder(View view) {
            super(view);

            container = view.findViewById(R.id.card_view_emergency);
            context = view.getContext();
            name = (TextView) view.findViewById(R.id.tv_name);
            number = (TextView) view.findViewById(R.id.tv_number);
            description = (TextView) view.findViewById(R.id.tv_description);

            btn_call = (Button) view.findViewById(R.id.btn_call);
            btn_msg = (Button) view.findViewById(R.id.btn_message);
        }
    }

}

