package com.example.maditrack.MediTracklFolder;

public class Reminder {
    private int id;
    private int frequency;
    private int interval;
    private String startDateTime;

    public Reminder() {

    }

    public Reminder(int id, int frequency, int interval, String startDateTime) {
        this.id = id;
        this.frequency = frequency;
        this.interval = interval;
        this.startDateTime = startDateTime;
    }

    public int getId() {
        return (int) Math.random();
    }

    public int getFrequency() {
        return frequency;
    }

    public int getInterval() {
        return interval;
    }

    public String getstartDateTime() {
        return startDateTime;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;

        return id == ((Reminder) obj).id;
        
    }
}
