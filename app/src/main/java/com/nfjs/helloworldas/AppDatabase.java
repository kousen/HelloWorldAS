package com.nfjs.helloworldas;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = { User.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO getUserDao();
}
