package com.exaample.timbre.ui.ui;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exaample.timbre.R;
import com.exaample.timbre.api.SharedPrefs;
import com.exaample.timbre.api.room.Database;
import com.exaample.timbre.models.Utilizator;
import com.exaample.timbre.ui.ui.login.LoggedInUserView;
import com.exaample.timbre.ui.ui.login.LoginViewModel;
import com.exaample.timbre.ui.ui.login.LoginViewModelFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private Utilizator existsUser = null;
    private SharedPrefs prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button guestButton = findViewById(R.id.guestLogin);
        final Button utilizatorNou = findViewById(R.id.btnUtilizatorNou);

        prefs = SharedPrefs.getInstance(this);

        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                login(new Utilizator(username, password));
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameEditText.getText().toString();
                final String password = passwordEditText.getText().toString();
                final Handler handler = new Handler();
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Database database = Database.getInstance(getBaseContext());
                        existsUser = database.getDatabase().utilizatorDAO().gasesteUtilizatorul(username, password, "");

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (existsUser == null)
                                    Toast.makeText(getBaseContext(), "Logare esuata", Toast.LENGTH_LONG).show();
                                else {
                                    Toast.makeText(getBaseContext(), "Logare cu succes", Toast.LENGTH_LONG).show();
                                    login(existsUser);
                                }
                            }
                        });
                    }
                })).start();
            }
        });
        utilizatorNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, InregistrareActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login(Utilizator u) {
        saveToJSON(u.numeUtilizator, u.parola);
        prefs.saveString("id", u.id);
        prefs.saveString("username", u.numeUtilizator);
        prefs.saveString("password", u.parola);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("username", u.numeUtilizator);
        startActivity(intent);
    }

    private void saveToJSON(String username, String password) {
        try {
            File file = new File(this.getApplicationContext().getFilesDir(), "users.json");
            boolean gasit = false;
            JSONArray users = new JSONArray();
            if (file.exists()) {

                StringBuilder text = new StringBuilder();
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    text.append(line);
                }
                br.close();

                JSONObject jsonObjectRead = new JSONObject(text.toString());
                users = jsonObjectRead.getJSONArray("users");
                for (int i = 0; i < users.length(); i++) {
                    JSONObject jsonUser = users.getJSONObject(i);
                    if (jsonUser.get("username").toString().equals(username) && jsonUser.get("password").toString().equals(password)) {
                        gasit = true;
                    }
                }
            }
            if (gasit == false) {
                JSONObject jsonRoot = new JSONObject();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", username);
                jsonObject.put("password", password);

                users.put(users.length(), jsonObject);

                jsonRoot.put("users", (Object) users);
                Writer output = null;
                output = new BufferedWriter(new FileWriter(file));
                output.write(jsonRoot.toString());
                output.close();
            }

            Toast toast = Toast.makeText(getApplicationContext(), "User saved and updated", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 10);
            toast.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}