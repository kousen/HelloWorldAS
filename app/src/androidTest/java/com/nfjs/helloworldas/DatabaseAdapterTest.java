package com.nfjs.helloworldas;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class DatabaseAdapterTest {
    private DatabaseAdapter dba;

    @Before
    public void setUp() throws Exception {
        dba = new DatabaseAdapter(InstrumentationRegistry.getTargetContext());
        dba.open();
        dba.deleteAllNames();
    }

    @Test
    public void testInsertAndGetAllNames() {
        dba.insertName("Fred");
        dba.insertName("Barney");
        List<String> names = dba.getAllNames();
        assertTrue(names.contains("Fred"));
        assertTrue(names.contains("Barney"));
    }

    @Test
    public void testExceptionForEmptyName() {
        try {
            dba.insertName("");
            fail("Should never get here");
        } catch(IllegalArgumentException e) {
            assertEquals("Name must not be empty", e.getMessage());
        }
    }

    @Test
    public void testDeleteName() {
        int count = dba.getAllNames().size();
        dba.insertName("Fred");
        assertEquals(count + 1, dba.getAllNames().size());

        dba.deleteName("Fred");
        assertEquals(count, dba.getAllNames().size());
    }

    @Test
    public void testExists() {
        assertFalse(dba.getAllNames().contains("Fred"));

        dba.insertName("Fred");
        assertTrue(dba.getAllNames().contains("Fred"));

        dba.deleteName("Fred");
        assertFalse(dba.getAllNames().contains("Fred"));
    }
}
