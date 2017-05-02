package com.example.anderson.webcoleta.util;

import android.content.SharedPreferences;

/**
 * Created by erika on 01/05/2017.
 *
 * Salva, lê e apaga informações de preferências do usuário usando SharedPreferences
 *
 */

public class SavePreferences {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditorPreferences;

    private static SavePreferences instance;

    public static SavePreferences getInstance(SharedPreferences shared){
        if (instance == null) {
            instance = new SavePreferences(shared);
        }
        return instance;
    }

    private SavePreferences(SharedPreferences shared){
        //Seta o sharedPreferences
        mSharedPreferences = shared;
    }


    //Salvando informações no sharedPreferences
    public void savePreference(String key, String value) {

        mEditorPreferences = mSharedPreferences.edit();
        mEditorPreferences.putString(key, value);
        mEditorPreferences.apply();
    }

    //Apagando todas as informações no sharedPreferences
    public void clearPreference() {

        mEditorPreferences = mSharedPreferences.edit();
        mEditorPreferences.clear();
        mEditorPreferences.apply();
    }

    //retornando um valor específico salvo em sharedPreference
    public String getPreference(String key) {

        String value = mSharedPreferences.getString(key, "");

        return value;
    }



}

