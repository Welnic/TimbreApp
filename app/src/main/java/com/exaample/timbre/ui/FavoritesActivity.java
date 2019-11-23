package com.exaample.timbre.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.exaample.timbre.R;
import com.exaample.timbre.api.Api;
import com.exaample.timbre.models.Timbru;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private List<Timbru> lista;
    private ListView listView;
    private TimbreAdaptor timbreAdaptor;
    public Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        lista = new ArrayList<>();
        listView = findViewById(R.id.lvTimbre);
        timbreAdaptor = new TimbreAdaptor( lista, this);
        listView.setAdapter(timbreAdaptor);
        btnBack=findViewById(R.id.btnBack);
        connect();



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
    private void connect(){
        Api api = new Api(){
            @Override
            protected void onPostExecute (String json) {
                lista = parseResponse(json);
                timbreAdaptor.updateLista(lista);
            }
        };
        api.execute();
    }


}
