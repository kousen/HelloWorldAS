package com.nfjs.helloworldas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context) {
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open() {
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private Cursor getAllEntries() {
        String[] columns = new String[1];
        columns[0] = "name";
        return database.query("names", columns, null, null, null, null, null);
    }

    public List<String> getAllNames() {
        ArrayList<String> names = new ArrayList<>();
        Cursor cursor = getAllEntries();
        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return names;
    }

    public boolean exists(String name) {
        Cursor cursor = database.rawQuery(
                "select name from names where name=?",
                new String[]{ name });
        return cursor.getCount() >= 1;
    }

    public long insertName(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        ContentValues values = new ContentValues();
        values.put("name", name);
        return database.insert("names", null, values);
    }

    public int deleteName(String name) {
        String whereClause = "name = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = name;
        return database.delete("names", whereClause, whereArgs);
    }

    public int deleteAllNames() {
        return database.delete("names", null, null);
    }

    public int updateName(String name) {
        String whereClause = "name = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = name;
        ContentValues values = new ContentValues();
        values.put("name", name);
        return database.update("names", values, whereClause, whereArgs);
    }
}
