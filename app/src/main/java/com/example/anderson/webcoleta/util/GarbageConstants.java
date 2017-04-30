package com.example.anderson.webcoleta.util;

import android.support.v7.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.widget.ListView;

import com.example.anderson.webcoleta.adapter.GarbagePlacesAdapter;
import com.google.android.gms.location.places.Place;
import com.google.gson.Gson;


public class GarbageConstants  {


    //constante que referencia um garbagaplace passado por uma intent
    public static final String sEXTRA_PLACE = "place";
    //Precisa dessa??
    public static final String EXTRA_PLACE_ROUTE = "garbage_place_route";

}
