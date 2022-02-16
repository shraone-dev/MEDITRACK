package com.example.maditrack.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maditrack.MediTracklFolder.Person;

public class PersonDAO extends DBHelper {


    public PersonDAO(Context context) {
        super(context);
    }

    public long addPerson(String name,String idno, String dateofbirth,String address,String bloodtype,String postalcode,String height){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put(Constant.COLUMN_NAME,name);
        contantValues.put(Constant.UNIQUEID,idno);
        contantValues.put(Constant.DATEOFBIRTH,dateofbirth);
        contantValues.put(Constant.PERSONALADDRESS,address);
        contantValues.put(Constant.BLOODTYPE,bloodtype);
        contantValues.put(Constant.POSTALCODE,postalcode);
        contantValues.put(Constant.HEIGHT,height);

        return db.insert(Constant.PersonalBio_Table_Name, null, contantValues);
    }

    public boolean UpdatePerson(Person person)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put(Constant.COLUMN_NAME,person.getName());
        contantValues.put(Constant.DATEOFBIRTH,person.getDateofbirth());
        contantValues.put(Constant.UNIQUEID,person.getIdno());
        contantValues.put(Constant.PERSONALADDRESS,person.getAddress());
        contantValues.put(Constant.POSTALCODE,person.getPostalcode());
        contantValues.put(Constant.HEIGHT,person.getHeight());
        contantValues.put(Constant.BLOODTYPE,person.getBloodtype());

        db.update(Constant.PersonalBio_Table_Name, contantValues, Constant.COLUMN_ID + "= ?", new String[]{Integer.toString(person.getId())});
        db.close();
        return true;
    }
    public Cursor GetPerson(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from PERSONALBIO",null);
        return cursor;
    }
}