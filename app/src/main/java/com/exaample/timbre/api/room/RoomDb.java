package com.exaample.timbre.api.room;

import com.exaample.timbre.models.Timbru;
import com.exaample.timbre.models.Utilizator;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

@Database(entities = {Utilizator.class, Timbru.class}, version = 1, exportSchema = false)
public abstract class RoomDb extends RoomDatabase {
    public abstract UtilizatorDAO utilizatorDAO();
    public abstract TimbruDAO timbruDAO();


}
