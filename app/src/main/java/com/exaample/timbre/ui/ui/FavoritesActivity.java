package com.exaample.timbre.ui.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.exaample.timbre.R;
import com.exaample.timbre.api.Api;
import com.exaample.timbre.api.SharedPrefs;
import com.exaample.timbre.api.room.Database;
import com.exaample.timbre.models.Timbru;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FavoritesActivity extends AppCompatActivity {

    private List<Timbru> lista;
    private ListView listView;
    private TimbreAdaptor timbreAdaptor;
    private Button btnBack;
    private Button btnFilter;
    private Button btnSaveFile;
    private EditText etFiltru;
    private Timbru returnedResult;
    private FloatingActionButton fab;
    private FloatingActionButton fabRefresh;
    private String userId;
    private SharedPrefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        lista = new ArrayList<>();
        etFiltru = findViewById(R.id.etFilter);
        btnBack = findViewById(R.id.btnBack);
        btnSaveFile = findViewById(R.id.btnSaveFile);
        btnFilter = findViewById(R.id.btnFilter);
        listView = findViewById(R.id.lvTimbre);
        fab = findViewById(R.id.fab);

        returnedResult = getIntent().getParcelableExtra("timbru");

        timbreAdaptor = new TimbreAdaptor(lista, this);
        listView.setAdapter(timbreAdaptor);
        listView.setLongClickable(true);

        prefs = SharedPrefs.getInstance(this);
        userId = prefs.getString("id");

        connect();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });
        btnSaveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                for (Timbru t : lista) {
                    text += t.toString() + "\n";
                }
                writeToFile(text, getBaseContext());
                Toast.makeText(getBaseContext(), "Fisier exportat cu succes!", Toast.LENGTH_LONG).show();
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
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filtru = etFiltru.getText().toString();
                if (filtru.equals("")) {
                    getTimbre("");
                } else {
                    getTimbre("%" + filtru + "%");
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("long clicked", "pos: " + i);
                Bundle bundle = new Bundle();
                bundle.putString("id", lista.get(i).getId());

                DialogFragment newFragment = new DeleteDialogFragment();
                newFragment.setArguments(bundle);
                newFragment.show(getSupportFragmentManager(), "delete");
                return true;
            }
        });
    }

    private void connect() {
        @SuppressLint("StaticFieldLeak") Api api = new Api() {
            @Override
            protected void onPostExecute(String json) {
                final List<Timbru> listaJson = parseResponse(json);
                final Handler handler = new Handler();
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Database database = Database.getInstance(getBaseContext());
                        if (returnedResult != null) {
//                            lista.add(returnedResult);
                        }
                        if (database.getDatabase().timbruDAO().gasesteTimbru(userId) == 0)
                            for (Timbru t : listaJson) {
                                t.setId(UUID.randomUUID().toString());
                                t.setIdUser(userId);
                                database.getDatabase().timbruDAO().insertTimbru(t);
                            }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                getTimbre("");
                            }
                        });
                    }
                })).start();
            }
        };
        api.execute();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 113) {
            if (resultCode == RESULT_OK) {
                returnedResult = data.getParcelableExtra("timbru");
            }
        }
    }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("timbre.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void getTimbre(final String filtruSerie) {
        final Handler handler = new Handler();
        (new Thread(new Runnable() {
            @Override
            public void run() {
                Database database = Database.getInstance(getBaseContext());
                lista.clear();
                if (filtruSerie.equals(""))
                    lista.addAll(database.getDatabase().timbruDAO().gasesteTimbre(userId));
                else {
                    lista.addAll(database.getDatabase().timbruDAO().gasesteTimbreBySerie(userId, filtruSerie));
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        timbreAdaptor.updateLista(lista);
                    }
                });
            }
        })).start();
    }
}
