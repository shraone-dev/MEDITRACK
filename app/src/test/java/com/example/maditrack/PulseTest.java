package com.example.maditrack;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.maditrack.MediTracklFolder.Pulse;


/**
 * Created by Mahesh Inna Kedage on 26/3/2017.
 */

public class PulseTest extends TestCase {
    private Pulse pulse1 = new Pulse(1, "15 Dec 2016", 90);
    private Pulse pulse2 = new Pulse(2, "17 Mar 2017", 80);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public void testPulse() {
        /*Measurement m = new Pulse();
        Pulse pulse1 = (Pulse) m;*/
        assertEquals(1, pulse1.getMeasurementId());
        assertEquals("15 Dec 2016", pulse1.getMeasuredOn());
        assertEquals(90, pulse1.getPulse());

        assertEquals(2, pulse2.getMeasurementId());
        assertEquals("17 Mar 2017", pulse2.getMeasuredOn());
        assertEquals(80, pulse2.getPulse());

    }

    @Test
    public void testGetPulse() {
        assertEquals(90, pulse1.getPulse());
        assertEquals(80, pulse2.getPulse());
    }

    @Test
    public void testEquals() {
        assertEquals(pulse1, new Pulse(1, "15 Dec 2016", 90));
        assertEquals(pulse2, new Pulse(2, "17 Mar 2017", 80));

        assertFalse(pulse1.equals(pulse2));
        assertFalse(pulse2.equals(pulse1));

        Pulse pulse3 = new Pulse(3, "20 Mar 2017", 85);
        assertFalse(pulse1.equals(pulse3));
        assertFalse(pulse3.equals(pulse1));

        Pulse pulse4 = new Pulse(4, "10 Feb 2017", 90);
        assertFalse(pulse2.equals(pulse4));
        assertFalse(pulse4.equals(pulse2));
    }
}
