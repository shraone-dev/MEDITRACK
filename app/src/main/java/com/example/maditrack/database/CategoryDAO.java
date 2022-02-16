package com.example.maditrack.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.maditrack.MediTracklFolder.Category;

public class CategoryDAO {

    Context c;
    SQLiteDatabase db;
    DBHelper databaseHelper;
    ArrayList<Category> categorylist = new ArrayList<Category>();

    public CategoryDAO(Context context) {

        this.c = context;
        databaseHelper = new DBHelper(c);
    }


    public CategoryDAO openDb() {

        db = databaseHelper.getWritableDatabase();

        return this;
    }

    public void close() {

        databaseHelper.close();
    }

    public long categoryUpdate(int id, String categorycode, String categoryname, String categorydescription,String categoryreminder) {
        try {

            ContentValues contentValues = new ContentValues();
            contentValues.put(Constant.CategoryCode, categorycode);
            contentValues.put(Constant.Categoryname, categoryname);
            contentValues.put(Constant.CategoryDescription, categorydescription);
            contentValues.put(Constant.CategoryReminder,categoryreminder);

            return db.update(Constant.TableName, contentValues, Constant.CategoryId + "=?", new String[]{String.valueOf(id)});
        /*long result = sd.insert(Table_Name, null, contentValues); */

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Category> getAllCategory() {
        ArrayList<Category> itemObjectList = new ArrayList<Category>();
        Cursor cursor = databaseHelper.getReadableDatabase().rawQuery("SELECT * FROM categorytable", null);

        while (cursor.moveToNext()) {
            Category category = new Category();

            // editText.append(cursor.getString(1) + "" +cursor.getString(2) + "" +cursor.getString(3));
            category.setId(cursor.getInt(0));
            category.setCategoryname(cursor.getString(1));
            category.setCategorycode(cursor.getString(2));
            category.setCategorydescription(cursor.getString(3));
            category.setCategoryreminder(cursor.getString(4));
            itemObjectList.add(category);
        }
        return itemObjectList;
    }

    public Cursor AllCategory() {

        String[] columns = {Constant.CategoryId, Constant.CategoryCode, Constant.Categoryname, Constant.CategoryDescription,Constant.CategoryReminder};
        return db.query(Constant.TableName, columns, null, null, null, null, null);
    }

    public int getCategoryIdByName(String name) {
        String query = "Select * from " + Constant.TableName + " where " + Constant.Categoryname + " = " + "'" + name + "'";

        Cursor cursor = databaseHelper.getReadableDatabase().rawQuery(query, null);
        int id = 0;
        while (cursor.moveToNext()) {
            id = cursor.getInt(0);
        }
        return id;
    }
}
