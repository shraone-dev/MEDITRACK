package com.example.maditrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.maditrack.MediTracklFolder.Weight;
import com.example.maditrack.activity.WeightDisplayEditUpdateActivity;

import com.example.maditrack.R;

public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.weightHolder> {

    private ArrayList<Weight> weightlist = new ArrayList<>();
    private Context context;

    public WeightAdapter(Context context, ArrayList<Weight> weightlist) {
        this.weightlist = weightlist;
        this.context = context;
    }

    @Override
    public weightHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutview = LayoutInflater.from(parent.getContext()).inflate(R.layout.weightviewlayout, parent, false);
        weightHolder recyclerViewHolders = new weightHolder(layoutview);
        return recyclerViewHolders;
    }

    @Override
    public void onBindViewHolder(weightHolder holder, int position) {
        holder.weight.setText(String.valueOf(weightlist.get(position).getWeight()));
        holder.measuredOn.setText(weightlist.get(position).getMeasuredOn());
        holder.container.setOnClickListener(onClickListener(position));
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WeightDisplayEditUpdateActivity.class);
                intent.putExtra("measurementId", weightlist.get(position).getMeasurementId());
                intent.putExtra("weight", weightlist.get(position).getWeight());
                intent.putExtra("MeasuredOn", weightlist.get(position).getMeasuredOn());
                context.startActivity(intent);
            }
        };
    }

    @Override
    public int getItemCount() {
        return (null != weightlist ? weightlist.size() : 0);
    }

    class weightHolder extends RecyclerView.ViewHolder {
        TextView weight, measuredOn;
        View container;

        public weightHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.weightcardview);
            weight = (TextView) itemView.findViewById(R.id.Textweight);
            measuredOn = (TextView) itemView.findViewById(R.id.TextmeasuredOn); //Need to think logic to spilt the date and time to display in rycler view


        }
    }


}
