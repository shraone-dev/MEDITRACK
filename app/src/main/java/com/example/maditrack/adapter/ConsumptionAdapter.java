package com.example.maditrack.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.example.maditrack.MediTracklFolder.Medicine;
import com.example.maditrack.Model.MedicineConsumption;

import com.example.maditrack.R;

public class ConsumptionAdapter extends RecyclerView.Adapter<ConsumptionAdapter.ViewHolder> {

    private List<MedicineConsumption> medicineConsumptionList;

    private Activity activity;

    public ConsumptionAdapter(Activity activity, List<Medicine> medicines, List<MedicineConsumption> medicineConsumptionList) {
        this.medicineConsumptionList=medicineConsumptionList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_recycler_consumption, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ConsumptionAdapter.ViewHolder viewHolder, int position) {
        viewHolder.txtMedicineName.setText(medicineConsumptionList.get(position).getMedicineName());
        viewHolder.txtQuantityConsumed.setText(Integer.toString(medicineConsumptionList.get(position).getConsumedQty()));
        viewHolder.txtConsumedOn.setText(Integer.toString(medicineConsumptionList.get(position).getConsumedOn()));

    }

    @Override
    public int getItemCount() {
        return (null != medicineConsumptionList ? medicineConsumptionList.size() : 0);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMedicineName;
        private TextView txtQuantityConsumed;
        private TextView txtConsumedOn;
        private View container;

        public ViewHolder(View view) {
            super(view);

            container = view.findViewById(R.id.card_view_consumption);
            txtMedicineName = (TextView) view.findViewById(R.id.txtMedicineName);
            txtQuantityConsumed = (TextView) view.findViewById(R.id.txtQuantityConsumed);
            txtConsumedOn = (TextView) view.findViewById(R.id.txtConsumedOn);
        }
    }
}
