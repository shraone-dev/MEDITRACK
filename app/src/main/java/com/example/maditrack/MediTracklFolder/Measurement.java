package com.example.maditrack.MediTracklFolder;

import android.content.Context;

import java.util.ArrayList;
import java.util.Objects;

public class Measurement {

    private int measurementId;
    private String measuredOn;

    public Measurement() {
    }

    public Measurement(int measurementId, String measuredOn) {
        this.measurementId = measurementId;
        this.measuredOn = measuredOn;
    }

    public int getMeasurementId() {

        return measurementId;
    }

    public String getMeasuredOn() {
        return measuredOn;
    }

    public String toString() {


        return null;
    }

    public void addMeasurement(Measurement measurement, Context context) {

    }

    public ArrayList showDisplayMeasurements(Context context) {
        ArrayList<Objects> arrayList = new ArrayList<Objects>();
        return arrayList;
    }

    public void updateMeasurement(Measurement measurement, Context context) {

    }

    public boolean equals(Object measurement) {
        if (measurement instanceof Measurement) {
            Measurement measurement1 = (Measurement) measurement;
            return this.getMeasurementId() == (measurement1.getMeasurementId())
                    && (this.getMeasuredOn().equals(measurement1.getMeasuredOn()));
        }
        return true;
    }
    
}
