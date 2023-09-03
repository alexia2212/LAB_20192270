package com.example.lab_20192270;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Time;
import java.util.Random;
import java.util.Timer;

public class TeleAhorcado extends AppCompatActivity {

    private String [] words;
    private Random random;
    private  String currentWord;
    private TextView[] charViews;
    private LinearLayout wordLayout;
    private int numCorrect;
    private int numChars;
    private ImageView[] partes;
    private int sizePartes=6;
    private int currentPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_ahorcado);
        ActionBar barra = getSupportActionBar();
        barra.setTitle("TELEAHORCADO");

        words=getResources().getStringArray(R.array.words);
        wordLayout=findViewById(R.id.words);
        random= new Random();
        partes=new ImageView[sizePartes];
        partes[0] = findViewById(R.id.cabeza);
        partes[1] = findViewById(R.id.imagen_torso);
        partes[2] = findViewById(R.id.brazo_izq);
        partes[3] = findViewById(R.id.imageView7);
        partes[4] = findViewById(R.id.pierna_der);
        partes[5] = findViewById(R.id.pierna_izq);

        playJuego();

    }
    private void playJuego(){
        String newPalabra = words[random.nextInt(words.length)];
        while(newPalabra.equals(currentWord))newPalabra=words[random.nextInt(words.length)];
        {
            currentWord = newPalabra;

            charViews= new TextView[currentWord.length()];

            for(int i = 0; i<currentWord.length(); i++){
                charViews[i] = new TextView(this);
                charViews[i].setText(""+currentWord.charAt(i));
                charViews[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                charViews[i].setGravity(Gravity.CENTER);
                charViews[i].setTextColor(Color.WHITE);
                charViews[i].setBackgroundResource(R.drawable.letter_bg);
                wordLayout.addView(charViews[i]);
            }

            numCorrect=0;
            numChars=currentWord.length();
            currentPart=0;
        }

    }

    public void letterPresionar(Button button){
        String letter = (button.getText().toString());
        char letterChar = letter.charAt(0);

        button.setEnabled(false);
        boolean correct = false;
        for (int i = 0 ; i < currentWord.length();i++) {

            if (currentWord.charAt(i) == letterChar) {

                correct = true;
                numCorrect++;
                charViews[i].setTextColor(Color.BLACK);

            }
        }


        if(correct){
            if(numCorrect==numChars){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("GANASTEEE!!");
                    builder.setMessage("Tiempo de Juego: "+ "seg");
                    builder.setPositiveButton("Jugar de nuevo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            TeleAhorcado.this.playJuego();

                        }
                    });

                    builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            TeleAhorcado.this.finish();

                        }
                    });
                    
                    builder.show();
                }
        } else if (currentPart<sizePartes) {
                partes[currentPart].setVisibility(View.VISIBLE);
                currentPart++;
                
        }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("PERDISTE :(");
                builder.setMessage("Tiempo de Juego: "+ "seg");
                builder.setPositiveButton("Jugar de nuevo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TeleAhorcado.this.playJuego();

                    }
                });

                builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TeleAhorcado.this.finish();

                    }
                });

                builder.show();
        }


    }
    public void disableButton(){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_juego, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.icono_estad){
            Intent intent = new Intent(this, Estadisticas.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}