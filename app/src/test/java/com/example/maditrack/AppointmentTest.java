//package com.example.medipalapplication;
//import junit.framework.TestCase;
//import org.junit.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import com.example.medipalapplication.MedipalFolder.Appointment;
//
//import andro
//
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//
//import com.example.medipalapplication.database.DBHelper;
//
//public class AppointmentTest extends TestCase {
//    Appointment appointment;
//    @Before
//    public void setUp() throws Exception {
//         appointment = new Appointment();
//        getTargetContext().deleteDatabase(DatabaseHelper.DB_NAME);
//        database = new DatabaseHelper(getTargetContext());
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void testGetAppointments() {
//
//        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
//        appointments = appointment.getAppointments();
//        assertEquals(0,appointments.size());
//    }
//
//    @Test
//    public void testAddAppointment(){
//        DBHelper dbHelper = new DBHelper(null,"db",null,1);
//
//        Appointment appointment = new Appointment(dbHelper,"Clementi Hospital urine test", "Fasting of 12 hours for blooad test", "22-12-2017 6:00 PM");
//
//        List<Appointment> appointments =  appointment.getAppointments();
//        assertTrue(appointments.size() == 0);
//
//        appointment.addAppointment(appointment);
//        appointment.addAppointment("Clementi Hospital urine test", "Fasting of 12 hours for blooad test", "22-12-2017 6:00 PM");
//        appointment.addAppointment("Clementi Hospital routine test", "Fasting of 12 hours for blooad test", "22-12-2017 6:00 PM");
//        appointment.addAppointment("Clementi Hospital Xray test", "Fasting of 12 hours for blooad test", "22-12-2017 6:00 PM");
//
//        appointments =  appointment.getAppointments();
//        assertTrue(appointments.size() == 4);
//    }
//
//    @Test
//    public void testDeleteAppointment(){
//        Appointment appointment = new Appointment();
//        List<Appointment> appointments =  appointment.getAppointments();
//        assertTrue(appointments.size() == 0);
//
//        appointment.addAppointment("Clementi Hospital blood test", "Fasting of 12 hours for blooad test", "22-12-2017 6:00 PM");
//        appointment.addAppointment("Clementi Hospital urine test", "Fasting of 12 hours for blooad test", "22-12-2017 6:00 PM");
//        appointment.addAppointment("Clementi Hospital routine test", "Fasting of 12 hours for blooad test", "22-12-2017 6:00 PM");
//        appointment.addAppointment("Clementi Hospital Xray test", "Fasting of 12 hours for blooad test", "22-12-2017 6:00 PM");
//
//        appointments =  appointment.getAppointments();
//        assertTrue(appointments.size() == 4);
//
//        appointment.DeleteAppointmentById(1);
//
//        assertTrue(appointments.size() == 3);
//    }
//}
