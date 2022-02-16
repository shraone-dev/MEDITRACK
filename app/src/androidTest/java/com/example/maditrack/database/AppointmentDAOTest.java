package com.example.maditrack.database;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import com.example.maditrack.MediTracklFolder.Appointment;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vipul Zambare on 3/19/2017.
 */


@RunWith(AndroidJUnit4.class)
public class AppointmentDAOTest {
    private DBHelper database;
    AppointmentDAO appointmentDAO;

    @Before
    public void setUp() throws Exception {
        getTargetContext().deleteDatabase("MedipalDB.db");
        database = new DBHelper(getTargetContext());
        appointmentDAO = new AppointmentDAO(getTargetContext());
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }

    @Test
    public void useAppContext() throws Exception {
        Context appContext = getTargetContext();
        assertEquals("com.example.medipalapplication", appContext.getPackageName());
    }

    @Test
    public void shouldAddAppointment() throws Exception
    {
        ArrayList<Appointment> appointments;

        appointments = appointmentDAO.getAllAppointments();
        assertTrue(appointments.size() == 0);

        appointmentDAO.addAppointment(new Appointment(1,"Clementi Hospital","Routine checkup","22-10-2017","3:00 PM"));
        appointmentDAO.addAppointment(new Appointment(2,"City Hospital","Blood checkup","26-10-2017","5:00 PM"));
        appointmentDAO.addAppointment(new Appointment(3,"Central Hospital","X-RAY","26-10-2017","6:00 PM"));
        appointmentDAO.addAppointment(new Appointment(4,"West coast Hospital","Routine checkup","28-10-2017","8:00 PM"));
        appointmentDAO.addAppointment(new Appointment(5,"East coast Hospital","Routine checkup","30-10-2017","9:00 PM"));

        appointments = appointmentDAO.getAllAppointments();
        assertTrue(appointments.size() == 5);
    }

    @Test
    public void shouldUpdateAppointment() throws Exception
    {
        ArrayList<Appointment> appointments;

        appointments = appointmentDAO.getAllAppointments();
        assertTrue(appointments.size() == 0);

        appointmentDAO.addAppointment(new Appointment(1,"Clementi Hospital","Routine checkup","22-10-2017","3:00 PM"));
        appointmentDAO.addAppointment(new Appointment(2,"City Hospital","Routine checkup","26-10-2017","5:00 PM"));

        appointments = appointmentDAO.getAllAppointments();
        assertTrue(appointments.size() == 2);

        Appointment appointmentToUpdate = new Appointment(2, "City Hospital Update", "Routine checkup_updated" , "26-10-2017","8:00 PM");

        boolean result = appointmentDAO.updateAppointment(appointmentToUpdate);
        assertTrue(result);

        Appointment updatedAppointment = appointmentDAO.getAppointmentById(2);

        assertEquals(appointmentToUpdate.getLocation(), updatedAppointment.getLocation());
        assertEquals(appointmentToUpdate.getDate(), updatedAppointment.getDate());
        assertEquals(appointmentToUpdate.getTime(), updatedAppointment.getTime());
    }

    @Test
    public void shouldDeleteAppointment() throws Exception
    {
        ArrayList<Appointment> appointments;

        appointments = appointmentDAO.getAllAppointments();
        assertTrue(appointments.size() == 0);

        appointmentDAO.addAppointment(new Appointment(1,"Clementi Hospital","Routine checkup","22-10-2017","3:00 PM"));
        appointmentDAO.addAppointment(new Appointment(2,"City Hospital","Routine checkup","26-10-2017","5:00 PM"));

        appointments = appointmentDAO.getAllAppointments();
        assertTrue(appointments.size() == 2);

        appointmentDAO.deleteAppointment(2);
        assertTrue(appointments.size() == 2);

        Appointment deletedAppointment = appointmentDAO.getAppointmentById(2);
        assertEquals(deletedAppointment, null);
    }
}
