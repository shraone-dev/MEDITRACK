package com.example.maditrack;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.maditrack.MediTracklFolder.Measurement;


/**
 * Created by MaheshIK on 26/3/2017.
 */

public class MeasurementTest extends TestCase {

    private Measurement measure1;
    private Measurement measure2;

    @Before
    public void setUp() throws Exception {
        measure1 = new Measurement(1, "15 Mar 2017");
        measure2 = new Measurement(2, "12 Mar 2017");
    }

    @After
    public void tearDown() throws Exception {
        measure1 = null;
        measure2 = null;
    }

    @Test
    public void testMeasurement() {
        assertEquals(1, measure1.getMeasurementId());
        assertEquals("15 Mar 2017", measure1.getMeasuredOn());

        assertEquals(2, measure2.getMeasurementId());
        assertEquals("12 Mar 2017", measure2.getMeasuredOn());
    }

    @Test
    public void testGetMeasuredOn() {
        assertEquals("15 Mar 2017", measure1.getMeasuredOn());
        assertEquals("12 Mar 2017", measure2.getMeasuredOn());
    }

    @Test
    public void testGetMeasuredId() {
        assertEquals(1, measure1.getMeasurementId());
        assertEquals(2, measure2.getMeasurementId());
    }

    @Test
    public void testEquals() {

        assertEquals(measure1, new Measurement(1, "15 Mar 2017"));
        assertEquals(measure2, new Measurement(2, "12 Mar 2017"));


        assertFalse(measure1.equals(measure2));
        assertFalse(measure2.equals(measure1));

        Measurement measure3 = new Measurement(3, "10 Jan 2017");
        assertFalse(measure1.equals(measure3));
        assertFalse(measure3.equals(measure1));

        Measurement measure4 = new Measurement(4, "20 Feb 2017");
        assertFalse(measure2.equals(measure4));
        assertFalse(measure4.equals(measure2));

    }

}
