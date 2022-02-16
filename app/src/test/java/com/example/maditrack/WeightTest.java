package com.example.maditrack;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.maditrack.MediTracklFolder.Weight;


/**
 * Created by Mahesh Inna Kedage on 26/3/2017.
 */

public class WeightTest extends TestCase {
    private Weight weight1 = new Weight(1, "15 Dec 2016", 72);
    private Weight weight2 = new Weight(2, "17 Mar 2017", 75);


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public void testWeight() {
        /*Measurement m = new Weight();
        Weight weight1 = (Weight) m;*/
        assertEquals(1, weight1.getMeasurementId());
        assertEquals("15 Dec 2016", weight1.getMeasuredOn());
        assertEquals(72, weight1.getWeight());

        assertEquals(2, weight2.getMeasurementId());
        assertEquals("17 Mar 2017", weight2.getMeasuredOn());

        assertEquals(75, weight2.getWeight());

    }

    @Test
    public void testGetWeight() {
        assertEquals(72, weight1.getWeight());
        assertEquals(75, weight2.getWeight());
    }

    @Test
    public void testEquals() {
        assertEquals(weight1, new Weight(1, "15 Dec 2016", 72));
        assertEquals(weight2, new Weight(2, "17 Mar 2017", 75));

        assertFalse(weight1.equals(weight2));
        assertFalse(weight2.equals(weight1));

        Weight weight3 = new Weight(3, "20 Mar 2017", 76);
        assertFalse(weight1.equals(weight3));
        assertFalse(weight3.equals(weight1));

        Weight weight4 = new Weight(4, "10 Feb 2017", 78);
        assertFalse(weight2.equals(weight4));
        assertFalse(weight4.equals(weight2));
    }
}
