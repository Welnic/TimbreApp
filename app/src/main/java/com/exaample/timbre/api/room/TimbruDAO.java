package com.exaample.timbre.api.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.exaample.timbre.models.Timbru;
import com.exaample.timbre.models.Utilizator;

import java.util.List;

@Dao
public interface TimbruDAO {
    @Query("SELECT * FROM Timbru WHERE idUser LIKE :userId")
    List<Timbru> gasesteTimbre(String userId);

    @Query("SELECT COUNT(*) FROM Timbru WHERE id LIKE :timbruId")
    int gasesteTimbru(String timbruId);

    @Insert
    public void insertTimbru(Timbru timbru);

    @Query("DELETE FROM Timbru WHERE id = :timbruId")
    public void deleteTimbru(String timbruId);

}
