package com.example.maditrack.MediTracklFolder;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import com.example.maditrack.database.PressureDAO;

public class Pressure extends Measurement {

    private int systolic;
    private int diastolic;
    private PressureDAO pressureDbInsert;
    private PressureDAO pressureDbupdate;

    public Pressure(int measurementId, String measuredOn, int systolic, int diastolic) {
        super(measurementId, measuredOn);
        this.systolic = systolic;
        this.diastolic = diastolic;
    }

    public Pressure() {
    }

    public int getSystolic() {
        return systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    @Override
    public ArrayList<Pressure> showDisplayMeasurements(Context context) {
        ArrayList<Pressure> pressureArrayList = new ArrayList<Pressure>();
        pressureDbInsert = new PressureDAO(context);
        Cursor cursor = pressureDbInsert.GetAllPressure();

        while (cursor.moveToNext()) {
            int measurementid = cursor.getInt(0);
            int systolic = cursor.getInt(1);
            int diastolic = cursor.getInt(2);
            String measuredOn = cursor.getString(3);

            Pressure pressure = new Pressure(measurementid, measuredOn, systolic, diastolic);
            pressureArrayList.add(pressure);
        }
        return pressureArrayList;
    }

    @Override
    public void addMeasurement(Measurement m, Context context) {
        Pressure p = (Pressure) m;
        pressureDbInsert = new PressureDAO(context);
        pressureDbInsert.addPressureDetailsToDatabase(p.systolic, p.diastolic, p.getMeasuredOn());
    }


    public void updateMeasurement(Measurement updatedPressure, Context context) {
        Pressure upPressure = (Pressure) updatedPressure;
        pressureDbupdate = new PressureDAO(context);
        pressureDbupdate.updatePressureDetailsToDatabase(upPressure.systolic, upPressure.diastolic, upPressure.getMeasuredOn(), upPressure.getMeasurementId());

    }
}
