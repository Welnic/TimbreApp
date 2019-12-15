package com.exaample.timbre.api.room;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.exaample.timbre.models.Utilizator;
public class InsertUtilizatorAsync extends AsyncTask<Utilizator, Void, Void> {
    //1. ce primeste ca param
    //3. ce returneaza
    //2. rezutatul pe parcurs
    private Context context;

    public InsertUtilizatorAsync(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Utilizator... utilizatori) {
        Database database = Database.getInstance(context);
        try {
            database.getDatabase().utilizatorDAO().insertUtilizator(utilizatori[0]);
        }
        catch (Exception e){
            Log.e("eroare db",e.getMessage());
        }
        return null;
    }
}
