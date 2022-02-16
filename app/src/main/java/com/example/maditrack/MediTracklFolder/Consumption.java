package com.example.maditrack.MediTracklFolder;

import java.util.Date;

import com.example.maditrack.database.ConsumptionDAO;

public class Consumption {
    private int id;
    private int medicineID;
    private int quantity;
    private Date date;

    public Consumption() {
    }

    public Consumption(int id, int medicineID, int quantity, Date date) {
        this.id = id;
        this.medicineID = medicineID;
        this.quantity = quantity;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getMedicineID() {
        return medicineID;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getDate() {
        return date;
    }

    public void addConsumption(Consumption consumption, ConsumptionDAO consumptionDAO) {
        consumptionDAO.addConsumption(consumption);
    }
}
