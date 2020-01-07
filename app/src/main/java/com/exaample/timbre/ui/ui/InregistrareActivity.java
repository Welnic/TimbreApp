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
import com.exaample.timbre.api.room.Database;
import com.exaample.timbre.api.room.InsertUtilizatorAsync;
import com.exaample.timbre.models.Utilizator;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class InregistrareActivity extends AppCompatActivity {
    EditText etNume, etParola;
    Button btnInregistrare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inregistrare);

        etNume = findViewById(R.id.etNume);
        etParola = findViewById(R.id.etParola);
        btnInregistrare = findViewById(R.id.btnInregistrare);


        btnInregistrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String utilizator = etNume.getText().toString();
                String parola = etParola.getText().toString();
                final Utilizator usr = new Utilizator();
                usr.numeUtilizator = utilizator;
                usr.parola = parola;
                usr.id = UUID.randomUUID().toString();

                if (utilizator.equals("") || parola.equals("")) {
                    Toast.makeText(getBaseContext(), "Date gresite!", Toast.LENGTH_LONG).show();
                } else {
                    final Handler handler = new Handler();
                    (new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Database database = Database.getInstance(getBaseContext());
                            database.getDatabase().utilizatorDAO().insertUtilizator(usr);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(InregistrareActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(getBaseContext(), "Cont creat cu succes", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    })).start();
                }
            }
        });
    }


}