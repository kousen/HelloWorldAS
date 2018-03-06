package com.nfjs.helloworldas;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class UserRoomTest {
    private UserDAO dao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        dao = db.getUserDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testInsertAndGetAllNames() {
        User fred = new User("Fred");
        User barney = new User("Barney");
        dao.insertUsers(fred, barney);
        List<User> users = dao.getAllUsers();
        assertTrue(users.contains(fred));
        assertTrue(users.contains(barney));
    }

    @Test
    public void testDeleteUser() {
        User fred = new User("Fred");

        int count = dao.getAllUsers().size();
        dao.insertUsers(fred);
        assertEquals(count + 1, dao.getAllUsers().size());

        dao.deleteUsers(fred);
        assertEquals(count, dao.getAllUsers().size());
    }

    @Test
    public void testExists() {
        User fred = new User("Fred");

        assertFalse(dao.getAllUsers().contains(fred));

        dao.insertUsers(fred);
        assertTrue(dao.getAllUsers().contains(fred));

        dao.deleteUsers(fred);
        assertFalse(dao.getAllUsers().contains(fred));
    }

}
