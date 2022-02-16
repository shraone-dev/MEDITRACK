package com.example.maditrack.database;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import com.example.maditrack.MediTracklFolder.Category;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.assertEquals;

/**
 * Created by Govardhan on 24/3/2017.
 */
public class CategoryDAOTest {

    CategoryDAO categoryDAO;
    private DBHelper database;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = getTargetContext();

        assertEquals("com.example.medipalapplication", appContext.getPackageName());
    }

    @Before
    public void setUp() throws Exception {
        getTargetContext().deleteDatabase("MedipalDB.db");
        database = new DBHelper(getTargetContext());
        categoryDAO = new CategoryDAO(getTargetContext());
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }


    @Test
    public void categoryUpdate() throws Exception {

        ArrayList<Category> categories = new ArrayList<Category>();
        // categoryDAO.categoryUpdate(new Category(1,"SUP","SUPPLEMENT","DESCRIPTION"));
        //Cursor categoryCursor = categoryDAO.getAllCategory();
    }
}