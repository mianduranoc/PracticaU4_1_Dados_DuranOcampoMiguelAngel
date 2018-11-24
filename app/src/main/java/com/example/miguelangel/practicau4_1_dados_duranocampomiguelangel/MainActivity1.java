/*package com.example.miguelangel.practicau4_1_dados_duranocampomiguelangel;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity1 extends AppCompatActivity {

    private int ronda,jugador_id;
    private Button btn;
    private TextView txt_ronda,txt_turno,txt_dado_1_jugador_1,txt_dado_2_jugador_1,txt_dado_1_jugador_2,txt_dado_2_jugador_2,txt_dado_1_jugador_3,txt_dado_2_jugador_3,txt_estatus_jugador_1,txt_estatus_jugador_2,txt_estatus_jugador_3;
    private boolean iniciado;
    private int contador;
    private ArrayList<Jugador> jugadores;

    class Jugador{

        private TextView txt_dado_1_jugador,txt_dado_2_jugador,txt_estatus_jugador;
        public boolean seleccionado;
        public int turno;
        private int tiro1,tiro2,tiro3,tiro4,tiro5,tiro6,tiro7,tiro8;

        public Jugador(TextView txt_dado_1_jugador,TextView txt_dado_2_jugador,TextView estatus){
            this.txt_dado_1_jugador=txt_dado_1_jugador;
            this.txt_dado_2_jugador=txt_dado_2_jugador;
            txt_estatus_jugador=estatus;
            reiniciar_valores();
        }

        private void reiniciar_valores(){
            seleccionado=false;
            tiro1=0;
            tiro2=0;
            tiro3=0;
            tiro4=0;
            tiro5=0;
            tiro6=0;
            tiro7=0;
            tiro8=0;
            turno=0;
            reset_dados();
        }
        public int getTotal(){
            return tiro1+tiro2+tiro3+tiro4+tiro5+tiro6+tiro7+tiro8;
        }
        public void reset_dados(){
            txt_dado_1_jugador.setText("-");
            txt_dado_2_jugador.setText("-");
        }

        public int tirar_dado(){
            return (int)((Math.random()*5)+1);
        }
        public void iniciar(){
            String cad= "Tiro 1:"+(tiro1+tiro2)+"\nTiro 2:"+(tiro3+tiro4)+"\nTiro 3:"+(tiro5+tiro6)+"\nTiro 4:"+(tiro7+tiro8)+"\nTotal:"+(tiro1+tiro2+tiro3+tiro4+tiro5+tiro6+tiro7+tiro8);
            txt_estatus_jugador.setText(cad);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(iniciado){
                        if(!seleccionado)
                            continue;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int valor1=tirar_dado(),valor2=tirar_dado();
                                if(turno==0) {
                                    tiro1=valor1;
                                    tiro2=valor2;
                                }else if(turno==1) {
                                    tiro3=valor1;
                                    tiro4=valor2;
                                }else if(turno==2) {
                                    tiro5=valor1;
                                    tiro6=valor2;
                                }else if(turno==3) {
                                    tiro7=valor1;
                                    tiro8=valor2;
                                }

                                txt_dado_1_jugador.setText(valor1+"");
                                txt_dado_2_jugador.setText(valor2+"");
                                String cad= "Tiro 1:"+(tiro1+tiro2)+"\nTiro 2:"+(tiro3+tiro4)+"\nTiro 3:"+(tiro5+tiro6)+"\nTiro 4:"+(tiro7+tiro8)+"\nTotal:"+(tiro1+tiro2+tiro3+tiro4+tiro5+tiro6+tiro7+tiro8);
                                txt_estatus_jugador.setText(cad);
                            }
                        });
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_ronda=findViewById(R.id.txt_ronda);
        txt_turno=findViewById(R.id.txt_turno);

        jugadores=new ArrayList<>();

        btn=findViewById(R.id.btn);
        txt_dado_1_jugador_1=findViewById(R.id.txt_dado_1_jugador_1);
        txt_dado_2_jugador_1=findViewById(R.id.txt_dado_2_jugador_1);
        txt_dado_1_jugador_2=findViewById(R.id.txt_dado_1_jugador_2);
        txt_dado_2_jugador_2=findViewById(R.id.txt_dado_2_jugador_2);
        txt_dado_1_jugador_3=findViewById(R.id.txt_dado_1_jugador_3);
        txt_dado_2_jugador_3=findViewById(R.id.txt_dado_2_jugador_3);
        txt_estatus_jugador_1=findViewById(R.id.txt_estatus_jugador_1);
        txt_estatus_jugador_2=findViewById(R.id.txt_estatus_jugador_2);
        txt_estatus_jugador_3=findViewById(R.id.txt_estatus_jugador_3);

    }

    public void iniciar_hilo(View v)  {
        btn.setEnabled(false);
        ronda=0;
        jugador_id=0;
        iniciado=true;
        jugadores.clear();
        Jugador j1=new Jugador(txt_dado_1_jugador_1,txt_dado_2_jugador_1,txt_estatus_jugador_1);
        jugadores.add(j1);
        j1.iniciar();
        Jugador j2=new Jugador(txt_dado_1_jugador_2,txt_dado_2_jugador_2,txt_estatus_jugador_2);
        jugadores.add(j2);
        j2.iniciar();
        Jugador j3=new Jugador(txt_dado_1_jugador_3,txt_dado_2_jugador_3,txt_estatus_jugador_3);
        jugadores.add(j3);
        j3.iniciar();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<4;i++){
                    ronda=i;
                    for (int e=0;e<jugadores.size();e++){

                        final Jugador jugador=jugadores.get(e);
                        jugador.turno=i;
                        jugador_id=e;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txt_ronda.setText("Ronda\n"+(ronda+1));
                                txt_turno.setText("Turno de jugador\n"+(jugador_id+1));
                            }
                        });

                        jugador.seleccionado=true;

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                        jugador.seleccionado=false;

                        /*runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final Jugador jugador_ant = jugadores.get(jugador_id - 2);
                                jugador_ant.reset_dados();
                            }
                        });
                    }
                }
                btn.setEnabled(true);
                int tot1=jugadores.get(0).getTotal();
                int tot2=jugadores.get(1).getTotal();
                int tot3=jugadores.get(2).getTotal();
                if(tot1>tot2&&tot1>tot3){
                    Toast.makeText(MainActivity1.this,"El ganador es el jugador 1",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(tot2>tot1&&tot2>tot3){
                    Toast.makeText(MainActivity1.this,"El ganador es el jugador 2",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(tot3>tot1&&tot3>tot2){
                    Toast.makeText(MainActivity1.this,"El ganador es el jugador 2",Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(MainActivity1.this,"Empate",Toast.LENGTH_SHORT).show();

            }
        }).start();
    }
}
*/