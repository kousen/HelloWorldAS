package com.nfjs.helloworldas;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "users")
public class User {
    private static int nextId = 0;

    @PrimaryKey
    private int uid;

    private String name;

    @Ignore
    public User() {
        this(nextId++, "DEFAULT_NAME");
    }

    @Ignore
    public User(String name) {
        this(nextId++, name);
    }

    public User(int uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (uid != user.uid) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
