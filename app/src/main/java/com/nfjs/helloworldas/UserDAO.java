package com.nfjs.helloworldas;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("SELECT EXISTS(SELECT 1 FROM users where name=:name)")
    boolean exists(String name);

    @Insert
    void insertUsers(User... user);

    @Delete
    void deleteUsers(User... user);
}
