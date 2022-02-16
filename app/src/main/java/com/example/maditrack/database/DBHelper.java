package com.example.maditrack.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MedipalDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_PERSONALBIO = "CREATE TABLE " + Constant.PersonalBio_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.COLUMN_NAME + " TEXT NOT NULL, "
            + Constant.DATEOFBIRTH + " INTEGER NOT NULL , "
            + Constant.UNIQUEID + " INTEGER NOT NULL ,"
            + Constant.PERSONALADDRESS + " TEXT NOT NULL, "
            + Constant.POSTALCODE + " TEXT NOT NULL,"
            + Constant.HEIGHT + " INTEGER NOT NULL, "
            + Constant.BLOODTYPE + " TEXT NOT NULL );";
    private static final String CREATE_HEALTHBIO = "CREATE TABLE " + Constant.HealthBio_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.CONDITION + " TEXT NOT NULL, "
            + Constant.STARTDATE + " TEXT NOT NULL, "
            + Constant.CONDITIONTYPE + " TEXT NOT NULL );";

    private static final String CREATE_CATEGORY = "CREATE TABLE " + Constant.TableName + "(" +
            Constant.CategoryId + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            Constant.Categoryname + " TEXT NOT NULL, " +
            Constant.CategoryCode + " TEXT NOT NULL, " +
            Constant.CategoryDescription + " TEXT NOT NULL, " +
            Constant.CategoryReminder + " INTEGER );";

    private static final String CREATE_MEDICINE = "CREATE TABLE " + Constant.MedicineTableName + "(" +
            Constant.MedicineId + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            Constant.MedicineName + " TEXT NOT NULL, " +
            Constant.MedicineDescription + " TEXT NOT NULL, " +
            Constant.MedicineCatId + " INTEGER NOT NULL, " +
            Constant.MedicineRemenderId + " INTEGER NULL DEFAULT 0, " +
            Constant.MedicineRemind + " INTEGER NOT NULL DEFAULT 0, " +
            Constant.MedicineQuantity + " INTEGER NOT NULL, " +
            Constant.MedicineDosage + " INTEGER NOT NULL, " +
            Constant.MedicineDateIssued + " INTEGER NOT NULL, " +
            Constant.MedicineConsumeQuantity + " INTEGER NOT NULL, " +
            Constant.MedicineThreshold + " INTEGER NOT NULL, " +
            Constant.MedicineExpireFactor + " INTEGER NOT NULL);";

    private static final String CREATE_MEASUREMENT = " CREATE TABLE " + Constant.Measurement_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.SYSTOLIC + " INTEGER  NULL, "
            + Constant.DIASTOLIC + " INTEGER  NULL, "
            + Constant.PULSE + " INTEGER  NULL, "
            + Constant.TEMPERATURE + " INTEGER  NULL, "
            + Constant.WEIGHT + " INTEGER  NULL, "
            + Constant.MEASUREDON + " TEXT  NULL);";

    private static final String CREATE_CONSUMPTIION = " CREATE TABLE " + Constant.Consumption_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.MEDICINEID + " INTEGER NOT NULL, "
            + Constant.QUANTITY + " INTEGER NOT NULL, "
            + Constant.CONSUMEDON + " TEXT NOT NULL);";

    private static final String CREATE_APPOINTEMENT = " CREATE TABLE " + Constant.Appointment_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.LOCATION +  " TEXT NOT NULL, "
            + Constant.APPOINTMENTDATETIME + " TEXT NOT NULL, "
            + Constant.DESCRIPTION + " TEXT NOT NULL);";

    private static final String CREATE_REMINDER = " CREATE TABLE " + Constant.Reminder_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.FREQUENCY + " INTEGER  NOT NULL, "
            + Constant.STARTTIME + " TEXT NOT NULL, "
            + Constant.INTERVAL + " INTEGER NOT NULL);";
    private static final String CREATE_ICE = "CREATE TABLE " + Constant.ICE_Table_Name
            + "("
            + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.COLUMN_NAME + " TEXT NOT NULL, "
            + Constant.CONTACTNO + " INTEGER NOT NULL, "
            + Constant.CONTACTTYPE + " TEXT NOT NULL, "
            + Constant.DESCRIPTION + " TEXT NOT NULL,"
            + Constant.PREFERENCES + " TEXT NOT NULL);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Category_Table_Row1 = "insert into " + Constant.TableName + " (" + Constant.Categoryname + ", " + Constant.CategoryCode + "," + Constant.CategoryDescription + ") values('" + Constant.Categorynamevalue + "','" + Constant.CategoryCodevalue + "','" + Constant.CategoryDescriptionvalue + "')";
        String Category_Table_Row2 = "insert into " + Constant.TableName + " (" + Constant.Categoryname + ", " + Constant.CategoryCode + "," + Constant.CategoryDescription + ") values('" + Constant.Categorynamevalue2 + "','" + Constant.CategoryCodevalue2 + "','" + Constant.CategoryDescriptionvalue2 + "')";
        String Category_Table_Row3 = "insert into " + Constant.TableName + " (" + Constant.Categoryname + ", " + Constant.CategoryCode + "," + Constant.CategoryDescription + ") values('" + Constant.Categorynamevalue3 + "','" + Constant.CategoryCodevalue3 + "','" + Constant.CategoryDescriptionvalue3 + "')";
        String Category_Table_Row4 = "insert into " + Constant.TableName + " (" + Constant.Categoryname + ", " + Constant.CategoryCode + "," + Constant.CategoryDescription + ") values('" + Constant.Categorynamevalue4 + "','" + Constant.CategoryCodevalue4 + "','" + Constant.CategoryDescriptionvalue4 + "')";
        String Category_Table_Row5 = "insert into " + Constant.TableName + " (" + Constant.Categoryname + ", " + Constant.CategoryCode + "," + Constant.CategoryDescription + ") values('" + Constant.Categorynamevalue5 + "','" + Constant.CategoryCodevalue5 + "','" + Constant.CategoryDescriptionvalue5 + "')";

        db.execSQL(CREATE_PERSONALBIO);
        db.execSQL(CREATE_HEALTHBIO);
        db.execSQL(CREATE_CATEGORY);
        db.execSQL(CREATE_MEDICINE);
        db.execSQL(CREATE_MEASUREMENT);
        db.execSQL(CREATE_CONSUMPTIION);
        db.execSQL(CREATE_REMINDER);
        db.execSQL(CREATE_APPOINTEMENT);
        db.execSQL(CREATE_ICE);

        db.execSQL(Category_Table_Row1);
        db.execSQL(Category_Table_Row2);
        db.execSQL(Category_Table_Row3);
        db.execSQL(Category_Table_Row4);
        db.execSQL(Category_Table_Row5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_PERSONALBIO);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_HEALTHBIO);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_MEDICINE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_MEASUREMENT);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_CONSUMPTIION);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_REMINDER);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_APPOINTEMENT);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_ICE);
    }
}





