package com.example.maditrack.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PulseDAO extends DBHelper {

    public PulseDAO(Context context) {
        super(context);
    }

    public long addPulseDetailsToDatabase(int pulse, String dateTime) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.PULSE, pulse);
        contentValues.put(Constant.MEASUREDON, dateTime);
        return db.insert(Constant.Measurement_Table_Name, null, contentValues);
    }

    public Cursor GetAllPulse() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + Constant.COLUMN_ID + ", " + Constant.PULSE + " , " + Constant.MEASUREDON + " FROM " + Constant.Measurement_Table_Name
                        + " Where "
                        + Constant.PULSE
                        + " IS NOT NULL "

                , null);
        return cursor;
    }

    public long updatePulseDetailsToDatabase(int pulse, String dateTime, int measurementID) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.COLUMN_ID, measurementID);
        contentValues.put(Constant.PULSE, pulse);
        contentValues.put(Constant.MEASUREDON, dateTime);

        return db.update(Constant.Measurement_Table_Name, contentValues, Constant.COLUMN_ID + "=?", new String[]{String.valueOf(measurementID)});
    }


}
