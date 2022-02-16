package com.example.maditrack.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.maditrack.MediTracklFolder.EmergencyContact;

public class EmergencyContactDAO extends DBHelper {
    public EmergencyContactDAO(Context context) {
        super(context);
    }

    public boolean addContact(EmergencyContact emergencyContact) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();

            values.put(Constant.COLUMN_NAME, emergencyContact.getName());
            values.put(Constant.CONTACTNO, emergencyContact.getNumber());
            values.put(Constant.CONTACTTYPE, emergencyContact.getContactType());
            values.put(Constant.DESCRIPTION, emergencyContact.getDesc());
            values.put(Constant.PREFERENCES, emergencyContact.getPreference());

            db.insert(Constant.ICE_Table_Name, null, values);

            return true;
        } catch (SQLiteException e) {
            Log.i("Contact insert", "Unable to insert Contact!");
            return false;
        } finally {
            db.close();
        }
    }

    public boolean updateContact(EmergencyContact updateEmergencyContact) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();

            values.put(Constant.COLUMN_NAME, updateEmergencyContact.getName());
            values.put(Constant.CONTACTNO, updateEmergencyContact.getNumber());
            values.put(Constant.CONTACTTYPE, updateEmergencyContact.getContactType());
            values.put(Constant.DESCRIPTION, updateEmergencyContact.getDesc());
            values.put(Constant.PREFERENCES, updateEmergencyContact.getPreference());

            db.update(Constant.ICE_Table_Name, values, Constant.COLUMN_ID + "= ?", new String[]{Integer.toString(updateEmergencyContact.getId())});
            return true;
        } catch (SQLiteException ex) {
            Log.i("Contact update", "unable to update contact");
            return false;
        } finally {
            db.close();
        }
    }

    public Integer deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Constant.ICE_Table_Name, Constant.COLUMN_ID + " = ? ", new String[]{Integer.toString(id)});

    }

    public Cursor getAllContact() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + Constant.ICE_Table_Name, null);
        return cursor;
    }

    public Cursor getNOKContact() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + Constant.ICE_Table_Name + " where " + Constant.CONTACTTYPE + " = 'NextOfKin' ", null);
        return cursor;
    }

    public Cursor getDoctorContact() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + Constant.ICE_Table_Name + " where " + Constant.CONTACTTYPE + " = 'Doctor' ", null);
        return cursor;
    }

    public Cursor getEmergencyContact() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + Constant.ICE_Table_Name + " where " + Constant.PREFERENCES + " = 'YES' ", null);
        return cursor;
    }
}
