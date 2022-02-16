package com.example.maditrack.MediTracklFolder;

import android.database.Cursor;

import java.util.ArrayList;

import com.example.maditrack.database.HealthBioDAO;

public class HealthBio {

    private int id;
    private String condition;
    private String startdate;
    private String conditiontype;

    public HealthBio() {
    }

    public HealthBio(int id, String condition, String startdate, String conditiontype) {
        this.condition = condition;
        this.startdate = startdate;
        this.conditiontype = conditiontype;
        this.id = id;
    }

    public void addHealthBio(HealthBio healthBio, HealthBioDAO healthBioDAO) {
        healthBioDAO.addHealthBio(healthBio);
    }

    public ArrayList<HealthBio> getHealthbio(HealthBioDAO healthBioDAO) {

        ArrayList<HealthBio> healthBioArrayList = new ArrayList<>();
        Cursor cursor = healthBioDAO.GetAllHeathBio();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String condition = cursor.getString(1);
            String startdate = cursor.getString(2);
            String conditiontype = cursor.getString(3);

            HealthBio healthBio = new HealthBio(id, condition, startdate, conditiontype);
            healthBioArrayList.add(healthBio);
        }
        return healthBioArrayList;
    }

    public boolean UpdateHealthBioById(HealthBio healthBioToUpdate, HealthBioDAO healthBioDAO) {
        boolean result = healthBioDAO.UpdateHealthBio(healthBioToUpdate);
        return result;
    }

    public void DeleteAppointmentById(int HealthBioId, HealthBioDAO healthBioDAO) {
        healthBioDAO.DeleteHealthbio(HealthBioId);
    }

    public String getCondition() {
        return condition;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getConditionType() {
        return conditiontype;
    }

    public int getId() {
        return id;
    }
}
