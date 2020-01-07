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
import com.exaample.timbre.models.Timbru;

import java.util.Random;
import java.util.UUID;

public class AddActivity extends AppCompatActivity {

    EditText etSerie;
    EditText etAn;
    EditText etMarime;
    EditText etTematica;
    Button btAdd;
    Button btBack;
    SharedPrefs prefs;
    String userId;

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

        prefs = SharedPrefs.getInstance(this);
        userId = prefs.getString("id");

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    final Timbru timbru = new Timbru();
                    timbru.setId(UUID.randomUUID().toString());
                    timbru.setTematica(etTematica.getText().toString());
                    timbru.setSerie(etSerie.getText().toString());
                    timbru.setAn(Integer.parseInt(etAn.getText().toString()));
                    timbru.setMarime(etMarime.getText().toString());
                    timbru.setNou(1);

                    final Handler handler = new Handler();
                    (new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Database database = Database.getInstance(getBaseContext());
                            timbru.setIdUser(userId);
                            database.getDatabase().timbruDAO().insertTimbru(timbru);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(AddActivity.this.getBaseContext(), FavoritesActivity.class);
                                    intent.putExtra("timbru", timbru);
                                    setResult(RESULT_OK, intent);
                                    startActivityForResult(intent, 123);
                                    finish();
                                }
                            });
                        }
                    })).start();
                }catch (Exception e){
                    Toast.makeText(getBaseContext(),"Nu se poate adauga timbrul!", Toast.LENGTH_LONG).show();
                    Log.d("eroare",e.getMessage());
                }
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
