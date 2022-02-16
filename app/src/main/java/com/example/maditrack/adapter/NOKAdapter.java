package com.example.maditrack.adapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.maditrack.MediTracklFolder.EmergencyContact;
import com.example.maditrack.activity.AddNOKActivity;
import com.example.maditrack.database.EmergencyContactDAO;

import com.example.maditrack.R;

public class NOKAdapter extends RecyclerView.Adapter<NOKAdapter.ViewHolder> {

    private ArrayList<EmergencyContact> emergencyContactList;
    private Activity activity;

    public NOKAdapter(Activity activity, ArrayList<EmergencyContact> emergencyContact) {
        this.emergencyContactList = emergencyContact;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.card_view_nok, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NOKAdapter.ViewHolder viewHolder, int position) {

        viewHolder.name.setText(emergencyContactList.get(position).getName());
        viewHolder.number.setText(emergencyContactList.get(position).getNumber());
        viewHolder.description.setText(emergencyContactList.get(position).getDesc());

        viewHolder.container.setOnClickListener(onClickListener(position));
    }

    @Override
    public int getItemCount() {
        return (null != emergencyContactList ? emergencyContactList.size() : 0);
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AddNOKActivity.class);

                intent.putExtra("Id", emergencyContactList.get(position).getId());
                intent.putExtra("name", emergencyContactList.get(position).getName());
                intent.putExtra("number", emergencyContactList.get(position).getNumber());
                intent.putExtra("description", emergencyContactList.get(position).getDesc());
                intent.putExtra("preference", emergencyContactList.get(position).getPreference());

                activity.startActivity(intent);
            }
        };
    }

    public void loadNOK(EmergencyContactDAO emergencyContactDAO) {

        emergencyContactList = new ArrayList<EmergencyContact>();
        EmergencyContact emergencyContact = new EmergencyContact();
        Cursor cursor = emergencyContact.getNOKContacts(emergencyContactDAO);

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

        private TextView name;
        private TextView number;
        private TextView description;
        private View container;

        public ViewHolder(View view) {
            super(view);

            view.setLongClickable(true);

            container = view.findViewById(R.id.card_view_nok);

            name = (TextView) view.findViewById(R.id.tv_name);
            number = (TextView) view.findViewById(R.id.tv_number);
            description = (TextView) view.findViewById(R.id.tv_description);
        }
    }
}
