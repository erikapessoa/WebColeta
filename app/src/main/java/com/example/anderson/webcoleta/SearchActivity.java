package com.example.anderson.webcoleta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anderson.webcoleta.adapter.GarbagePlacesAdapter;
import com.example.anderson.webcoleta.model.GarbagePlace;
import com.example.anderson.webcoleta.util.GarbageConstants;
import com.example.anderson.webcoleta.util.WebService;

public class SearchActivity extends AppCompatActivity {

    private GarbagePlacesAdapter mGarbagePlacesAdapter;
    private ListView mListGarbagePlaces;
    private GarbagePlace[] mGarbagePlaces;
    private ProgressDialog mProgress;
    private String letra = "";


    Spinner spinnerS;
    private static final String[] itensSetor = new String[] {
            "Escolha um setor: ", "A", "B", "C"
    };

    Spinner spinnerT;
    private static final String[] itensTurno = new String[] {
            "Escolha um turno","manhã", "tarde", "noturno"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        TextView t = (TextView)findViewById(R.id.textView1);
        t.requestFocus();

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


                    if(position == 0) {
                        //Toast.makeText(SearchActivity.this, "Escolha um elemento válido: ", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    letra = ((AppCompatTextView) view).getText().toString();

                        setContentView(R.layout.activity_garbage_place_list);
                        mListGarbagePlaces = (ListView) findViewById(R.id.list_garbage_places);
                        mListGarbagePlaces.setEmptyView(findViewById(android.R.id.empty));
                        new SyncDataTask().execute();


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    if(position == 0) {
                        //Toast.makeText(SearchActivity.this, "Escolha um elemento válido: ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    letra = ((AppCompatTextView) view).getText().toString();
                    if (letra != null) {
                        setContentView(R.layout.activity_garbage_place_list);
                        mListGarbagePlaces = (ListView) findViewById(R.id.list_garbage_places);
                        mListGarbagePlaces.setEmptyView(findViewById(android.R.id.empty));
                        new SyncDataTask().execute();
                    }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


    }


    private void loadData() {

        WebService service = new WebService();
        mGarbagePlaces = service.readSetorGarbagePlaces(letra);

    }

    private void setupListView() {
        //init adapter


        mGarbagePlacesAdapter = new GarbagePlacesAdapter(this, mGarbagePlaces);

        mListGarbagePlaces.setAdapter(mGarbagePlacesAdapter);

        addListFooter(); //
        addListHeared();//

        mListGarbagePlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GarbagePlace place = (GarbagePlace) adapterView.getItemAtPosition(i);

                Intent it = new Intent(SearchActivity.this, GarbagePlaceDetailActivity.class);
                it.putExtra(GarbageConstants.sEXTRA_PLACE, place);

                startActivity(it);
            }
        });


        // para setar todos os GarbagePlaces serem true para autoCompTextView
        for(GarbagePlace place : mGarbagePlaces) {
            place.setAutoComp(true);
        }

        // Não pode ficar no OnCreat pq aqui é onde de fato a lista está populada
        ArrayAdapter<GarbagePlace> adapterBusca = new ArrayAdapter<GarbagePlace>
                (this, android.R.layout.simple_dropdown_item_1line, mGarbagePlaces);
        AutoCompleteTextView busca = (AutoCompleteTextView) findViewById(R.id.pesquisa);
        busca.setAdapter(adapterBusca);


        // para pegar o item escolhido depois da filtro/busca
        busca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GarbagePlace place = (GarbagePlace) adapterView.getItemAtPosition(i);

                Intent it = new Intent(SearchActivity.this, GarbagePlaceDetailActivity.class);
                it.putExtra(GarbageConstants.sEXTRA_PLACE, place);
                startActivity(it);
                //new GarbagePlaceListActivity.SyncDataTask().execute();
            }
        });

    }

    private void addListFooter() {
        final int PADDING = 10;
        TextView txtHeader = new TextView(this);
        txtHeader.setBackgroundColor(Color.parseColor("#66CDAA"));
        txtHeader.setTextColor(Color.WHITE);
        txtHeader.setText(R.string.list_header_text);
        txtHeader.setPadding(PADDING, PADDING, 0, PADDING);
        mListGarbagePlaces.addHeaderView(txtHeader);

    }

    private void addListHeared() {
        final int PADDING = 10;
        final TextView txtFooter = new TextView(this);
        txtFooter.setText(getResources().getQuantityString(
                R.plurals.list_header_plural,
                mGarbagePlacesAdapter.getCount(),
                mGarbagePlacesAdapter.getCount()));
        txtFooter.setBackgroundColor(Color.parseColor("#66CDAA"));
        txtFooter.setTextColor(Color.WHITE);
        txtFooter.setGravity(Gravity.LEFT);
        txtFooter.setPadding(0, PADDING, PADDING, PADDING);
        mListGarbagePlaces.addFooterView(txtFooter);

    }



    private class SyncDataTask extends AsyncTask<Object, Object, Object> {

        @Override
        protected void onPreExecute() {
            //Show progress while downloading data
            mProgress = new ProgressDialog(SearchActivity.this);
            mProgress.setTitle(R.string.dialog_wait_title);
            mProgress.setIcon(getDrawable(R.drawable.garbage_icon));
            mProgress.setMessage(getString(R.string.dialog_wait_message));
            mProgress.show();

        }

        @Override
        protected Object doInBackground(Object... params) {
            //load data in backgroud
            loadData();
            return null;
        }


        @Override
        protected void onPostExecute(Object result) {
            //Dismiss project and setup List Adapter
            if (mProgress != null && mProgress.isShowing()) {
                try {
                    mProgress.dismiss();
                } catch (Exception e) {

                }
            }
            setupListView();

        }
    }


}