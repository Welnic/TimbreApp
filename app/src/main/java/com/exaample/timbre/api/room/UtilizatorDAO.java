package com.exaample.timbre.api.room;
import com.exaample.timbre.models.Timbru;
import com.exaample.timbre.models.Utilizator;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UtilizatorDAO {


    @Query("SELECT * FROM Utilizator WHERE nume LIKE :nume AND parola like :parola OR id like :id")
    Utilizator gasesteUtilizatorul(String nume, String parola, String id);

    @Insert
    public void insertUtilizator(Utilizator user);

    @Query("DELETE FROM Utilizator WHERE id = :utilizatorId")
    public void deleteUtilizator(String utilizatorId);

    @Query("UPDATE Utilizator set nume=:username, parola=:password WHERE id = :utilizatorId")
    public void updateUtilizator(String username, String password, String utilizatorId);
}

