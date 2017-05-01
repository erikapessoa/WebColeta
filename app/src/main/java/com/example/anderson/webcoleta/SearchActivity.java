package com.example.anderson.webcoleta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    Spinner spinnerS;
    private static final String[] itensSetor = new String[] {
            "A", "B", "C"
    };

    Spinner spinnerT;
    private static final String[] itensTurno = new String[] {
            "manhã", "tarde", "noite"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        spinnerT = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapterT = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itensTurno);
        adapterT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerT.setAdapter(adapterT);

        spinnerS = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itensSetor);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerS.setAdapter(adapter);

        spinnerS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(SearchActivity.this, "O item escolhido foi: ", Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });




            }
        }




