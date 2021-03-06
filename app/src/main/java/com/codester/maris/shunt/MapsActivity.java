package com.codester.maris.shunt;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback ,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

private GoogleMap mMap;
        GoogleApiClient mGoogleApiClient;
        Location mLastLocation;
        Marker mCurrLocationMarker;
        LocationRequest mLocationRequest;

        ArrayList<String> rayz = new ArrayList<>();

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        checkLocationPermission();
        }//end if statement

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        }//end onCreate method

public void onMapSearch(View view) {

        String location ;
        List<Address> addressList = null;



        ArrayList<String> rayz = new ArrayList<>();
        rayz.add("Aviva Stadium");
        rayz.add("Grafton Street");
        rayz.add("Henry Street");
        rayz.add("Croke Park");
        rayz.add("Temple Bar");
        rayz.add("Saint Patricks Cathedral");
        rayz.add("St. Micheals Church");
        rayz.add("Merrion Square");
        rayz.add("Trinity College");
        rayz.add("Dublin Castle");
        rayz.add("Molly Malone Statue");



        int a = rayz.size();
        for(int i=0; i<a; i++) {//loop through the list and keep adding markers which will give notification
        //now that player is in near landmark and would get points
        location = rayz.get(i);
        location = location + " dublin";

        if (location != null || !location.equals("")) {
        Geocoder geocoder = new Geocoder(this);
        try {
        addressList = geocoder.getFromLocationName(location, 1);

        } catch (IOException e) {
        e.printStackTrace();
        }
        Address address = addressList.get(0);
        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title(location));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }//end if
        }//end for
        }

@Override
public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (ContextCompat.checkSelfPermission(this,
        Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) {
        buildGoogleApiClient();
        mMap.setMyLocationEnabled(true);
        }
        }
        else {
        buildGoogleApiClient();
        mMap.setMyLocationEnabled(true);
        }
        }//end onMapReady method

protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .addApi(LocationServices.API)
        .build();
        mGoogleApiClient.connect();
        }

@Override
public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
        Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }//end if statement

        }//end onConnected method

@Override
public void onConnectionSuspended(int i) {

        }

@Override
public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
        mCurrLocationMarker.remove();
        }//end if statement

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Starting Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        //stop location updates
        if (mGoogleApiClient != null) {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }//end if statement

        }//end onLocationChanged method

@Override
public void onConnectionFailed(ConnectionResult connectionResult) {

        }

public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {

        // Asking user if explanation is needed
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
        Manifest.permission.ACCESS_FINE_LOCATION)) {

        // Show an explanation to the user *asynchronously* -- don't block
        // this thread waiting for the user's response! After the user
        // sees the explanation, try again to request the permission.

        //Prompt the user once explanation has been shown
        ActivityCompat.requestPermissions(this,
        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
        MY_PERMISSIONS_REQUEST_LOCATION);


        } else {
        // No explanation needed, we can request the permission.
        ActivityCompat.requestPermissions(this,
        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
        MY_PERMISSIONS_REQUEST_LOCATION);
        }
        return false;
        } else {
        return true;
        }
        }//end checkLocationPermission method

@Override
public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        switch (requestCode) {
        case MY_PERMISSIONS_REQUEST_LOCATION: {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        // permission was granted. Do the
        // contacts-related task you need to do.
        if (ContextCompat.checkSelfPermission(this,
        Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) {

        if (mGoogleApiClient == null) {
        buildGoogleApiClient();
        }
        mMap.setMyLocationEnabled(true);
        }

        } else {

        // Permission denied, Disable the functionality that depends on this permission.
        Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
        }//end main if
        return;
        }//end switch statement

        // other 'case' lines to check for other permissions this app might request.
        // You can add here other case statements according to your requirement.
        }
        }//end onRequestPermissionsResult method

        }//end MapsActivity class
