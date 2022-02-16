package com.example.maditrack.Model;

import com.example.maditrack.MediTracklFolder.Consumption;

public class MedicineConsumption {
    private String medicineName;
    private Consumption consumption;
    private int consumedQty;
    private int consumedOn;

    public MedicineConsumption(String medicineName, int consumptionQty, int consumedOn) {
        this.medicineName = medicineName;
        this.consumedQty = consumptionQty;
        this.consumedOn = consumedOn;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public Consumption getConsumption() {
        return consumption;
    }

    public int getConsumedQty() {
        return consumedQty;
    }

    public int getConsumedOn() {
        return consumedOn;
    }
}
