package com.example.maditrack;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.maditrack.MediTracklFolder.Temperature;


/**
 * Created by Mahesh Inna Kedage on 26/3/2017.
 */

public class TemperatureTest extends TestCase {
    private Temperature temperature1 = new Temperature(1, "15 Dec 2016", 97.0f);
    private Temperature temperature2 = new Temperature(2, "17 Mar 2017", 98.0f);


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public void testTemperature() {
        /*Measurement m = new Temperature();
        Temperature temperature1 = (Temperature) m;*/
        assertEquals(1, temperature1.getMeasurementId());
        assertEquals("15 Dec 2016", temperature1.getMeasuredOn());
        assertEquals(97.0f, temperature1.getTemperature());

        assertEquals(2, temperature2.getMeasurementId());
        assertEquals("17 Mar 2017", temperature2.getMeasuredOn());
        assertEquals(98.0f, temperature2.getTemperature());

    }


    @Test
    public void testGetTemperature() {
        assertEquals(97.0f, temperature1.getTemperature());
        assertEquals(98.0f, temperature2.getTemperature());
    }

    @Test
    public void testEquals() {
        assertEquals(temperature1, new Temperature(1, "15 Dec 2016", 97.0f));
        assertEquals(temperature2, new Temperature(2, "17 Mar 2017", 98.0f));

        assertFalse(temperature1.equals(temperature2));
        assertFalse(temperature2.equals(temperature1));

        Temperature temperature3 = new Temperature(3, "20 Mar 2017", 99.0f);
        assertFalse(temperature1.equals(temperature3));
        assertFalse(temperature3.equals(temperature1));

        Temperature temperature4 = new Temperature(4, "10 Feb 2017", 100.0f);
        assertFalse(temperature2.equals(temperature4));
        assertFalse(temperature4.equals(temperature2));
    }
}
