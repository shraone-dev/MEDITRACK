package com.example.maditrack.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.maditrack.MediTracklFolder.Reminder;

/**
 * Created by Gaurav on 22-03-2017.
 */

public class ReminderDAO extends DBHelper {

    private DBHelper databaseHelper;
    private SQLiteDatabase db = this.getWritableDatabase();
    private Context c;


    public ReminderDAO(Context context) {

        super(context);
        this.c = context;
        databaseHelper = new DBHelper(c);
    }

    public boolean addReminder(Reminder reminder) {
        try
        {
            ContentValues contantValues = new ContentValues();
            contantValues.put(Constant.FREQUENCY, reminder.getFrequency());
            contantValues.put(Constant.STARTTIME, reminder.getstartDateTime());
            contantValues.put(Constant.INTERVAL, reminder.getInterval());
            db.insert(Constant.Reminder_Table_Name, null, contantValues);
            return true;
        }
        catch (SQLiteException e)
        {
            Log.i("Reminder insert", "Unable to insert Reminder");
            return false;
        }
        finally {
            db.close();
        }
    }

    public boolean UpdateReminder(Reminder reminderToUpdate) {
        try {
            ContentValues contantValues = new ContentValues();
            contantValues.put(Constant.FREQUENCY, reminderToUpdate.getFrequency());
            contantValues.put(Constant.INTERVAL, reminderToUpdate.getInterval());
            contantValues.put(Constant.STARTTIME, reminderToUpdate.getstartDateTime());
            db.update(Constant.Reminder_Table_Name, contantValues, Constant.COLUMN_ID + "= ?", new String[]{Integer.toString(reminderToUpdate.getId())});
            return true;
        } catch (SQLiteException ex) {
            Log.i("Reminder update", "unable to update Reminder");
            return false;
        } finally {
            db.close();
        }
    }

    public long DeleteReminder(int id) {
        try {
            return db.delete(Constant.Reminder_Table_Name, Constant.COLUMN_ID + "=?", new String[]{String.valueOf(id)});

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Cursor getReminderById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Reminder where " + Constant.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        return cursor;
    }

    public Cursor getAllReminders() {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Reminder",null);
        return cursor;
    }

    public Reminder getLastReminder() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = " SELECT * FROM Reminder WHERE _ID = " + " ( SELECT MAX ( " + Constant.COLUMN_ID + " )  FROM Reminder)";

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        int id = cursor.getInt(0);
        int frequency = cursor.getInt(1);
        int interval = cursor.getInt(2);
        String starttime = cursor.getString(3);
        Reminder reminder = new Reminder(id, frequency, interval, starttime);

        return reminder;
    }

    public int getLastReminderId() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "Select MAX (" + Constant.COLUMN_ID + ") from Reminder";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
}
