package com.example.anderson.webcoleta;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anderson.webcoleta.model.GarbagePlace;
import com.example.anderson.webcoleta.util.GarbageConstants;
import com.example.anderson.webcoleta.util.PermissionUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class GarbagePlaceDetailActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener{//} implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, ActivityCompat.OnRequestPermissionsResultCallback{


    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;


    private TextView mTextStreet;
    private TextView mTextInterval;
    private TextView mTextFrequency;
    private GarbagePlace mGarbagePlace;
    private GoogleMap mMap;

    //private boolean mPermissionDenied = false;
    //private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garbage_place_detail);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapview);
        mapFragment.getMapAsync(this);

        mGarbagePlace = (GarbagePlace) getIntent().getSerializableExtra(GarbageConstants.sEXTRA_PLACE);

        mTextStreet = (TextView) findViewById(R.id.txt_garbage_place_street);
        mTextInterval = (TextView) findViewById(R.id.txt_garbage_place_interval);
        mTextFrequency = (TextView) findViewById(R.id.txt_garbage_place_frequency);

        if (mGarbagePlace != null){
            mTextStreet.setText(mGarbagePlace.getEndereco());
            mTextInterval.setText(mGarbagePlace.getIntervalo());
            mTextFrequency.setText(mGarbagePlace.getFrequencia());
        }

        /* Verificar se este ponto de coleta está registrado para receber notificações, caso esteja,
        exibir esta infrmação e permitir que ele descadastre o ponto. Caso não esteja, permitir que
                ele cadastre o ponto. */


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //      mPerth = mMap.addMarker(new MarkerOptions().title("Perth"));
        //      mPerth.setTag(0);
        // Add a marker in Sydney and move the camera
        mMap.setOnMyLocationButtonClickListener(this);
        checkPermission();

        try {
            changeCamera(mMap);
        } catch (Exception e) {
            Log.i("Exceção: ", e.toString());
        }


    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }


    public void changeCamera(GoogleMap mMap) throws Exception
    {

        Address addresses;
        Geocoder geocoder = new Geocoder(this);
        addresses = geocoder.getFromLocationName(mTextStreet.getText().toString()+" Recife, PE, Brasil", 1).get(0);

        double latitude= addresses.getLatitude();
        double longitude= addresses.getLongitude();

        LatLng loc = new LatLng(latitude, longitude);

        mMap.addMarker(new MarkerOptions()
                .position(loc)
                .title(mTextStreet.getText().toString()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 10.0f));

        throw new Exception ("Exceção");

    }

    private void checkPermission() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    android.Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);



        }
    }




    public void registerGarbagePlaceNotification(View v) {

        Intent it = new Intent(GarbagePlaceDetailActivity.this, RegisterNotificationActivity.class);
        it.putExtra(GarbageConstants.sEXTRA_PLACE, mGarbagePlace);

        startActivity(it);
    }

    public void cancelGarbagePlaceNotification(View v) {

        Intent it = new Intent(GarbagePlaceDetailActivity.this, GarbagePlaceListActivity.class);
        startActivity(it);
    }

}


//comentar daqui até o final

/*
    private void setupMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapview);
        mapFragment.getMapAsync(this);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setMyLocationEnabled(true);
      //  double latitude = Double.parseDouble(mPlace.getGeometry().getCoordinates()[1]);
      //  double longitude = Double.parseDouble(mPlace.getGeometry().getCoordinates()[0]);
        Geocoder geocoder = new Geocoder(this);
        List<Address> addresses = null;
        try {
            // Find a maximum of 3 locations with the name Kyoto
            addresses = geocoder.getFromLocationName(mTextName.getText().toString() + "Recife, Pernambuco", 3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses != null) {
            for (Address loc : addresses) {
                MarkerOptions opts = new MarkerOptions()
                        .position(new LatLng(loc.getLatitude(), loc.getLongitude()))
                        .title(loc.getAddressLine(0));
                mMap.addMarker(opts);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(loc.getLatitude(), loc.getLongitude()), 10);
                mMap.animateCamera(cameraUpdate);
            }
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //      mPerth = mMap.addMarker(new MarkerOptions().title("Perth"));
        //      mPerth.setTag(0);
        // Add a marker in Sydney and move the camera
        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();
    }
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    android.Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
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
    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }
    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }
*/