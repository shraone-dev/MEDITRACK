package com.example.maditrack.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.maditrack.MediTracklFolder.HealthBio;

public class HealthBioDAO extends DBHelper {

    public HealthBioDAO(Context context) {
        super(context);
    }

    public boolean addHealthBio(HealthBio healthBio) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues contantValues = new ContentValues();
            contantValues.put(Constant.CONDITION, healthBio.getCondition());
            contantValues.put(Constant.STARTDATE, healthBio.getStartdate());
            contantValues.put(Constant.CONDITIONTYPE, healthBio.getConditionType());
            db.insert(Constant.HealthBio_Table_Name, null, contantValues);
            return true;
        } catch (SQLiteException e) {
            Log.i("HealthBio insert", Constant.ErrorMsg_RecordNotAdded);
            return false;
        } finally {
            db.close();
        }
    }

    public boolean UpdateHealthBio(HealthBio healthBioToUpdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues contantValues = new ContentValues();
            contantValues.put(Constant.CONDITION, healthBioToUpdate.getCondition());
            contantValues.put(Constant.CONDITIONTYPE, healthBioToUpdate.getStartdate());
            contantValues.put(Constant.STARTDATE, healthBioToUpdate.getConditionType());
            db.update(Constant.HealthBio_Table_Name, contantValues, Constant.COLUMN_ID + "= ?", new String[]{Integer.toString(healthBioToUpdate.getId())});
            return true;
        } catch (SQLiteException ex) {
            Log.i("HealthBio update", Constant.ErrorMsg_RecordNotUpdated);
            return false;
        } finally {
            db.close();
        }
    }

    public Integer DeleteHealthbio(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Constant.HealthBio_Table_Name, Constant.COLUMN_ID + "= ?", new String[]{Integer.toString(id)});
    }

    public Cursor GetAllHeathBio() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Constant.HealthBioSelectQuery, null);
        return cursor;
    }
}
