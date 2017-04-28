package com.example.anderson.webcoleta;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.anderson.webcoleta.model.GarbagePlace;
import com.example.anderson.webcoleta.util.ListGarbage;
import com.example.anderson.webcoleta.util.Manifest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GarbagePlaceDetailActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_ACCESS_MAP = 1;

    public static final String EXTRA_PLACE_ROUTE = "garbage_place_route";
    private TextView mTextName;
    private TextView mTextDetail;

    private GoogleMap mMap;

    private GarbagePlace mGarbagePlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garbage_place_detail);

        mGarbagePlace = (GarbagePlace) getIntent().getSerializableExtra(ListGarbage.EXTRA_PLACE);

        mTextName = (TextView) findViewById(R.id.txt_garbage_place_name);
        mTextDetail = (TextView) findViewById(R.id.txt_garbage_place_turno);

     //   checkPermission(); //fazer


        if (mGarbagePlace != null){
            mTextName.setText(mGarbagePlace.getProperties().getEndereco());
            mTextDetail.setText(mGarbagePlace.getProperties().getTurno());

        }
    }
/*
    private void setupMap(){



        mMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.mapview)).getMap();

        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setMyLocationEnabled(true);

        double latitude = Double.parseDouble(mPlace.getGeometry().getCoordinates()[1]);
        double longitude = Double.parseDouble(mPlace.getGeometry().getCoordinates()[0]);

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(mPlace.getProperties().getPTurist())
                .snippet(mPlace.getProperties().getPTurist())
        );

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 10);
        mMap.animateCamera(cameraUpdate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_route:
                Intent intent = new Intent(this, TraceRouteActivity.class);
                intent.putExtra(EXTRA_PLACE_ROUTE, mPlace);
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkPermission() {
        if(Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.MAPS_RECEIVE) //classe criada por mim, auto gerada por diego... estranho...
                    == PackageManager.PERMISSION_GRANTED) {
                setupMap();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_ACCESS_MAP);
            }
        } else {
            setupMap();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_MAP: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupMap();
                } else {

                }
                return;
            }
        }
    }
*/
}
