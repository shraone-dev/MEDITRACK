package com.example.maditrack.MediTracklFolder;

import android.database.Cursor;

import java.util.ArrayList;

import com.example.maditrack.database.EmergencyContactDAO;

public class EmergencyContact {

    private int id;
    private String name;
    private String number;
    private String contactType;
    private String desc;
    private String preference;

    public EmergencyContact() {
    }

    public EmergencyContact(int id, String name, String number, String contactType, String desc, String preference) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.contactType = contactType;
        this.desc = desc;
        this.preference = preference;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getContactType() {
        return contactType;
    }

    public String getDesc() {
        return desc;
    }

    public String getPreference() {
        return preference;
    }

    public void addContact(EmergencyContact emergencyContact, EmergencyContactDAO emergencyContactDAO) {
        emergencyContactDAO.addContact(emergencyContact);
    }

    /*public Cursor getContacts(EmergencyContactDAO emergencyContactDAO) {
        return emergencyContactDAO.getAllContact();
    }*/

    public ArrayList<EmergencyContact> getContacts(EmergencyContactDAO emergencyContactDAO) {

        ArrayList<EmergencyContact> contactArrayList = new ArrayList<>();
        Cursor cursor = emergencyContactDAO.getAllContact();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String number = cursor.getString(2);
            String desc = cursor.getString(3);
            String preference = cursor.getString(4);

            EmergencyContact emergencyContact = new EmergencyContact(id, name, number, contactType, desc, preference);
            contactArrayList.add(emergencyContact);
        }
        return contactArrayList;
    }


    public Cursor getNOKContacts(EmergencyContactDAO emergencyContactDAO) {
        return emergencyContactDAO.getNOKContact();
    }

    public Cursor getDoctorContacts(EmergencyContactDAO emergencyContactDAO) {
        return emergencyContactDAO.getDoctorContact();
    }

    public Cursor getEmergencyContacts(EmergencyContactDAO emergencyContactDAO) {
        return emergencyContactDAO.getEmergencyContact();
    }

    public boolean updateContact(EmergencyContact updateContact, EmergencyContactDAO emergencyContactDAO) {
        return emergencyContactDAO.updateContact(updateContact);
    }

    public void deleteContact(int contactID, EmergencyContactDAO emergencyContactDAO) {
        emergencyContactDAO.deleteContact(contactID);
    }

    public boolean equals(Object person) {
        if (person instanceof EmergencyContact) {
            EmergencyContact p = (EmergencyContact) person;
            if (this.getName().equals(p.getName())
                    && this.getNumber().equals(p.getNumber()))
                if (this.getDesc() == null) {
                    return p.getDesc() == null;
                } else if (p.getDesc() != null)
                    if (this.getDesc().equals(p.getDesc()))
                        return true;
        }
        return false;
    }

    public String toString() {
        String fullName = name;
        return (fullName);
    }

    public void show() {
        System.out.println(this);
    }
}


