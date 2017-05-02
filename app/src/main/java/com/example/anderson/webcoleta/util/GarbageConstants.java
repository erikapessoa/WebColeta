package com.example.anderson.webcoleta.util;

import android.support.v7.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.widget.ListView;

import com.example.anderson.webcoleta.adapter.GarbagePlacesAdapter;
import com.google.android.gms.location.places.Place;
import com.google.gson.Gson;


public class GarbageConstants  {


    /*Link do CSV -->*/
    public static final String sURL = "http://dados.recife.pe.gov.br/dataset/aa9ab544-dc34-4312-8c8f-68a5da7c1ee1/resource/f4ca6471-bb7b-4412-b248-d522948aa789/download/roteirizacao.csv";
    //constante que referencia um garbagaplace passado por uma intent
    public static final String sEXTRA_PLACE = "place";
    //constantes relacionadas as opções de antecedencia de aviso. Caso altere aqui, precisa alterar também no
    //arquivo de strings rn_time_15, rn_time_30, rn_time_60
    public static final int sTime_1 = 15;
    public static final int sTime_2 = 30;
    public static final int sTime_3 = 60;
    //constantes relacionadas as preferencias salvas do usuario
    public static final String sFILE_SHARED_PREFERENCE = "prefs_webcoleta";
    public static final String sKey_Time = "key_time_preference";
    public static final String sKey_Street_Name = "key_street_name_preference";
    public static final String sKey_Frequency = "key_frequency_preference";
    public static final String sKey_Interval = "key_interval_preference";

}
