package com.example.anderson.webcoleta.util;

import android.util.Log;


import com.example.anderson.webcoleta.model.GarbagePlace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by erika on 30/04/2017.
 */

public class WebService  {

    private ArrayList<GarbagePlace> mArrGPI = new ArrayList<GarbagePlace>();
    private ArrayList<GarbagePlace> mArrGPI2 = new ArrayList<GarbagePlace>();

    private static int sINTERVAL = 0;
    private static int sSECTOR = 1;
    private static int sSTREET = 2;
    private static int sSHIFT = 3;
    private static int sROUTESECTOR = 4;
    private static int sFREQUENCY = 5;


    private GarbagePlace[] mGarbagePlacles;

    public GarbagePlace[] readGarbagePlaces () {

        try {

            URL url = new URL(GarbageConstants.sURL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10 * 1000);
            connection.setConnectTimeout(15 * 1000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) { //conseguiu conectar


                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";

                int count = 0;
                while ((line = reader.readLine()) != null) {
                    // "," ou ";" de acordo com o arquivo
                    String[] row = line.split(";");
                    count++;
                    // Log.i("Linha: ", row[0]);
                    if (count > 1) {
                        GarbagePlace mGarbagePlace = new GarbagePlace();
                        mGarbagePlace.setId(String.valueOf(count - 1));
                        mGarbagePlace.setInterval(row[sINTERVAL]);
                        mGarbagePlace.setSector(row[sSECTOR]);
                        mGarbagePlace.setStreet(row[sSTREET]);
                        mGarbagePlace.setShift(row[sSHIFT]);
                        mGarbagePlace.setRouteSector(row[sROUTESECTOR]);
                        mGarbagePlace.setFrequency(row[sFREQUENCY]);
                        mArrGPI.add(mGarbagePlace);
                        //  Log.i("Valor = ", String.valueOf(count));
                    }

                }
                //  mGGL = new GarbagePlaceGeosonList();
                mGarbagePlacles = new GarbagePlace[mArrGPI.size()];

                for (int i = 0; i < mGarbagePlacles.length; i++) {
                    mGarbagePlacles[i] = new GarbagePlace();
                    mGarbagePlacles[i] = mArrGPI.get(i);
                    mGarbagePlacles[i].setId(Integer.toString(i));
                }

            } else {
                mGarbagePlacles = null;
                Log.i("WebService","Erro HTTP " + connection.getResponseCode() );
                //  Snackbar.make(mListPlaces, getString(R.string.connection_exception), Snackbar.LENGTH_LONG).show();
            }

        } catch (IOException e2) {
            // Toast.makeText(ListPlacesActivity.this, R.string.connection_exception, Toast.LENGTH_LONG).show();
            //Snackbar.make(mListPlaces, getString(R.string.connection_exception), Snackbar.LENGTH_LONG).show();
            mGarbagePlacles = null;
            Log.i("Exceção: ", e2.toString());
        }


        //retorna o array contendo todos os pontos de coleta
        return mGarbagePlacles;
    }


    public GarbagePlace[] readSetorGarbagePlaces (String letra) {

        int opcao;

        if(letra.length() > 1)
            opcao = sSHIFT;
        else
            opcao = sSECTOR;

        try {

            URL url = new URL(GarbageConstants.sURL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10 * 1000);
            connection.setConnectTimeout(15 * 1000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) { //conseguiu conectar


                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                int count = 0;
                while ((line = reader.readLine()) != null) {
                    // "," ou ";" de acordo com o arquivo
                    String[] row = line.split(";");
                    count++;
                    // Log.i("Linha: ", row[0]);
                    if (count > 1) {

                        if(row[opcao].toLowerCase().toString().contains(letra.toLowerCase())) {
                            GarbagePlace mGarbagePlace = new GarbagePlace();
                            mGarbagePlace.setId(String.valueOf(count - 1));
                            mGarbagePlace.setInterval(row[sINTERVAL]);
                            mGarbagePlace.setSector(row[sSECTOR]);
                            mGarbagePlace.setStreet(row[sSTREET]);
                            mGarbagePlace.setShift(row[sSHIFT]);
                            mGarbagePlace.setRouteSector(row[sROUTESECTOR]);
                            mGarbagePlace.setFrequency(row[sFREQUENCY]);
                            mArrGPI2.add(mGarbagePlace);
                        }
                        //  Log.i("Valor = ", String.valueOf(count));
                    }

                }
                //  mGGL = new GarbagePlaceGeosonList();
                mGarbagePlacles = new GarbagePlace[mArrGPI2.size()];

                for (int i = 0; i < mGarbagePlacles.length; i++) {
                    mGarbagePlacles[i] = new GarbagePlace();
                    mGarbagePlacles[i] = mArrGPI2.get(i);
                    mGarbagePlacles[i].setId(Integer.toString(i));
                }

            } else {
                mGarbagePlacles = null;
                Log.i("WebService","Erro HTTP " + connection.getResponseCode());
                //  Snackbar.make(mListPlaces, getString(R.string.connection_exception), Snackbar.LENGTH_LONG).show();
            }
        } catch (IOException e2) {
            // Toast.makeText(ListPlacesActivity.this, R.string.connection_exception, Toast.LENGTH_LONG).show();
            //Snackbar.make(mListPlaces, getString(R.string.connection_exception), Snackbar.LENGTH_LONG).show();
            mGarbagePlacles = null;
            Log.i("Exceção: ", e2.toString());
        }

        //retorna o array contendo todos os pontos de coleta
        return mGarbagePlacles;
    }

}