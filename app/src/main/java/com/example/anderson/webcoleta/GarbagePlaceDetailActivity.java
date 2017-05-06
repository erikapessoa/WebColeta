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

    Button register;
    Button cancelRegister;

    TextView registered;

    public static int RESULT_INTENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garbage_place_detail);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapview);
        mapFragment.getMapAsync(this);

        register= (Button)findViewById(R.id.register_garbage_place_button);
        cancelRegister = (Button)findViewById(R.id.cancel_garbage_place_button);
        registered = (TextView)findViewById(R.id.registered);
        mGarbagePlace = (GarbagePlace) getIntent().getSerializableExtra(GarbageConstants.sEXTRA_PLACE);

        mTextStreet = (TextView) findViewById(R.id.txt_garbage_place_street);
        mTextInterval = (TextView) findViewById(R.id.txt_garbage_place_interval);
        mTextFrequency = (TextView) findViewById(R.id.txt_garbage_place_frequency);

        if (mGarbagePlace != null){
            mTextStreet.setText(mGarbagePlace.getStreet());
            mTextInterval.setText(mGarbagePlace.getInterval());
            mTextFrequency.setText(mGarbagePlace.getFrequency());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1)
        {
            register.setEnabled(false);
            register.setVisibility(View.GONE);
            cancelRegister.setEnabled(false);
            cancelRegister.setVisibility(View.GONE);
            registered.setText("Você está registrado nesta coleta!");


        }

       /* if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Intent intent = new Intent(this, PictureActivity.class);
            intent.putExtras(data.getExtras());
            startActivity(intent);*/
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

        startActivityForResult(it, RESULT_INTENT);
    }

    public void cancelGarbagePlaceNotification(View v) {

        Intent it = new Intent(GarbagePlaceDetailActivity.this, GarbagePlaceListActivity.class);
        startActivity(it);
    }

}


