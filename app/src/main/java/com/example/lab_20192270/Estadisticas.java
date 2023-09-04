package com.example.lab_20192270;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Estadisticas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        ActionBar barra = getSupportActionBar();
        barra.setTitle("TELEAHORCADO");
        Intent intent = this.getIntent();
        ArrayList<String> estad = intent.getStringArrayListExtra("estadisticas");
        LinearLayout layout = findViewById(R.id.estad);
        for (int i=0; i<estad.size();i++){
            TextView tv = new TextView(this);
            tv.setTextSize(15);
            tv.setText("Juego "+(i+1)+": "+ estad.get(i));
            layout.addView(tv);
        }
        Button nuevo = findViewById(R.id.botonNuevoJuego);
        nuevo.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, TeleAhorcado.class);
            intent.putExtra("estadisticas", estad);
            startActivity(intent1);
        });

    }
}