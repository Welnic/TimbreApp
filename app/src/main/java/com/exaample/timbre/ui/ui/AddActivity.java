package com.exaample.timbre.ui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.exaample.timbre.R;
import com.exaample.timbre.models.Timbru;

import java.util.Random;

public class AddActivity extends AppCompatActivity {

    EditText etSerie;
    EditText etAn;
    EditText etMarime;
    EditText etTematica;
    Button btAdd;
    Button btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etSerie = findViewById(R.id.etSerie);
        etTematica = findViewById(R.id.etTematica);
        etAn = findViewById(R.id.etAn);
        etMarime = findViewById(R.id.etMarime);
        btAdd = findViewById(R.id.btAdd);
        btBack = findViewById(R.id.btnBack);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timbru timbru = new Timbru();
                timbru.setId(new Random().nextInt());
                timbru.setTematica(etTematica.getText().toString());
                timbru.setSerie(etSerie.getText().toString());
                timbru.setAn(Integer.parseInt(etAn.getText().toString()));
                timbru.setMarime(etMarime.getText().toString());
                timbru.setNou(1);
                Intent intent = new Intent(AddActivity.this.getBaseContext(), FavoritesActivity.class);
                intent.putExtra("timbru", timbru);
                setResult(RESULT_OK, intent);
                startActivityForResult(intent,123);
                finish();
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(AddActivity.this.getBaseContext(), MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });


    }

}
