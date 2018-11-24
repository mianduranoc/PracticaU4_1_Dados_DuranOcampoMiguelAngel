package com.example.miguelangel.practicau4_1_dados_duranocampomiguelangel;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

public class Jugador extends Thread{
    TextView[] tiros;
    ImageView[] dados;
    TextView total;
    int totalgeneral;
    boolean seleccionado;
    Activity ventana;
    int tiro;
    public Jugador(TextView[] tiros, int i, int i1, TextView totale, ImageView[] dados, int i2, int i3,Activity activity) {
        this.tiros=new TextView[4];
        this.dados=new ImageView[2];
        for (int j =i,k=0;j<i1;j++,k++){
            this.tiros[k]=tiros[j];
        }

        for (int j =i2,k=0;j<i3;j++,k++){
            this.dados[k]=dados[j];
        }
        total=totale;
        totalgeneral=0;
        ventana=activity;
        tiro=1;
        seleccionado=false;
    }
    @Override
    public void run(){
        while(seleccionado){
            final int v1=tirarDado(),v2=tirarDado();
            ventana.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    seleccionarDado(dados[0],v1);
                    seleccionarDado(dados[1],v2);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    switch (tiro){
                        case 1:
                            tiros[0].setText(tiros[0].getText().toString()+(v1+v2)+"");

                            return;
                        case 2:
                            tiros[1].setText(tiros[1].getText().toString()+(v1+v2)+"");
                            return;
                        case 3:
                            tiros[2].setText(tiros[2].getText().toString()+(v1+v2)+"");
                            return;
                        case 4:
                            tiros[3].setText(tiros[3].getText().toString()+(v1+v2)+"");
                            return;
                    }
                    totalgeneral+=v1+v2;
                    total.setText(totalgeneral+"");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    seleccionarDado(dados[0],0);
                    seleccionarDado(dados[1],0);
                    seleccionado=false;
                }
            });

        }
    }
    public int getTotalgeneral(){
        return totalgeneral;
    }
    public void setSeleccionado(boolean estado){
        this.seleccionado=estado;
    }
    private int tirarDado(){
        return (int)((Math.random()*5)+1);
    }
    private void seleccionarDado(ImageView image,int numero){
        switch (numero){
            case 0:
                image.setImageResource(R.drawable.dado);
                return;
            case 1:
                image.setImageResource(R.drawable.dado1);
                return;
            case 2:
                image.setImageResource(R.drawable.dado2);
                return;
            case 3:
                image.setImageResource(R.drawable.dado3);
                return;
            case 4:
                image.setImageResource(R.drawable.dado4);
                return;
            case 5:
                image.setImageResource(R.drawable.dado6);
                return;
            case 6:
                image.setImageResource(R.drawable.dado6);
                return;
        }
    }
    public void reiniciarTotal(){
        totalgeneral=0;
    }
    public void limpiarCampos(){
        for (int j =0;j<tiros.length;j++){
            tiros[j].setText("Tiro "+j+":");
        }
    }
}
