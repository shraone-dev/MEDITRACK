package com.example.maditrack;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.maditrack.MediTracklFolder.Pressure;


/**
 * Created by MaheshIK on 26/3/2017.
 */


public class PressureTest extends TestCase {

    private Pressure pressure1 = new Pressure(1, "15 Dec 2016", 120, 90);
    private Pressure pressure2 = new Pressure(2, "17 Mar 2017", 125, 80);


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public void testPressure() {
        /*Measurement m = new Pressure();
        Pressure pressure1 = (Pressure) m;*/
        assertEquals(1, pressure1.getMeasurementId());
        assertEquals("15 Dec 2016", pressure1.getMeasuredOn());
        assertEquals(120, pressure1.getSystolic());
        assertEquals(90, pressure1.getDiastolic());

        assertEquals(2, pressure2.getMeasurementId());
        assertEquals("17 Mar 2017", pressure2.getMeasuredOn());
        assertEquals(125, pressure2.getSystolic());
        assertEquals(80, pressure2.getDiastolic());

    }

    @Test
    public void testGetSystolic() {
        assertEquals(120, pressure1.getSystolic());
        assertEquals(125, pressure2.getSystolic());
    }

    @Test
    public void testGetDiastolic() {
        assertEquals(90, pressure1.getDiastolic());
        assertEquals(80, pressure2.getDiastolic());
    }

    @Test
    public void testEquals() {
        assertEquals(pressure1, new Pressure(1, "15 Dec 2016", 120, 90));
        assertEquals(pressure2, new Pressure(2, "17 Mar 2017", 125, 80));

        assertFalse(pressure1.equals(pressure2));
        assertFalse(pressure2.equals(pressure1));

        Pressure pressure3 = new Pressure(3, "20 Mar 2017", 125, 85);
        assertFalse(pressure1.equals(pressure3));
        assertFalse(pressure3.equals(pressure1));

        Pressure pressure4 = new Pressure(4, "10 Feb 2017", 130, 90);
        assertFalse(pressure2.equals(pressure4));
        assertFalse(pressure4.equals(pressure2));
    }


}
