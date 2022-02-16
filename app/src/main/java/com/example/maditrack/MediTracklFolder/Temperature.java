package com.example.maditrack.MediTracklFolder;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import com.example.maditrack.database.TemperatureDAO;

public class Temperature extends Measurement {
    private float temperature;
    private TemperatureDAO temperatureDbInsert;
    private TemperatureDAO temperatureDbupdate;

    public Temperature(int measurementId, String measuredOn, float temperature) {
        super(measurementId, measuredOn);
        this.temperature = temperature;
    }

    public Temperature() {

    }

    public float getTemperature() {
        return temperature;
    }

    public ArrayList<Temperature> showDisplayMeasurements(Context context) {
        ArrayList<Temperature> temperatureArrayList = new ArrayList<Temperature>();
        temperatureDbInsert = new TemperatureDAO(context);
        Cursor cursor = temperatureDbInsert.GetAllTemperature();
        while (cursor.moveToNext()) {
            int measurementid = cursor.getInt(0);
            int temperature = cursor.getInt(1);
            String measuredOn = cursor.getString(2);

            Temperature temp = new Temperature(measurementid, measuredOn, temperature);
            temperatureArrayList.add(temp);

        }

        return temperatureArrayList;

    }

    @Override
    public void addMeasurement(Measurement m, Context context) {
        Temperature degree = (Temperature) m;
        temperatureDbInsert = new TemperatureDAO(context);
        temperatureDbInsert.addTemperatureDetailsToDatabase(degree.temperature, degree.getMeasuredOn());
    }

    public void updateMeasurement(Measurement updatedTemperature, Context context) {
        Temperature upTemperature = (Temperature) updatedTemperature;
        temperatureDbupdate = new TemperatureDAO(context);
        temperatureDbupdate.updateTemperatureDetailsToDatabase(upTemperature.temperature, upTemperature.getMeasuredOn(), upTemperature.getMeasurementId());

    }

}
