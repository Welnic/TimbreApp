package com.exaample.timbre.api.room;


import android.content.Context;
import android.provider.ContactsContract;

import androidx.room.Room;
public class Database {

    private static Database instance;
    private RoomDb database;

    public Database(Context context) {
        this.database = Room.databaseBuilder(context, RoomDb.class, "timbre.db").build();

    }

    public static Database getInstance(Context context) {
        if(instance == null)
        {
            instance = new Database(context);
        }
        return instance;
    }

    public RoomDb getDatabase() {
        return database;
    }
}
