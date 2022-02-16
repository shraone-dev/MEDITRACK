package com.example.maditrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.maditrack.MediTracklFolder.Pressure;
import com.example.maditrack.activity.PressureDisplayEditUpdateActivity;

import com.example.maditrack.R;

public class PressureAdapter extends RecyclerView.Adapter<PressureAdapter.pressureHolder> {

    private ArrayList<Pressure> pressurelist = new ArrayList<>();
    private Context context;

    public PressureAdapter(Context context, ArrayList<Pressure> pressurelist) {
        this.pressurelist = pressurelist;
        this.context = context;
    }

    @Override
    public pressureHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutview = LayoutInflater.from(parent.getContext()).inflate(R.layout.pressureviewlayout, parent, false);
        pressureHolder recyclerViewHolders = new pressureHolder(layoutview);
        return recyclerViewHolders;
    }

    @Override
    public void onBindViewHolder(pressureHolder holder, int position) {
        holder.systolic.setText(String.valueOf(pressurelist.get(position).getSystolic()));
        holder.diastolic.setText(String.valueOf(pressurelist.get(position).getDiastolic()));
        holder.measuredOn.setText(pressurelist.get(position).getMeasuredOn());
        holder.container.setOnClickListener(onClickListener(position));
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PressureDisplayEditUpdateActivity.class);
                intent.putExtra("measurementId", pressurelist.get(position).getMeasurementId());
                intent.putExtra("systolic", String.valueOf(pressurelist.get(position).getSystolic()));
                intent.putExtra("Diastolic", String.valueOf(pressurelist.get(position).getDiastolic()));
                intent.putExtra("MeasuredOn", String.valueOf(pressurelist.get(position).getMeasuredOn()));
                context.startActivity(intent);
            }
        };
    }

    @Override
    public int getItemCount() {
        return (null != pressurelist ? pressurelist.size() : 0);
    }

    class pressureHolder extends RecyclerView.ViewHolder {
        TextView systolic, diastolic, measuredOn;
        View container;

        public pressureHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.pressurecardview);
            systolic = (TextView) itemView.findViewById(R.id.Textsystolic);
            diastolic = (TextView) itemView.findViewById(R.id.Textdiastolic);
            measuredOn = (TextView) itemView.findViewById(R.id.TextmeasuredOn); //Need to think logic to spilt the date and time to display in rycler view


        }
    }


}
