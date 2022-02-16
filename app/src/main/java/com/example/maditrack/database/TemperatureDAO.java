package com.example.maditrack.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TemperatureDAO extends DBHelper {

    public TemperatureDAO(Context context) {
        super(context);
    }

    public long addTemperatureDetailsToDatabase(float temperature, String dateTime) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.TEMPERATURE, temperature);
        contentValues.put(Constant.MEASUREDON, dateTime);
        return db.insert(Constant.Measurement_Table_Name, null, contentValues);
    }

    public Cursor GetAllTemperature() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + Constant.COLUMN_ID + ", " + Constant.TEMPERATURE + " , " + Constant.MEASUREDON + " FROM " + Constant.Measurement_Table_Name
                        + " Where "
                        + Constant.TEMPERATURE
                        + " IS NOT NULL "

                , null);
        return cursor;
    }

    public long updateTemperatureDetailsToDatabase(float temperature, String dateTime, int measurementID) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.COLUMN_ID, measurementID);
        contentValues.put(Constant.TEMPERATURE, temperature);
        contentValues.put(Constant.MEASUREDON, dateTime);

        return db.update(Constant.Measurement_Table_Name, contentValues, Constant.COLUMN_ID + "=?", new String[]{String.valueOf(measurementID)});
    }


}
