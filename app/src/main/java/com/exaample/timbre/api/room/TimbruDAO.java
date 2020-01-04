package com.exaample.timbre.api.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.exaample.timbre.models.Timbru;
import com.exaample.timbre.models.Utilizator;

import java.util.List;

@Dao
public interface TimbruDAO {
    @Query("SELECT * FROM Timbru WHERE idUser LIKE :userId")
    List<Timbru> gasesteTimbre(String userId);

    @Query("SELECT * FROM Timbru WHERE idUser LIKE :userId AND serie LIKE :serie")
    List<Timbru> gasesteTimbreBySerie(String userId, String serie);

    @Query("SELECT COUNT(*) FROM Timbru WHERE idUser LIKE :userId")
    int gasesteTimbru(String userId);

    @Insert
    public void insertTimbru(Timbru timbru);

    @Query("DELETE FROM Timbru WHERE id = :timbruId")
    public void deleteTimbru(String timbruId);

    @Update
    public void updateTimbru(Timbru timbru);
}
