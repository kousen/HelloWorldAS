package com.nfjs.helloworldas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        String[] columns = new String[2];
        columns[0] = "name";
        columns[1] = "birthMonth";
        return database.query("LIST_OF_USERS", columns, null, null, null, null, "birthMonth");
    }

    public ArrayList<User> getAllNames() {
        ArrayList<User> users = new ArrayList<User>() {
        };
        Cursor cursor = getAllEntries();
        if (cursor.moveToFirst()) {
            do {
                users.add(new User(cursor.getString(0),cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }

    public boolean exists(String name) {
        Cursor cursor = database.rawQuery(
                "select name from LIST_OF_USERS where name=?",
                new String[]{ name });
        boolean result = cursor.getCount() >= 1;
        cursor.close();
        return result;
    }

    // random.nextInt(max - min + 1) + min
    public long insertName(String name) {
        int myRandomInt = new Random().nextInt(12 );
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("birthMonth", ReturnMonth.findMonth(myRandomInt));
        //values.put("birthMonth", myRandomInt);
        return database.insert("LIST_OF_USERS", null, values);
    }

    public int deleteName(String name) {
        String whereClause = "name = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = name;
        return database.delete("LIST_OF_USERS", whereClause, whereArgs);
    }

    public int deleteAllNames() {
        return database.delete("LIST_OF_USERS", null, null);
    }

    public int updateName(String name) {
        String whereClause = "name = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = name;
        ContentValues values = new ContentValues();
        values.put("name", name);
        return database.update("LIST_OF_USERS", values, whereClause, whereArgs);
    }
}
