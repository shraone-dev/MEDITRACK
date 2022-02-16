package com.example.maditrack.MediTracklFolder;

import android.database.Cursor;

import java.util.ArrayList;

import com.example.maditrack.database.MedicineDAO;

public class Medicine {
    private String medicinename, medicinedescription, medicinecatid, medicineremind, medicinequantity, medicinedosage, medicinedataissued, medicineconsumequantity, medicinethreshold, medicineexpirefactor;
    private int id, medicinereminderid;

    public Medicine(String medicinename) {
    this.medicinename=medicinename;
    }

    public Medicine(String medicineName, int quantityConsumed) {
        this.medicinename = medicineName;
        this.medicineconsumequantity = String.valueOf(quantityConsumed);
    }

    public Medicine(int id, String medicinename, String medicinedescription, String medicinecatid, int medicinereminderid, String medicineremind, String medicinequantity, String medicinedosage, String medicinedataissued, String medicineconsumequantity, String medicinethreshold, String medicineexpirefactor) {
        this.id = id;
        this.medicinename = medicinename;
        this.medicinedescription = medicinedescription;
        this.medicinecatid = medicinecatid;
        this.medicinereminderid = medicinereminderid;
        this.medicineremind = medicineremind;
        this.medicinequantity = medicinequantity;
        this.medicinedosage = medicinedosage;
        this.medicinedataissued = medicinedataissued;
        this.medicineconsumequantity = medicineconsumequantity;
        this.medicinethreshold = medicinethreshold;
        this.medicineexpirefactor = medicineexpirefactor;
    }

    public String getMedicinename() {
        return medicinename;
    }

    public void setMedicinename(String medicineName){this.medicinename=medicineName;}

    public String getMedicinedescription() {
        return medicinedescription;
    }

    public String getMedicinecatid() {
        return medicinecatid;
    }

    public int getMedicinereminderid() {
        return medicinereminderid;
    }

    public String getMedicineremind() {
        return medicineremind;
    }

    public String getMedicinequantity() {
        return medicinequantity;
    }

    public String getMedicinedosage() {
        return medicinedosage;
    }

    public String getMedicinedataissued() {
        return medicinedataissued;
    }

    public String getMedicineconsumequantity() {
        return medicineconsumequantity;
    }

    public String getMedicinethreshold() {
        return medicinethreshold;
    }

    public String getMedicineexpirefactor() {
        return medicineexpirefactor;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Medicine> getMedicineName(MedicineDAO medicineDAO) {

        ArrayList<Medicine> medicineArrayList = new ArrayList<Medicine>();
        Cursor cursor = medicineDAO.getAllConsumption();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String medicineName = cursor.getString(1);
            int quantityConsumed = cursor.getInt(2);
            // String appointmentTime = cursor.getString(3);
            //String description = cursor.getString(4);

            Medicine medicine = new Medicine(medicineName, quantityConsumed);
            medicineArrayList.add(medicine);
        }
        return medicineArrayList;
    }

}