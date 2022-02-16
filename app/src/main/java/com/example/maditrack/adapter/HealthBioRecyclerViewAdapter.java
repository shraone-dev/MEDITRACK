package com.example.maditrack.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.example.maditrack.MediTracklFolder.HealthBio;
import com.example.maditrack.activity.AddHealthBioActivity;

import com.example.maditrack.R;

public class HealthBioRecyclerViewAdapter extends RecyclerView.Adapter<HealthBioRecyclerViewAdapter.ViewHolder> {

    private List<HealthBio> healthbio;
    private Activity activity;

    public HealthBioRecyclerViewAdapter(Activity activity, List<HealthBio> healthbio) {
        this.healthbio = healthbio;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_healthbio, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HealthBioRecyclerViewAdapter.ViewHolder viewHolder, int position) {
        viewHolder.condition.setText(healthbio.get(position).getCondition());
        viewHolder.startdate.setText(healthbio.get(position).getStartdate());
        viewHolder.conditiontype.setText(healthbio.get(position).getConditionType());

        viewHolder.container.setOnClickListener(onClickListener(position));
    }

    @Override
    public int getItemCount() {
        return (null != healthbio ? healthbio.size() : 0);
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AddHealthBioActivity.class);

                intent.putExtra("Id", healthbio.get(position).getId());
                intent.putExtra("condition", healthbio.get(position).getCondition());
                intent.putExtra("startdate", healthbio.get(position).getStartdate());
                intent.putExtra("conditiontype", healthbio.get(position).getConditionType());

                activity.startActivity(intent);
            }
        };
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView condition;
        private TextView startdate;
        private TextView conditiontype;
        private View container;

        public ViewHolder(View view) {
            super(view);

            container = view.findViewById(R.id.card_view);
            condition = (TextView) view.findViewById(R.id.txtAptCondition);
            startdate = (TextView) view.findViewById(R.id.txtAptStartDate);
            conditiontype = (TextView) view.findViewById(R.id.txtAptType);
        }
    }
}
