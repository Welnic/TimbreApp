package com.exaample.timbre.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.exaample.timbre.R;

public class AboutActivity extends AppCompatActivity {

    TextView tvAbout;
    Button btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        tvAbout = findViewById(R.id.tvAbout);
        tvAbout.setText("Anul lansarii: 2019. \n" +
                "Bucuresti, Romsnia. \n Autor: Melnic Catalina" +
                "Versiunea 1.0");

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

    }
}
