package com.example.maditrack.MediTracklFolder;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import com.example.maditrack.database.WeightDAO;

public class Weight extends Measurement {
    private int weight;
    private WeightDAO weightDbInsert;
    private WeightDAO weightDbupdate;

    public Weight(int measurementId, String measuredOn, int weight) {
        super(measurementId, measuredOn);
        this.weight = weight;
    }

    public Weight() {

    }

    public int getWeight() {
        return weight;
    }

    public ArrayList<Weight> showDisplayMeasurements(Context context) {
        ArrayList<Weight> weightArrayList = new ArrayList<Weight>();
        weightDbInsert = new WeightDAO(context);
        Cursor cursor = weightDbInsert.GetAllWeight();
        while (cursor.moveToNext()) {
            int measurementid = cursor.getInt(0);
            int weight = cursor.getInt(1);
            String measuredOn = cursor.getString(2);

            Weight weigh = new Weight(measurementid, measuredOn, weight);
            weightArrayList.add(weigh);

        }

        return weightArrayList;

    }

    @Override
    public void addMeasurement(Measurement m, Context context) {
        Weight kg = (Weight) m;
        weightDbInsert = new WeightDAO(context);
        weightDbInsert.addWeightDetailsToDatabase(kg.weight, kg.getMeasuredOn());
    }

    public void updateMeasurement(Measurement updatedWeight, Context context) {
        Weight upWeight = (Weight) updatedWeight;
        weightDbupdate = new WeightDAO(context);
        weightDbupdate.updateWeightDetailsToDatabase(upWeight.weight, upWeight.getMeasuredOn(), upWeight.getMeasurementId());

    }
}
