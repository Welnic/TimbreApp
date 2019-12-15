package com.exaample.timbre.ui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.exaample.timbre.R;
import com.exaample.timbre.models.Timbru;

public class MainActivity extends AppCompatActivity {

    Button btIesire;
    Button btAbout;
    Button btFavorite;
    Button btAdauga;
    TextView etUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = (findViewById(R.id.etUsername));
        btIesire = (findViewById(R.id.btIesire));
        btAbout = findViewById(R.id.btDespre);
        btAdauga = findViewById(R.id.btAdauga);
        btFavorite = findViewById(R.id.btLista);

        String userConectat = getIntent().getStringExtra("username");
        if ( userConectat!= null) {
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
        btAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this.getBaseContext(), AddActivity.class);
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