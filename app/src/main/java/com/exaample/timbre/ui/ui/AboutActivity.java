package com.exaample.timbre.ui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        btBack = findViewById(R.id.btnBack);

        tvAbout.setText("Anul lansării: 2019. \n" +
                "București, România. \nAutor: Melnic Cătălina" +
                "\nVersiunea 1.0");

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(AboutActivity.this.getBaseContext(), MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

    }
}
