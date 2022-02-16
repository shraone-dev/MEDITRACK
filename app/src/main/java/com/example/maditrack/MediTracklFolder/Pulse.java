package com.example.maditrack.MediTracklFolder;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import com.example.maditrack.database.PulseDAO;

public class Pulse extends Measurement {
    private int pulse;
    private PulseDAO pulseDbInsert;
    private PulseDAO pulseDbupdate;

    public Pulse(int measurementId, String measuredOn, int pulse) {
        super(measurementId, measuredOn);
        this.pulse = pulse;
    }

    public Pulse() {

    }

    public int getPulse() {
        return pulse;
    }

    public ArrayList<Pulse> showDisplayMeasurements(Context context) {
        ArrayList<Pulse> pulseArrayList = new ArrayList<Pulse>();
        pulseDbInsert = new PulseDAO(context);
        Cursor cursor = pulseDbInsert.GetAllPulse();
        while (cursor.moveToNext()) {
            int measurementid = cursor.getInt(0);
            int pulse = cursor.getInt(1);
            String measuredOn = cursor.getString(2);

            Pulse pul = new Pulse(measurementid, measuredOn, pulse);
            pulseArrayList.add(pul);

        }
        return pulseArrayList;
    }

    @Override
    public void addMeasurement(Measurement m, Context context) {
        Pulse puls = (Pulse) m;
        pulseDbInsert = new PulseDAO(context);
        pulseDbInsert.addPulseDetailsToDatabase(puls.pulse, puls.getMeasuredOn());
    }


    public void updateMeasurement(Measurement updatedPulse, Context context) {
        Pulse upPulse = (Pulse) updatedPulse;
        pulseDbupdate = new PulseDAO(context);
        pulseDbupdate.updatePulseDetailsToDatabase(upPulse.pulse, upPulse.getMeasuredOn(), upPulse.getMeasurementId());

    }


}
