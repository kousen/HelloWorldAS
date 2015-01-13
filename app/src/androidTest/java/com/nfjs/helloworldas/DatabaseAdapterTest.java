package com.nfjs.helloworldas;

import android.test.AndroidTestCase;

import java.util.List;

public class DatabaseAdapterTest extends AndroidTestCase {
    private DatabaseAdapter dba;

    @Override
    protected void setUp() throws Exception {
        dba = new DatabaseAdapter(getContext());
        dba.open();
        dba.deleteAllNames();
    }

    public void testInsertAndGetAllNames() {
        dba.insertName("Fred");
        dba.insertName("Barney");
        List<String> names = dba.getAllNames();
        assertTrue(names.contains("Fred"));
        assertTrue(names.contains("Barney"));
    }

    public void testExceptionForEmptyName() {
        try {
            dba.insertName("");
            fail("Should never get here");
        } catch(IllegalArgumentException e) {
            assertEquals("Name must not be empty", e.getMessage());
        }
    }

    public void testDeleteName() {
        int count = dba.getAllNames().size();
        dba.insertName("Fred");
        assertEquals(count + 1, dba.getAllNames().size());

        dba.deleteName("Fred");
        assertEquals(count, dba.getAllNames().size());
    }

    public void testExists() {
        assertFalse(dba.getAllNames().contains("Fred"));

        dba.insertName("Fred");
        assertTrue(dba.getAllNames().contains("Fred"));

        dba.deleteName("Fred");
        assertFalse(dba.getAllNames().contains("Fred"));
    }
}
