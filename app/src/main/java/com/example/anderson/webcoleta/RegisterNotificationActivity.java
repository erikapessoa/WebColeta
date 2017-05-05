package com.example.anderson.webcoleta;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
import com.example.anderson.webcoleta.util.NotificationReceiver;
import com.example.anderson.webcoleta.util.SavePreferences;

import java.util.Calendar;
import java.util.Date;

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
        Calendar calendar = null;

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

        //registrando a notificação
        //Data que a notificação será criada(por eqt um teste)
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        calendar.set(Calendar.HOUR, 15);
        calendar.set(Calendar.MINUTE, 30);

        notificationScheule(calendar);

        //mensagem para o usuário
        Toast.makeText(RegisterNotificationActivity.this, R.string.rn_msg_sucess, Toast.LENGTH_SHORT).show();

        //Log.i("RegisterActivity", savePrefs.getPreference(GarbageConstants.sKey_Interval));
    }

    public void cancelNotification (View v) {
        //voltar pra tela de listView
        Intent it = new Intent(RegisterNotificationActivity.this, GarbagePlaceListActivity.class);
        startActivity(it);
    }

    private void notificationScheule(Calendar calendar) {


        AlarmManager alarmManager;
        Intent intent;
        PendingIntent pendingIntent;

        // Obtém um alarm manager
        alarmManager = (AlarmManager) getApplicationContext().getSystemService(getBaseContext().ALARM_SERVICE);

        // Prepare the intent which should be launched at the date
        intent = new Intent(this, NotificationReceiver.class);

        // Obtém o pending intent
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), GarbageConstants.sID_NOTIFICATION,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Regista o alerta no sistema.
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }



}
