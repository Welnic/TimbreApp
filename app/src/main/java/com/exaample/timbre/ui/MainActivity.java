package com.exaample.timbre.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.exaample.timbre.R;

public class MainActivity extends AppCompatActivity {

    Button btIesire;
    Button btAbout;
    Button btFavorite;
    Button btAdauga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btIesire=(findViewById(R.id.btIesire));
        btAbout=findViewById(R.id.btDespre);
        btAdauga=findViewById(R.id.btAdauga);
        btFavorite=findViewById(R.id.btLista);

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
    }

}
