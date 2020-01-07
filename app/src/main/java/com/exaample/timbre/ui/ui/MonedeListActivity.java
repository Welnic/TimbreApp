package com.exaample.timbre.ui.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.exaample.timbre.R;
import com.exaample.timbre.api.Api;
import com.exaample.timbre.api.SharedPrefs;
import com.exaample.timbre.api.room.Database;
import com.exaample.timbre.models.Moneda;
import com.exaample.timbre.models.Timbru;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MonedeListActivity extends AppCompatActivity {

    private List<Moneda> lista;
    private ListView listView;
    private MonedeAdaptor monedeAdaptor;
    private Button btnBack;
    //    private Button btnFilter;
//    private Button btnSaveFile;
    private EditText etFiltru;
    //    private Moneda returnedResult;
    private FloatingActionButton fab;
    private String userId;
    private SharedPrefs prefs;

    public interface OnDataReceiveCallback {
        void onDataReceived();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monede_list);
        lista = new ArrayList<>();
        etFiltru = findViewById(R.id.etFilter);
        btnBack = findViewById(R.id.btnBack);
//        btnSaveFile = findViewById(R.id.btnSaveFile);
//        btnFilter = findViewById(R.id.btnFilter);
        listView = findViewById(R.id.lvTimbre);
        fab = findViewById(R.id.fab);
//        fabRefresh = findViewById(R.id.fabRefresh);

//        returnedResult = getIntent().getParcelableExtra("timbru");

        monedeAdaptor = new MonedeAdaptor(lista, this);
        listView.setAdapter(monedeAdaptor);
        listView.setLongClickable(true);

        prefs = SharedPrefs.getInstance(this);
        userId = prefs.getString("id");

        getMonede(new OnDataReceiveCallback() {
            public void onDataReceived() {
                monedeAdaptor.updateLista(lista);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });
//        btnSaveFile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String text = "";
//                for (Moneda m : lista) {
//                    text += m.toString() + "\n";
//                }
//                writeToFile(text, getBaseContext());
//            }
//        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonedeListActivity.this.getBaseContext(), AddActivity.class);
                startActivity(intent);
                finish();
            }
        });
//        btnFilter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String filtru = etFiltru.getText().toString();
//
//            }
//        });
    }

    private void getMonede(final OnDataReceiveCallback callback) {
        DatabaseReference categoryReference = FirebaseDatabase.getInstance().getReference("monede");
        lista.clear();
        ValueEventListener categoryListener = new ValueEventListener() {
            int i = 0;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    String key = dsp.getKey();
                    Moneda m = dsp.getValue(Moneda.class);
                    lista.add(m);
                }
                callback.onDataReceived();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadMonede:onCancelled", databaseError.toException());
            }
        };
        categoryReference.addListenerForSingleValueEvent(categoryListener);

    }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("monede.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
