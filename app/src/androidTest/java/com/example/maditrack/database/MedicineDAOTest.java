package com.example.maditrack.database;

import android.content.Context;
import android.database.Cursor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import com.example.maditrack.MediTracklFolder.Medicine;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Govardhan on 24/3/2017.
 */
public class MedicineDAOTest {

    MedicineDAO medicineDAO;
    private DBHelper database;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = getTargetContext();

        assertEquals("com.example.medipalapplication", appContext.getPackageName());
    }

    @Before
    public void setUp() throws Exception {
        getTargetContext().deleteDatabase("MedipalDB.db");
        database = new DBHelper(getTargetContext());
        medicineDAO = new MedicineDAO(getTargetContext());
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }


    @Test
    public void medicineAdd() throws Exception {

        ArrayList<Medicine> medicines = new ArrayList<Medicine>();
        //medicineDAO.medicineAdd(new Medicine(1,"chronic","fever","supplement","1","2","6","7","9","9","5","3"));
        Cursor medicineCursor = medicineDAO.getAllMedicine();

        while (medicineCursor.moveToNext()) {
            int id = medicineCursor.getInt(0);
            String medicinename = medicineCursor.getString(1);
            String medicinedescription = medicineCursor.getString(2);
            String medicinecatid = medicineCursor.getString(3);
            String medicinereminderid = medicineCursor.getString(4);
            String medicineremind = medicineCursor.getString(5);
            String medicinequantity = medicineCursor.getString(6);
            String medicinedosage = medicineCursor.getString(7);
            String medicinedateissued = medicineCursor.getString(8);
            String medicineconsumequantity = medicineCursor.getString(9);
            String medicienthreshold = medicineCursor.getString(10);
            String mediceineexpire = medicineCursor.getString(11);

//            Medicine medicine = new Medicine(id, medicinename, medicinedescription, medicinecatid, medicinereminderid, medicineremind, medicinequantity, medicinedosage, medicinedateissued, medicineconsumequantity, medicienthreshold, mediceineexpire);
//            medicines.add(medicine);
        }
        assertTrue(medicines.size() == 1);
    }


    @Test
    public void medicineUpdate() throws Exception {

    }


    @Test
    public void medicineDelete() throws Exception {

    }

}