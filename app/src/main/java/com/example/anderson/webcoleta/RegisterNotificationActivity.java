package com.example.anderson.webcoleta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.anderson.webcoleta.model.GarbagePlace;
import com.example.anderson.webcoleta.util.GarbageConstants;

public class RegisterNotificationActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_notification);
    }

    //cadastrar as preferências (tempo de antecedência do aviso e registrar a notificação
    public void saveNotification(View v) {

        RadioGroup mRadioGroup;
        GarbagePlace place;
        RadioButton radio;
        int idRadioSelected;
        String radioText;

        place = (GarbagePlace) getIntent().getExtras().get(GarbageConstants.sEXTRA_PLACE);
        mRadioGroup = (RadioGroup)findViewById(R.id.timeOptions);
        idRadioSelected = mRadioGroup.getCheckedRadioButtonId();
        radio = (RadioButton)findViewById(idRadioSelected);
        radioText = radio.getText().toString();

        Toast.makeText(RegisterNotificationActivity.this, radioText, Toast.LENGTH_SHORT).show();
    }

    public void cancelNotification (View v) {
        //voltar pra tela de listView
    }
}
