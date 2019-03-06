package com.example.picturemarker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.location.Location;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {


    private static final int TAKE_PICTURE = 1;
    private GoogleMap mMap;
    private FloatingActionButton button;
    FusedLocationProviderClient fusedLocationProviderClient;
    private SettingsClient settingsClient;
    ImageView test;


    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        settingsClient = LocationServices.getSettingsClient(this);



        test = findViewById(R.id.imageView);
        button = findViewById(R.id.picture);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pictureIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE
                );
                if(pictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(pictureIntent,
                            TAKE_PICTURE);
                }
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Test for LARAMIE
        mMap = googleMap;
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Display image again.
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PICTURE &&
                resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {

                // Get image
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                imageBitmap = Rotate(imageBitmap, 90);
                test.setImageBitmap(imageBitmap);



                // Get location
                //LatLng current = new LatLng(fusedLocationProviderClient.getLastLocation().getResult().getLongitude(), fusedLocationProviderClient.getLastLocation().getResult().getLatitude());
                //mMap.addMarker(new MarkerOptions().position(current).icon(BitmapDescriptorFactory.fromBitmap(imageBitmap)));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(current));

            }
        }
    }

    public Bitmap Rotate(Bitmap original, float degree){
        Bitmap bOutput;
        float degrees = degree;
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees);
        bOutput = Bitmap.createBitmap(original, 0, 0, original.getWidth(), original.getHeight(), matrix, true);
        return bOutput;
    }
}
