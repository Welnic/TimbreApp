package com.exaample.timbre.ui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exaample.timbre.R;
import com.exaample.timbre.api.SharedPrefs;
import com.exaample.timbre.api.room.Database;
import com.exaample.timbre.models.Moneda;
import com.exaample.timbre.models.Timbru;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;
import java.util.UUID;

public class MonedeActivity extends AppCompatActivity {

    EditText etNume;
    EditText etAn;
    EditText etValoare;
    Button btAdd;
    Button btLista;
    Button btBack;
    SharedPrefs prefs;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monede_acitivity);

        etNume = findViewById(R.id.etNume);
        etValoare = findViewById(R.id.etValoare);
        etAn = findViewById(R.id.etAn);
        btAdd = findViewById(R.id.btAdd);
        btBack = findViewById(R.id.btnBack);
        btLista = findViewById(R.id.btLista);

        prefs = SharedPrefs.getInstance(this);
        userId = prefs.getString("id");

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final Moneda moneda = new Moneda();
                    moneda.setId(UUID.randomUUID().toString());
                    moneda.setNume(etNume.getText().toString());
                    moneda.setValoare(Integer.parseInt(etValoare.getText().toString()));
                    moneda.setAn(Integer.parseInt(etAn.getText().toString()));

                    final Handler handler = new Handler();
                    (new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // Write a message to the database
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("monede");
                            myRef.child(moneda.id).setValue(moneda);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MonedeActivity.this.getBaseContext(), MonedeListActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }
                    })).start();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Nu se poate adauga moneda!", Toast.LENGTH_LONG).show();
                    Log.d("eroare", e.getMessage());
                }
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MonedeActivity.this.getBaseContext(), MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        btLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonedeActivity.this, MonedeListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
