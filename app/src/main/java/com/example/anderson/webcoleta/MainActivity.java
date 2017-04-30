package com.example.anderson.webcoleta;

import android.content.Intent;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void abrirMenu(View v) {

        Intent it = new Intent(this, MenuActivity.class);
        startActivity(it);

    }

/*
    private Gson mGson;

    private GarbagePlacesAdapter mGarbagePlacesAdapter;

    public static final String sEXTRA_PLACE = "place";

    private ListView mListGarbagePlaces;

    private GarbagePlace[] mGarbagePlacles;

    private ProgressDialog mProgress;

    ArrayList<GarbagePlace> ArrGPI = new ArrayList<GarbagePlace>();



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

                int count = 0;
                while ((line = reader.readLine()) != null) {

                    // "," ou ";" de acordo com o arquivo

                    String[] row = line.split(";");
                    count++;
                   Log.i("Linha: ", row[0]);
                    if(count > 1) {
                        GarbagePlace mGarbagePlace = new GarbagePlace();
                        mGarbagePlace.setId(String.valueOf(count-1));
                        mGarbagePlace.setIntervalo(row[0]);
                        mGarbagePlace.setSetor(row[1]);
                        mGarbagePlace.setEndereco(row[2]);
                        mGarbagePlace.setTurno(row[3]);
                        mGarbagePlace.setRotaSetor(row[4]);
                        mGarbagePlace.setFrequencia(row[5]);
                        ArrGPI.add(mGarbagePlace);
                        Log.i("Valor = ", String.valueOf(count));
                    }

                }
              //  mGGL = new GarbagePlaceGeosonList();
                mGarbagePlacles = new GarbagePlace[ArrGPI.size()];

                for(int i = 0; i< mGarbagePlacles.length; i++)
                {
                    mGarbagePlacles[i] = new GarbagePlace();
                    mGarbagePlacles[i] = ArrGPI.get(i);
                    mGarbagePlacles[i].setId(Integer.toString(i));
                    //mGarbagePlacles[i].setType("GarbagePlace");

                }




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
                it.putExtra(sEXTRA_PLACE, place);

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

 */

}
