package com.example.anderson.webcoleta.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Anderson on 12/04/2017.
 */

public class App extends Application {

    private static Context mContext = null;
    @Override
    public void onCreate() {
        super.onCreate();
        setContext(getApplicationContext());

    }

    private static void setContext(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }
}
