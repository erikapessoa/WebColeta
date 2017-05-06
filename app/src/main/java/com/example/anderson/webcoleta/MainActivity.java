package com.example.anderson.webcoleta;

import android.content.Intent;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void abrirMenu(View v) {

        Intent it = new Intent(this, MenuActivity.class);
        startActivity(it);

    }


}
