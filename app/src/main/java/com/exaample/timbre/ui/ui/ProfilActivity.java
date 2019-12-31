package com.exaample.timbre.ui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exaample.timbre.R;
import com.exaample.timbre.api.SharedPrefs;
import com.exaample.timbre.api.room.Database;
import com.exaample.timbre.models.Utilizator;

import java.util.UUID;

public class ProfilActivity extends AppCompatActivity {

    EditText etNume, etParola;
    Button btnSalvare, btnSterge;
    private SharedPrefs prefs;
    private String id;
    private Utilizator user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        init();

        btnSalvare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String utilizator = etNume.getText().toString();
                String parola = etParola.getText().toString();
                final Utilizator usr = new Utilizator();
                usr.numeUtilizator = utilizator;
                usr.parola = parola;
                usr.id = id;
                final Handler handler = new Handler();
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Database database = Database.getInstance(getBaseContext());
                        database.getDatabase().utilizatorDAO().updateUtilizator(usr.numeUtilizator, usr.parola, usr.id);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(), "Cont modificat cu succes", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                })).start();
            }
        });

        btnSterge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Handler handler = new Handler();
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Database database = Database.getInstance(getBaseContext());
                        database.getDatabase().utilizatorDAO().deleteUtilizator(id);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(), "Cont sters cu succes", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                })).start();
            }
        });
    }

    private void init() {
        etNume = findViewById(R.id.etNume);
        etParola = findViewById(R.id.etParola);
        btnSalvare = findViewById(R.id.btnSalvare);
        btnSterge = findViewById(R.id.btnSterge);

        prefs = SharedPrefs.getInstance(this);

        id = prefs.getString("id");
        final Handler handler = new Handler();
        (new Thread(new Runnable() {
            @Override
            public void run() {
                Database database = Database.getInstance(getBaseContext());
                user = database.getDatabase().utilizatorDAO().gasesteUtilizatorul("", "", id);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        etNume.setText(user.numeUtilizator);
                        etParola.setText(user.parola);
                    }
                });
            }
        })).start();
    }
}
