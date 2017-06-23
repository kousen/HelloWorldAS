package com.nfjs.helloworldas;

import android.test.AndroidTestCase;

import java.util.List;

public class DatabaseAdapterTest extends AndroidTestCase {
    private DatabaseAdapter dba;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
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
        dba.deleteAllNames();
    }

    public void testDeleteName() {
        int count = dba.getAllNames().size();
        dba.insertName("Fred");
        assertEquals(count + 1, dba.getAllNames().size());

        dba.deleteName("Fred");
        assertEquals(count, dba.getAllNames().size());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        dba.close();
    }
}
