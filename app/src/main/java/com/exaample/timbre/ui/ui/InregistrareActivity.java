package com.exaample.timbre.ui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.exaample.timbre.R;
import com.exaample.timbre.api.room.InsertUtilizatorAsync;
import com.exaample.timbre.models.Utilizator;

import java.util.Random;

public class InregistrareActivity extends AppCompatActivity {
    EditText etNume, etParola;
    Button btnInregistrare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inregistrare);

        etNume= findViewById(R.id.etNume);
        etParola = findViewById(R.id.etParola);
        btnInregistrare = findViewById(R.id.btnInregistrare);

        btnInregistrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String utilizator = etNume.getText().toString();
                String parola = etParola.getText().toString();
                Utilizator usr = new Utilizator();
                usr.numeUtilizator = utilizator;
                usr.parola = parola;
                usr.id = new Random().nextInt();
                new InsertUtilizatorAsync(getBaseContext()).execute(usr);
            }
        });
    }


}