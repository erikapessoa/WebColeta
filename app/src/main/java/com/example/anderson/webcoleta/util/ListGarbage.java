package com.example.anderson.webcoleta.util;

import android.support.v7.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.widget.ListView;

import com.example.anderson.webcoleta.adapter.GarbagePlacesAdapter;
import com.google.android.gms.location.places.Place;
import com.google.gson.Gson;


/**
 * Created by Anderson on 12/04/2017.
 */

public class ListGarbage extends AppCompatActivity {


    private Gson mGson;

    private GarbagePlacesAdapter mPlacesAdapter;

    public static final String EXTRA_PLACE = "place";

    private ListView mListPlaces;

    private Place[] mPlacles; //modificar

    private ProgressDialog mProgress;

}
