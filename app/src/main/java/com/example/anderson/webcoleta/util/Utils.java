package com.example.anderson.webcoleta.util;

/**
 * Created by Anderson on 12/04/2017.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;



import com.example.anderson.webcoleta.app.App;

public final class Utils {

    //public static final String sURL = "http://dados.recife.pe.gov.br/dataset/eeee4ac5-d0e0-490b-aac0-490a6de74e07/resource/8d05eb77-a83d-4e9b-b5b6-6c3bd71e736d/download/pontosturisticosciclfxs.geojson";
   /*Link em CSV -->*/ public static final String sURL = "http://dados.recife.pe.gov.br/dataset/aa9ab544-dc34-4312-8c8f-68a5da7c1ee1/resource/f4ca6471-bb7b-4412-b248-d522948aa789/download/roteirizacao.csv";



    private Utils(){

    }

    public static boolean hasIntenetConnection(){
        ConnectivityManager cm = (ConnectivityManager)
                App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected());

    }
}
