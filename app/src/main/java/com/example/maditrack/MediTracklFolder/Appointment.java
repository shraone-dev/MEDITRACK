package com.example.maditrack.MediTracklFolder;

public class Appointment {

    private int id;
    private String location;
    private String description;
    private String date;
    private String time;

    public Appointment(){
    }

    public Appointment(int id, String location, String description, String date, String time) {
        this.location = location;
        this.description = description;
        this.date = date;
        this.time = time;
        this.id = id;
    }

    public String getLocation(){
        return location;
    }

    public String getDescription(){
        return description;
    }

    public String getDate(){
        return date;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }
}
