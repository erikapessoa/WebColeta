package com.example.anderson.webcoleta;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.anderson.webcoleta.model.GarbagePlace;
import com.example.anderson.webcoleta.util.GarbageConstants;
import com.example.anderson.webcoleta.util.SavePreferences;

public class RegisterNotificationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_notification);
    }

    //cadastrar as preferências (tempo de antecedência do aviso
    public void savePreferences(View v) {

        RadioGroup mRadioGroup = null;
        GarbagePlace place = null;
        RadioButton radio = null;
        int idRadioSelected, radioId = 0;
        SharedPreferences sp = null;
        SavePreferences savePrefs = null;
        String time = "";

        place = (GarbagePlace) getIntent().getExtras().get(GarbageConstants.sEXTRA_PLACE);
        mRadioGroup = (RadioGroup)findViewById(R.id.timeOptions);
        idRadioSelected = mRadioGroup.getCheckedRadioButtonId();
        radio = (RadioButton)findViewById(idRadioSelected);
        //radioText = radio.getText().toString();
        radioId = mRadioGroup.indexOfChild(radio);

        if(radioId == GarbageConstants.sTime_1)
            time = String.valueOf(GarbageConstants.sTime_1);
        else if(radioId == GarbageConstants.sTime_2)
            time = String.valueOf(GarbageConstants.sTime_2);
        else time = String.valueOf(GarbageConstants.sTime_3);

        //Salvando as preferências no sharedPreferences
        sp = getSharedPreferences(GarbageConstants.sFILE_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        savePrefs = SavePreferences.getInstance(sp);
        savePrefs.savePreference(GarbageConstants.sKey_Time, time);
        savePrefs.savePreference(GarbageConstants.sKey_Street_Name, place.getEndereco());
        savePrefs.savePreference(GarbageConstants.sKey_Frequency, place.getFrequencia());
        savePrefs.savePreference(GarbageConstants.sKey_Interval, place.getIntervalo());

        Toast.makeText(RegisterNotificationActivity.this, R.string.rn_msg_sucess, Toast.LENGTH_SHORT).show();

        Log.i("RegisterActivity", savePrefs.getPreference(GarbageConstants.sKey_Interval));
    }

    public void cancelNotification (View v) {
        //voltar pra tela de listView
    }

    // e registrar a notificação
}
