package com.example.anderson.webcoleta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.anderson.webcoleta.adapter.GarbagePlacesAdapter;
import com.example.anderson.webcoleta.model.GarbagePlace;
import com.example.anderson.webcoleta.util.GarbageConstants;
import com.example.anderson.webcoleta.util.LogWrapper;
import com.example.anderson.webcoleta.util.Utils;
import com.example.anderson.webcoleta.util.WebService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GarbagePlaceListActivity extends AppCompatActivity {



    private GarbagePlacesAdapter mGarbagePlacesAdapter;

    private ListView mListGarbagePlaces;

    private GarbagePlace[] mGarbagePlacles;

    private ProgressDialog mProgress;

    ArrayList<GarbagePlace> ArrGPI = new ArrayList<GarbagePlace>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garbage_place_list);

        mListGarbagePlaces = (ListView)findViewById(R.id.list_garbage_places);
        mListGarbagePlaces.setEmptyView(findViewById(android.R.id.empty));
        new SyncDataTask().execute();

    }


    //Fazer essa função
    private void loadData() {

        WebService service = new WebService();
        mGarbagePlacles = service.readGarbagePlaces();

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

                Intent it = new Intent(GarbagePlaceListActivity.this, GarbagePlaceDetailActivity.class);
                it.putExtra(GarbageConstants.sEXTRA_PLACE, place);

                startActivity(it);
            }
        });
    }
    private void addListFooter() {
        final int PADDING = 10;
        TextView txtHeader = new TextView(this);
        txtHeader.setBackgroundColor(Color.GRAY);
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
       // txtFooter.setBackgroundColor(Color.BLUE);
        txtFooter.setGravity(Gravity.LEFT);
        txtFooter.setPadding(0, PADDING, PADDING, PADDING);
        mListGarbagePlaces.addFooterView(txtFooter);

    }


    private class SyncDataTask extends AsyncTask<Object, Object, Object> {

        @Override
        protected void onPreExecute() {
            //Show progress while downloading data
            mProgress = new ProgressDialog(GarbagePlaceListActivity.this);
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
