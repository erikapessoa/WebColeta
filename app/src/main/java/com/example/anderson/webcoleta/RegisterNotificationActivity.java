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

public class RegisterNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_notification);
    }

    //cadastrar as preferências (tempo de antecedência do aviso
    public void savePreferences(View v) {

        Intent it;
        RadioGroup mRadioGroup = null;
        GarbagePlace place = null;
        RadioButton radio = null;
        int idRadioSelected, radioId = 0;
        SharedPreferences sp = null;
        SavePreferences savePrefs = null;
        String time = "";
        Calendar calendar = null;
        it= getIntent();
        place = (GarbagePlace) it.getExtras().get(GarbageConstants.sEXTRA_PLACE);
        mRadioGroup = (RadioGroup)findViewById(R.id.timeOptions);
        idRadioSelected = mRadioGroup.getCheckedRadioButtonId();
        radio = (RadioButton)findViewById(idRadioSelected);
        //radioText = radio.getText().toString();
        radioId = mRadioGroup.indexOfChild(radio);

        if(radioId == 0)
            time = String.valueOf(GarbageConstants.sTime_1);
        else if(radioId == 1)
            time = String.valueOf(GarbageConstants.sTime_2);
        else time = String.valueOf(GarbageConstants.sTime_3);

        //Salvando as preferências no sharedPreferences
        sp = getSharedPreferences(GarbageConstants.sFILE_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        savePrefs = SavePreferences.getInstance(sp);
        savePrefs.savePreference(GarbageConstants.sKey_Time, time);
        savePrefs.savePreference(GarbageConstants.sKey_Street_Name, place.getStreet());
        savePrefs.savePreference(GarbageConstants.sKey_Frequency, place.getFrequency());
        savePrefs.savePreference(GarbageConstants.sKey_Interval, place.getInterval());

        //registrando a notificação, horário que a notificação será criada
        calendar = createDate(place, time);
        notificationSchedule(calendar);

        it.putExtra("RESULT_OK", 1);
        setResult(1,it);
        finish();

        //Log.i("RegisterActivity", savePrefs.getPreference(GarbageConstants.sKey_Interval));
    }

    public void cancelNotification (View v) {
        //voltar pra tela de listView
        Intent it = new Intent(RegisterNotificationActivity.this, GarbagePlaceListActivity.class);
        startActivity(it);
    }

    private void notificationSchedule(Calendar calendar) {


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

        // Regista o alerta no sistema. Seta o intervalo (por eqt t setando para 15 minutos para poder testar
        //depois precisa ver a frequencia para fazer o aviso
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);


    }

    public Calendar createDate(GarbagePlace place, String timeBefore) {

        String[] hourMinute;
        Calendar calendar;
        int tb, hour, minute;
        String interval;
        String frequency;

        calendar =  Calendar.getInstance();
        tb = -Integer.parseInt(timeBefore);
        interval = place.getInterval();
        //hourMinute = interval.split(":");
        frequency = place.getFrequency(); //ainda desconsiderando esta informação para fazer o agendamento

        hour = Integer.parseInt(interval.substring(0,2));
        minute = Integer.parseInt(interval.substring(3,5));

        Log.i("RegisterActivity", "antecedencia: " + tb);

        //para que o alarm comece a partir do dia seguinte
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        //para que o alarme avise com a antecedência escolhida pelo usuário
        calendar.add(Calendar.MINUTE, tb);

        return calendar;

    }



}
