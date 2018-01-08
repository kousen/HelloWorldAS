package com.nfjs.helloworldas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LIST_OF_USERS.db";
    private static final int VERSION = 3;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE LIST_OF_USERS( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, birthMonth TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS LIST_OF_USERS;");
        onCreate(db);
    }
}
