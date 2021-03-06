package com.exaample.timbre.ui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.exaample.timbre.R;
import com.exaample.timbre.api.SharedPrefs;

public class MainActivity extends AppCompatActivity {

    Button btIesire;
    Button btAbout;
    Button btFavorite;
    Button btTimbre;
    Button btMonede;
    Button btHarta;
    TextView etUser;
    private SharedPrefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = (findViewById(R.id.etUsername));
        btIesire = (findViewById(R.id.btIesire));
        btAbout = findViewById(R.id.btDespre);
        btTimbre = findViewById(R.id.btTimbre);
        btMonede = findViewById(R.id.btMonede);
        btFavorite = findViewById(R.id.btLista);
        btHarta = findViewById(R.id.btHarta);
        prefs = SharedPrefs.getInstance(this);

        String username = prefs.getString("username");
        if (!username.equals("")) {
            etUser.setText(username);
            etUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), ProfilActivity.class);
                    startActivity(intent);
                }
            });
        }

        String userConectat = getIntent().getStringExtra("username");
        if (userConectat != null) {
            etUser.setText(userConectat);
        }

        btIesire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finishAffinity();
            }
        });

        btFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this.getBaseContext(), FavoritesActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btTimbre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this.getBaseContext(), AddActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btHarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this.getBaseContext(), MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btMonede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this.getBaseContext(), MonedeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this.getBaseContext(), AboutActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}