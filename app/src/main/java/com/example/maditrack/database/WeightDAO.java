package com.example.maditrack.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class WeightDAO extends DBHelper {

    public WeightDAO(Context context) {
        super(context);
    }

    public long addWeightDetailsToDatabase(int weight, String dateTime) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.WEIGHT, weight);
        contentValues.put(Constant.MEASUREDON, dateTime);
        return db.insert(Constant.Measurement_Table_Name, null, contentValues);
    }

    public Cursor GetAllWeight() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + Constant.COLUMN_ID + ", " + Constant.WEIGHT + " , " + Constant.MEASUREDON + " FROM " + Constant.Measurement_Table_Name
                        + " Where "
                        + Constant.WEIGHT
                        + " IS NOT NULL "

                , null);
        return cursor;
    }

    public long updateWeightDetailsToDatabase(int weight, String dateTime, int measurementID) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.COLUMN_ID, measurementID);
        contentValues.put(Constant.WEIGHT, weight);
        contentValues.put(Constant.MEASUREDON, dateTime);

        return db.update(Constant.Measurement_Table_Name, contentValues, Constant.COLUMN_ID + "=?", new String[]{String.valueOf(measurementID)});
    }


}
