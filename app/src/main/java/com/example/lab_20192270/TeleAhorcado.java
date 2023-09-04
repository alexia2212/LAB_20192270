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
import java.time.Instant;
import java.util.ArrayList;
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
    private Instant inicio;
    private ArrayList<String> estadisticas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_ahorcado);
        ActionBar barra = getSupportActionBar();
        barra.setTitle("TELEAHORCADO");
        Intent intent = this.getIntent();
        if (intent.getStringArrayListExtra("estadisticas")!=null){
            estadisticas =(ArrayList<String>) intent.getStringArrayListExtra("estadisticas");
        }
        words=getResources().getStringArray(R.array.words);
        wordLayout=findViewById(R.id.words);
        random= new Random();
        partes=new ImageView[sizePartes];
        partes[0] = findViewById(R.id.cabeza);
        partes[1] = findViewById(R.id.imagen_torso);
        partes[2] = findViewById(R.id.brazo_izq);
        partes[3] = findViewById(R.id.brazo_der);
        partes[4] = findViewById(R.id.pierna_der);
        partes[5] = findViewById(R.id.pierna_izq);

        inicio = Instant.now();
        Button nuevo = findViewById(R.id.BotonNuevoJuego);
        nuevo.setOnClickListener(view -> {
            Intent intentN = new Intent(TeleAhorcado.this, TeleAhorcado.class);
            intentN.putExtra("estadisticas", estadisticas);
            startActivity(intentN);
        });

        Button buttonA = findViewById(R.id.Button_A); buttonA.setOnClickListener(view -> {letterPresionar(buttonA);});
        Button buttonB = findViewById(R.id.Button_B); buttonB.setOnClickListener(view -> {letterPresionar(buttonB);});
        Button buttonC = findViewById(R.id.Button_C); buttonC.setOnClickListener(view -> {letterPresionar(buttonC);});
        Button buttonD = findViewById(R.id.Button_D); buttonD.setOnClickListener(view -> {letterPresionar(buttonD);});
        Button buttonE = findViewById(R.id.Button_E); buttonE.setOnClickListener(view -> {letterPresionar(buttonE);});
        Button buttonF = findViewById(R.id.Button_F); buttonF.setOnClickListener(view -> {letterPresionar(buttonF);});
        Button buttonG = findViewById(R.id.Button_G); buttonG.setOnClickListener(view -> {letterPresionar(buttonG);});
        Button buttonH= findViewById(R.id.Button_H); buttonH.setOnClickListener(view -> {letterPresionar(buttonH);});
        Button buttonI = findViewById(R.id.Button_I); buttonI.setOnClickListener(view -> {letterPresionar(buttonI);});
        Button buttonJ = findViewById(R.id.Button_J); buttonJ.setOnClickListener(view -> {letterPresionar(buttonJ);});
        Button buttonK= findViewById(R.id.Button_K); buttonK.setOnClickListener(view -> {letterPresionar(buttonK);});
        Button buttonL= findViewById(R.id.Button_L); buttonL.setOnClickListener(view -> {letterPresionar(buttonL);});
        Button buttonM = findViewById(R.id.Button_M); buttonM.setOnClickListener(view -> {letterPresionar(buttonM);});
        Button buttonN = findViewById(R.id.Button_N); buttonN.setOnClickListener(view -> {letterPresionar(buttonN);});
        Button buttonO = findViewById(R.id.Button_O); buttonO.setOnClickListener(view -> {letterPresionar(buttonO);});
        Button buttonP = findViewById(R.id.Button_P); buttonP.setOnClickListener(view -> {letterPresionar(buttonP);});
        Button buttonQ = findViewById(R.id.Button_Q); buttonQ.setOnClickListener(view -> {letterPresionar(buttonQ);});
        Button buttonR = findViewById(R.id.Button_R); buttonR.setOnClickListener(view -> {letterPresionar(buttonR);});
        Button buttonS = findViewById(R.id.Button_S); buttonS.setOnClickListener(view -> {letterPresionar(buttonS);});
        Button buttonT = findViewById(R.id.Button_T); buttonT.setOnClickListener(view -> {letterPresionar(buttonT);});
        Button buttonU = findViewById(R.id.Button_U); buttonU.setOnClickListener(view -> {letterPresionar(buttonU);});
        Button buttonV = findViewById(R.id.Button_V); buttonV.setOnClickListener(view -> {letterPresionar(buttonV);});
        Button buttonW = findViewById(R.id.Button_W); buttonW.setOnClickListener(view -> {letterPresionar(buttonW);});
        Button buttonX = findViewById(R.id.Button_X); buttonX.setOnClickListener(view -> {letterPresionar(buttonX);});
        Button buttonY = findViewById(R.id.Button_Y); buttonY.setOnClickListener(view -> {letterPresionar(buttonY);});
        Button buttonZ = findViewById(R.id.Button_Z); buttonZ.setOnClickListener(view -> {letterPresionar(buttonZ);});



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
                charViews[i].setTextSize(20);
                charViews[i].setBackgroundResource(R.drawable.baseline_minimize_24);
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
                    Instant fin = Instant.now();
                    long segundos = fin.getEpochSecond() - inicio.getEpochSecond();

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("GANASTEEE!!");
                    builder.setMessage("Tiempo de Juego: "+ segundos +" seg");
                    estadisticas.add("Terminó en "+segundos+" seg");
                    builder.setPositiveButton("Jugar de nuevo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(TeleAhorcado.this, TeleAhorcado.class);
                            intent.putExtra("estadisticas", estadisticas);
                            startActivity(intent);
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
                builder.setMessage("Vuelve a intentarlo");
                estadisticas.add("Canceló");
                builder.setPositiveButton("Jugar de nuevo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(TeleAhorcado.this, TeleAhorcado.class);
                        intent.putExtra("estadisticas", estadisticas);
                        startActivity(intent);
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
            intent.putExtra("estadisticas", estadisticas);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}