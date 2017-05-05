package com.example.anderson.webcoleta;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anderson.webcoleta.util.ColetaNotification;
import com.example.anderson.webcoleta.util.GarbageConstants;
import com.example.anderson.webcoleta.util.SavePreferences;

public class NotificationActivity extends AppCompatActivity {

    ColetaNotification mNotificacaoColeta;
    TextView mTime, mStreet, mFrequency;
    SavePreferences mPreferences;
    SharedPreferences mSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        //Toast.makeText(NotificationActivity.this, "Cancelando notificação", Toast.LENGTH_SHORT).show();
        mNotificacaoColeta = ColetaNotification.getInstance(this);
        mNotificacaoColeta.cancelNotification();
        //Toast.makeText(NotificationActivity.this, "Chamada a notificação cancelada", Toast.LENGTH_SHORT).show();

        mSP = getSharedPreferences(GarbageConstants.sFILE_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        mPreferences = SavePreferences.getInstance(mSP);

        mTime = (TextView) findViewById(R.id.notification_time);
        mStreet = (TextView) findViewById(R.id.notification_street);
        mFrequency = (TextView) findViewById(R.id.notification_frequency);

        mTime.setText(mPreferences.getPreference(GarbageConstants.sKey_Time));
        mStreet.setText(mPreferences.getPreference(GarbageConstants.sKey_Street_Name));
        mFrequency.setText(mPreferences.getPreference(GarbageConstants.sKey_Frequency));
    }
}
