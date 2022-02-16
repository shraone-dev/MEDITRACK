package com.example.maditrack.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.example.maditrack.MediTracklFolder.Appointment;
import com.example.maditrack.activity.SaveAppointmentActivity;

import com.example.maditrack.R;

public class AppointmentRecyclerViewAdapter extends RecyclerView.Adapter<AppointmentRecyclerViewAdapter.ViewHolder> {

    private List<Appointment> appointments;
    private Activity activity;

    public AppointmentRecyclerViewAdapter(Activity activity, List<Appointment> appointments) {
        this.appointments = appointments;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AppointmentRecyclerViewAdapter.ViewHolder viewHolder, int position)
    {
        viewHolder.location.setText(appointments.get(position).getLocation());
        viewHolder.description.setText(appointments.get(position).getDescription());
        viewHolder.appointmentDate.setText(appointments.get(position).getDate());
        viewHolder.appointmentTime.setText(appointments.get(position).getTime());

        viewHolder.container.setOnClickListener(onClickListener(position));
    }

    @Override
    public int getItemCount() {
        return (null != appointments ? appointments.size() : 0);
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,SaveAppointmentActivity.class);

                intent.putExtra("Id",appointments.get(position).getId());
                intent.putExtra("location",appointments.get(position).getLocation());
                intent.putExtra("date",appointments.get(position).getDate());
                intent.putExtra("time",appointments.get(position).getTime());
                intent.putExtra("description",appointments.get(position).getDescription());

                activity.startActivity(intent);
            }
        };
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView appointmentDate;
        private TextView appointmentTime;
        private TextView location;
        private TextView description;
        private View container;

        public ViewHolder(View view) {
            super(view);

            container = view.findViewById(R.id.card_view);
            location = (TextView) view.findViewById(R.id.txtAptLocation);
            description = (TextView) view.findViewById(R.id.txtAptDescription);
            appointmentDate = (TextView) view.findViewById(R.id.txtAptDate);
            appointmentTime = (TextView) view.findViewById(R.id.txtAptTime);
        }
    }
}
