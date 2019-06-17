package com.nfjs.helloworldas;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = { User.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO getUserDao();
}
