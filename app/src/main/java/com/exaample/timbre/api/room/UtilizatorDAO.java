package com.exaample.timbre.api.room;
import com.exaample.timbre.models.Timbru;
import com.exaample.timbre.models.Utilizator;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UtilizatorDAO {


    @Query("SELECT * FROM Utilizator WHERE nume LIKE :nume AND parola like :parola")
    Utilizator gasesteUtilizatorul(String nume, String parola);

    @Insert
    public void insertUtilizator(Utilizator user);
}

