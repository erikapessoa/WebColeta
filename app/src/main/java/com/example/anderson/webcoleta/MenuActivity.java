package com.example.anderson.webcoleta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void listarPontosColeta(View v) {

        Intent it = new Intent(this, GarbagePlaceListActivity.class);
        startActivity(it);
    }

    public void buscarPontosColeta(View v) {

        Intent it = new Intent(this, GarbagePlaceDetailActivity.class);
        startActivity(it);
    }

}
