package com.example.anderson.webcoleta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anderson.webcoleta.adapter.GarbagePlacesAdapter;
import com.example.anderson.webcoleta.model.GarbagePlace;
import com.example.anderson.webcoleta.model.GarbagePlaceGeosonList;
import com.example.anderson.webcoleta.model.GarbagePlaceInfo;
import com.example.anderson.webcoleta.util.LogWrapper;
import com.example.anderson.webcoleta.util.Utils;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

//alterando o arquivo para testar commit e sincronozação

// Alterando pra testar Dani alterando - parte 2

public class MainActivity extends AppCompatActivity {

    private Gson mGson;

    private GarbagePlacesAdapter mGarbagePlacesAdapter;

    public static final String EXTRA_PLACE = "place";

    private ListView mListGarbagePlaces;

    private GarbagePlace[] mGarbagePlacles;

    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListGarbagePlaces = (ListView)findViewById(R.id.list_garbage_places);
        mListGarbagePlaces.setEmptyView(findViewById(android.R.id.empty));
        new SyncDataTask().execute();
    }


    //Fazer essa função
    private void loadData() {

        try {
            mGson = new Gson(); // precisa da dependencia no gradle --> compile 'com.google.code.gson:gson:2.2.4'

          //  PlaceGeosonList places;

            URL url = new URL(Utils.sURL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10 * 1000);
            connection.setConnectTimeout(15 * 1000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) { //conseguiu conectar



                final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line="";
                ArrayList<GarbagePlaceInfo> ArrGPI = new ArrayList<GarbagePlaceInfo>();
                GarbagePlaceGeosonList mGGL;


                GarbagePlaceInfo mGarbagePlaceInfo = new GarbagePlaceInfo();
                int count = 0;
                while ((line = reader.readLine()) != null) {

                    // "," ou ";" de acordo com o arquivo

                    String[] row = line.split(";");
                    count++;
                   Log.i("Linha: ", row[0]);
                    if(count > 1) {

                        mGarbagePlaceInfo.setId(String.valueOf(count-1));
                        mGarbagePlaceInfo.setIntervalo(row[0]);
                        mGarbagePlaceInfo.setSetor(row[1]);
                        mGarbagePlaceInfo.setEndereco(row[2]);
                        mGarbagePlaceInfo.setTurno(row[3]);
                        mGarbagePlaceInfo.setRotaSetor(row[4]);
                        mGarbagePlaceInfo.setFrequencia(row[5]);
                        ArrGPI.add(mGarbagePlaceInfo);
                        Log.i("Valor = ", String.valueOf(count));
                    }

                }
              //  mGGL = new GarbagePlaceGeosonList();
                mGarbagePlacles = new GarbagePlace[ArrGPI.size()];

                for(int i = 0; i< mGarbagePlacles.length; i++)
                {
                    mGarbagePlacles[i] = new GarbagePlace();
                    mGarbagePlacles[i].setProperties(ArrGPI.get(i));
                    mGarbagePlacles[i].setId(Integer.toString(i));
                    mGarbagePlacles[i].setType("GarbagePlace");

                }




/*
                places = mGson.fromJson(reader, PlaceGeosonList.class); //convertendo o que vem em JSON para um objeto PlaceGeosonList

                mPlacles = places.getFeatures();
*/
            } else {
                LogWrapper.log("Erro HTTP " + connection.getResponseCode());
              //  Snackbar.make(mListPlaces, getString(R.string.connection_exception), Snackbar.LENGTH_LONG).show();
            }


        } catch (IOException e) {
           // Toast.makeText(ListPlacesActivity.this, R.string.connection_exception, Toast.LENGTH_LONG).show();
            //Snackbar.make(mListPlaces, getString(R.string.connection_exception), Snackbar.LENGTH_LONG).show();
            Log.i("Exceção: ", e.toString());
        }
    }


    private void setupListView() {
        //init adapter
        mGarbagePlacesAdapter = new GarbagePlacesAdapter(this, mGarbagePlacles);

        mListGarbagePlaces.setAdapter(mGarbagePlacesAdapter);

        addListFooter(); // --> FAZER
        addListHeared();//  --> FAZER

        mListGarbagePlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GarbagePlace place = (GarbagePlace) adapterView.getItemAtPosition(i);

                Intent it = new Intent(MainActivity.this, GarbagePlaceDetailActivity.class);
                it.putExtra(EXTRA_PLACE, place);

                startActivity(it);
            }
        });
    }
    private void addListFooter() {
        final int PADDING = 10;
        TextView txtHeader = new TextView(this);
        txtHeader.setBackgroundColor(Color.BLUE);
        txtHeader.setTextColor(Color.WHITE);
        txtHeader.setText(R.string.list_header_text);
        txtHeader.setPadding(PADDING, PADDING, 0, PADDING);
        mListGarbagePlaces.addHeaderView(txtHeader);

    }

    private void addListHeared() {
        final int PADDING = 10;
        final TextView txtFooter = new TextView(this);
        txtFooter.setText(getResources().getQuantityString(
                R.plurals.list_header_plural,
                mGarbagePlacesAdapter.getCount(),
                mGarbagePlacesAdapter.getCount()));
        txtFooter.setBackgroundColor(Color.BLUE);
        txtFooter.setGravity(Gravity.LEFT);
        txtFooter.setPadding(0, PADDING, PADDING, PADDING);
        mListGarbagePlaces.addFooterView(txtFooter);

    }



    private class SyncDataTask extends AsyncTask<Object, Object, Object> {

        @Override
        protected void onPreExecute() {
            //Show progress while downloading data
            mProgress = new ProgressDialog(MainActivity.this);
            mProgress.setTitle(R.string.dialog_wait_title);
            mProgress.setIcon(getDrawable(R.drawable.garbage_icon));
            mProgress.setMessage(getString(R.string.dialog_wait_message));
            mProgress.show();

        }

        @Override
        protected Object doInBackground(Object... params) {
            //load data in backgroud
            loadData();
            return null;
        }


        @Override
        protected void onPostExecute(Object result) {
            //Dismiss project and setup List Adapter
            if (mProgress != null && mProgress.isShowing()) {
                try {
                    mProgress.dismiss();
                } catch (Exception e) {

                }
            }
            setupListView();

        }
    }
}
