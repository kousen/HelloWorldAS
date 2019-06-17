package com.nfjs.helloworldas;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DatabaseAdapterTest {
    private DatabaseAdapter dba;

    @Before
    protected void setUp() throws Exception {
        dba = new DatabaseAdapter(ApplicationProvider.getApplicationContext());
        dba.open();
        dba.deleteAllNames();
    }

    public void testInsertAndGetAllNames() {
        dba.insertName("Fred");
        dba.insertName("Barney");
        List<String> names = dba.getAllNames();
        assertTrue(names.contains("Fred"));
        assertTrue(names.contains("Barney"));
        dba.deleteAllNames();
    }

    public void testDeleteName() {
        int count = dba.getAllNames().size();
        dba.insertName("Fred");
        assertEquals(count + 1, dba.getAllNames().size());

        dba.deleteName("Fred");
        assertEquals(count, dba.getAllNames().size());
    }

    @After
    protected void tearDown() throws Exception {
        dba.close();
    }
}
