package com.exaample.timbre.ui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.exaample.timbre.R;
import com.exaample.timbre.api.Api;
import com.exaample.timbre.models.Timbru;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private List<Timbru> lista;
    private ListView listView;
    private TimbreAdaptor timbreAdaptor;
    private Button btnBack;
    private Timbru returnedResult;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        lista = new ArrayList<>();
        btnBack = findViewById(R.id.btnBack);
        listView = findViewById(R.id.lvTimbre);
        fab = findViewById(R.id.fab);

        returnedResult = getIntent().getParcelableExtra("timbru");

        timbreAdaptor = new TimbreAdaptor(lista, this);
        listView.setAdapter(timbreAdaptor);

        connect();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavoritesActivity.this.getBaseContext(), AddActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void connect() {
        Api api = new Api() {
            @Override
            protected void onPostExecute(String json) {
                lista = parseResponse(json);
                if (returnedResult != null)
                    lista.add(returnedResult);
                timbreAdaptor.updateLista(lista);
            }
        };
        api.execute();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                returnedResult = data.getParcelableExtra("timbru");
                // OR
                // String returnedResult = data.getDataString();
            }
        }
    }

}
