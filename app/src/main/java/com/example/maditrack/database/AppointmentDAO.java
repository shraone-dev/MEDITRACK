package com.example.maditrack.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import com.example.maditrack.MediTracklFolder.Appointment;

public class AppointmentDAO extends DBHelper {
    public AppointmentDAO(Context context) {
        super(context);
    }

    public boolean addAppointment(Appointment appointment){
        SQLiteDatabase db=this.getWritableDatabase();
        try
        {
            String appointmentDateTime = appointment.getDate() +"_" +appointment.getTime();
            ContentValues contentValues = fillContentValues(appointment, appointmentDateTime);

            db.insert(Constant.Appointment_Table_Name, null, contentValues);
            return true;
        }
        catch (SQLiteException e)
        {
            Log.i("Appointment insert", Constant.ErrorMsg_RecordNotAdded);
            return false;
        }
        finally {
            db.close();
        }
    }

    public boolean updateAppointment(Appointment appointmentToUpdate)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        try {
            String appointmentDateTime = appointmentToUpdate.getDate() +"_" +appointmentToUpdate.getTime();
            ContentValues contentValues = fillContentValues(appointmentToUpdate, appointmentDateTime);

            db.update(Constant.Appointment_Table_Name, contentValues, Constant.COLUMN_ID + "= ?", new String[]{Integer.toString(appointmentToUpdate.getId())});
            return true;
        }
        catch(SQLiteException ex)
        {
            Log.i("Appointment update",Constant.ErrorMsg_RecordNotUpdated);
            return false;
        }
        finally {
            db.close();
        }
    }

    public Integer deleteAppointment(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(Constant.Appointment_Table_Name, Constant.COLUMN_ID + "= ?", new String[]{Integer.toString(id)});
    }

    public ArrayList<Appointment> getAllAppointments(){
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<Appointment> appointmentArrayList = new ArrayList<>();
        Cursor cursor=db.rawQuery(Constant.AppointmentSelectQuery,null);

        while (cursor.moveToNext())
        {
            Appointment appointment = processAppointmentCursor(cursor);
            appointmentArrayList.add(appointment);
        }
        return appointmentArrayList;
    }

    public Appointment getAppointmentById(int appointmentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = " SELECT * FROM Appointment WHERE _ID = "+ appointmentId;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        try{
            Appointment appointment = processAppointmentCursor(cursor);
            return appointment;
        }
        catch (Exception e){
            return null;
        }
        finally {
            db.close();
        }
    }

    @NonNull
    private Appointment processAppointmentCursor(Cursor cursor) {
        int id = cursor.getInt(0);
        String location = cursor.getString(1);
        String appointmentDateTime = cursor.getString(2);
        String description = cursor.getString(3);

        String array[] = appointmentDateTime.split("_");
        String appointmentDate = array[0];
        String appointmentTime = array[1];

        return new Appointment(id,location, description, appointmentDate,appointmentTime);
    }

    @NonNull
    private ContentValues fillContentValues(Appointment appointmentToUpdate, String appointmentDateTime) {
        ContentValues contantValues = new ContentValues();
        contantValues.put(Constant.LOCATION, appointmentToUpdate.getLocation());
        contantValues.put(Constant.DESCRIPTION, appointmentToUpdate.getDescription());
        contantValues.put(Constant.APPOINTMENTDATETIME, appointmentDateTime);
        return contantValues;
    }

}
