package com.example.maditrack.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PressureDAO extends DBHelper {
    public PressureDAO(Context context) {
        super(context);
    }

    public long addPressureDetailsToDatabase(int systolic, int diastolic, String dateTime) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Constant.SYSTOLIC, systolic);
        contentValues.put(Constant.DIASTOLIC, diastolic);
        contentValues.put(Constant.MEASUREDON, dateTime);

        return db.insert(Constant.Measurement_Table_Name, null, contentValues);
    }

    public Cursor GetAllPressure() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + Constant.COLUMN_ID + ", " + Constant.SYSTOLIC + " , " + Constant.DIASTOLIC + " , " + Constant.MEASUREDON + " FROM " + Constant.Measurement_Table_Name
                        + " Where "
                        + Constant.SYSTOLIC
                        + " IS NOT NULL "

                , null);
        return cursor;
    }

    public long updatePressureDetailsToDatabase(int systolic, int diastolic, String dateTime, int measurementID) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.COLUMN_ID, measurementID);
        contentValues.put(Constant.SYSTOLIC, systolic);
        contentValues.put(Constant.DIASTOLIC, diastolic);
        contentValues.put(Constant.MEASUREDON, dateTime);

        return db.update(Constant.Measurement_Table_Name, contentValues, Constant.COLUMN_ID + "=?", new String[]{String.valueOf(measurementID)});
    }

}
