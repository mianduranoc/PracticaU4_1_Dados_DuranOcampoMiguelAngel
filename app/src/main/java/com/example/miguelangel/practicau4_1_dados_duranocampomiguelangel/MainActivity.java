package com.example.miguelangel.practicau4_1_dados_duranocampomiguelangel;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tiros[],totales[],ronda,turno,ganador;
    ImageView dados[];
    Button iniciar;
    Jugador jugadores[];
    class Jugador {
        final TextView[] tiros1;
        final ImageView[] dados1;
        final TextView total;
        int totalgeneral;
        boolean seleccionado;
        int tiro;

        public Jugador(TextView[] tiros, int i, int i1, TextView totale, ImageView[] dados, int i2, int i3) {
            this.tiros1 = new TextView[4];
            this.dados1 = new ImageView[2];
            for (int j = i, k = 0; j <= i1; j++, k++) {
                this.tiros1[k] = tiros[j];
            }

            for (int j = i2, k = 0; j <= i3; j++, k++) {
                this.dados1[k] = dados[j];
            }
            total = totale;
            totalgeneral = 0;
            tiro = 1;
            seleccionado = false;

        }
        public void iniciar(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        if (!seleccionado) continue;
                        final int v1 = tirarDado(), v2 = tirarDado();
                        totalgeneral += v1 + v2;
                        Log.e("E","Valor antes del hilo"+totalgeneral);
                        proceso1(v1,v2);
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.e("e","Antes de imprimir"+totalgeneral);
                        proceso2();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        tiro++;
                    }}
            }).start();
        }
        public void proceso1(final int v1,final int v2){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    seleccionarDado(dados1[0], v1);
                    seleccionarDado(dados1[1], v2);
                    switch (tiro) {
                        case 1:
                            tiros1[0].setText(tiros1[0].getText().toString() + (v1 + v2));
                            break;
                        case 2:
                            tiros1[1].setText(tiros1[1].getText().toString() + (v1 + v2) + "");
                            break;
                        case 3:
                            tiros1[2].setText(tiros1[2].getText().toString() + (v1 + v2) + "");
                            break;
                        case 4:
                            tiros1[3].setText(tiros1[3].getText().toString() + (v1 + v2) + "");
                            break;
                    }
                }
            });
        }
        public void proceso2(){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e("Error","El valor de total es: "+totalgeneral);
                    total.setText("Total: "+totalgeneral);
                    seleccionarDado(dados1[0], 0);
                    seleccionarDado(dados1[1], 0);
                }
            });
        }
            public int getTotalgeneral () {
                return totalgeneral;
            }
            public void setSeleccionado ( boolean estado){
                this.seleccionado = estado;
            }
            private int tirarDado () {
                return (int) ((Math.random() * 5) + 1);
            }
            private void seleccionarDado (ImageView image,int numero){
                switch (numero) {
                    case 0:
                        image.setImageResource(R.drawable.dado);
                        break;
                    case 1:
                        image.setImageResource(R.drawable.dado1);
                        break;
                    case 2:
                        image.setImageResource(R.drawable.dado2);
                        break;
                    case 3:
                        image.setImageResource(R.drawable.dado3);
                        break;
                    case 4:
                        image.setImageResource(R.drawable.dado4);
                        break;
                    case 5:
                        image.setImageResource(R.drawable.dado5);
                        break;
                    case 6:
                        image.setImageResource(R.drawable.dado6);
                        break;
                }
            }
            public void reiniciarTotal () {
                totalgeneral = 0;
            }
            public void limpiarCampos () {
                for (int j = 0; j < tiros1.length; j++) {
                    tiros1[j].setText("Tiro " + j + ":");
                }
                total.setText("Total: ");
                tiro=1;
            }
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ganador=findViewById(R.id.resultado);
        //Tiros
        tiros=new TextView[12];
        //Jugador 1
        tiros[0]=findViewById(R.id.jugador1t1);
        tiros[1]=findViewById(R.id.jugador1t2);
        tiros[2]=findViewById(R.id.jugador1t3);
        tiros[3]=findViewById(R.id.jugador1t4);
        //Jugador 2
        tiros[4]=findViewById(R.id.jugador2t1);
        tiros[5]=findViewById(R.id.jugador2t2);
        tiros[6]=findViewById(R.id.jugador2t3);
        tiros[7]=findViewById(R.id.jugador2t4);
        //Jugador 3
        tiros[8]=findViewById(R.id.jugador3t1);
        tiros[9]=findViewById(R.id.jugador3t2);
        tiros[10]=findViewById(R.id.jugador3t3);
        tiros[11]=findViewById(R.id.jugador3t4);
        //Totales
        totales=new TextView[3];
        totales[0]=findViewById(R.id.jugador1to);
        totales[1]=findViewById(R.id.jugador2to);
        totales[2]=findViewById(R.id.jugador3to);
        //Dados
        dados=new ImageView[6];
        dados[0]=findViewById(R.id.jugador1d1);
        dados[1]=findViewById(R.id.jugador1d2);
        dados[2]=findViewById(R.id.jugador2d1);
        dados[3]=findViewById(R.id.jugador2d2);
        dados[4]=findViewById(R.id.jugador3d1);
        dados[5]=findViewById(R.id.jugador3d2);
        //Ronda
        ronda=findViewById(R.id.ronda);
        //Turno
        turno=findViewById(R.id.nojugador);
        //Boton de inicio
        iniciar=findViewById(R.id.iniciar);
        //Jugadores
        jugadores=new Jugador[3];
        jugadores[0]=new Jugador(tiros,0,3,totales[0],dados,0,1);
        jugadores[1]=new Jugador(tiros,4,7,totales[1],dados,2,3);
        jugadores[2]=new Jugador(tiros,8,11,totales[2],dados,4,5);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ganador.setText("Ganador: ");
                ronda.setText("0");
                turno.setText("0");
                limpiar();
                jugadores[0].iniciar();
                jugadores[1].iniciar();
                jugadores[2].iniciar();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iniciar.setEnabled(false);
                            }
                        });

                        for (int i=1;i<=4;i++){
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            for (int j=0;j<jugadores.length;j++){
                                final int i1=i;
                                final int j1=j;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ronda.setText(i1+"");
                                        turno.setText((j1+1)+"");
                                    }
                                });

                                jugadores[j].setSeleccionado(true);
                                try {
                                    Thread.sleep(4000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                jugadores[j].setSeleccionado(false);
                            }
                        }
                        obtenerGanador();
                    }
                }).start();
            }
        });
    }
    private void limpiar() {
        for (int i=0;i<jugadores.length;i++){
            jugadores[i].limpiarCampos();
        }
        jugadores[0]=new Jugador(tiros,0,3,totales[0],dados,0,1);
        jugadores[1]=new Jugador(tiros,4,7,totales[1],dados,2,3);
        jugadores[2]=new Jugador(tiros,8,11,totales[2],dados,4,5);
    }

    private void obtenerGanador() {
        int jugador1=jugadores[0].getTotalgeneral(),jugador2=jugadores[1].getTotalgeneral(),jugador3=jugadores[2].getTotalgeneral();
        for (int i=0;i<jugadores.length;i++){
            jugadores[i].reiniciarTotal();
        }
        if (jugador1==jugador2 && jugador2==jugador3)ganador.setText(ganador.getText()+" J1 y J2 y J3");
        if (jugador1>=jugador2){
            if (jugador1>=jugador3){
                if (jugador1==jugador3)ganador.setText(ganador.getText()+" J1 y J3");
                else if (jugador1==jugador2)ganador.setText(ganador.getText()+" J1 y J2");
                else ganador.setText(ganador.getText()+" Jugador 1");
            }
            else if (jugador1==jugador2)ganador.setText(ganador.getText()+" J1 y J2");
            else {
                ganador.setText(ganador.getText()+" Jugador 3");
            }
        }
        else if (jugador2>jugador3){
            ganador.setText(ganador.getText()+" Jugador 2");
        }
        else if (jugador2==jugador3)ganador.setText(ganador.getText()+" J2 y J3");
        else {
            ganador.setText(ganador.getText()+" Jugador 3");
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                iniciar.setEnabled(true);
            }
        });

    }
}
