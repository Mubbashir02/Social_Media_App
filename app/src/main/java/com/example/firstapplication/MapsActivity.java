package com.example.firstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.SphericalUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    LatLng latLng;
    LatLng startPoint;
    LatLng endPoint;
    Location currentLocation;
    TextView source_input;
    TextView dest_address_input;
    TextView disance_value;
    TextView dest_input;
    FusedLocationProviderClient fusedLocationProviderClient;

    private static final int REQUEST_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        source_input = (TextView) findViewById(R.id.source_value);
        dest_input = (TextView) findViewById(R.id.dest_value);
        dest_address_input = (TextView) findViewById(R.id.dest_address_value);
        disance_value = (TextView) findViewById(R.id.distance_value);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLocation();


    }

    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    Toast.makeText(getApplicationContext(), currentLocation.getLatitude() + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    assert supportMapFragment != null;
                    supportMapFragment.getMapAsync(MapsActivity.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        double[] latitude_no = {24.860587636070388};
        double[] longitude_no = {67.0698779669637};


        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        String convert_string_current_lat = new Double(currentLocation.getLatitude()).toString();
        String convert_string_current_long = new Double(currentLocation.getLongitude()).toString();
        source_input.setText(convert_string_current_lat + " , " + convert_string_current_long);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("I am here!");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        googleMap.addMarker(markerOptions);


        LatLng latLng_maju = new LatLng(latitude_no[0], longitude_no[0]);
        MarkerOptions markerOptions_maju = new MarkerOptions().position(latLng_maju);
        googleMap.addMarker(markerOptions_maju);




        // The drawable to use for the circle
        GradientDrawable d = new GradientDrawable();
        d.setShape(GradientDrawable.OVAL);
        d.setSize(500,500);
        d.setColor(Color.rgb(144, 154, 176));

        d.setStroke(5, Color.TRANSPARENT);

        Bitmap bitmap = Bitmap.createBitmap(d.getIntrinsicWidth()
                , d.getIntrinsicHeight()
                , Bitmap.Config.ARGB_8888);

        // Convert the drawable to bitmap
        Canvas canvas = new Canvas(bitmap);
        d.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        d.draw(canvas);

        // Radius of the circle
        final int radius = 100;

        // Add the circle to the map
        final GroundOverlay circle = googleMap.addGroundOverlay(new GroundOverlayOptions()
                .position(latLng, 2 * radius).image(BitmapDescriptorFactory.fromBitmap(bitmap)));

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setIntValues(0, radius);
        valueAnimator.setDuration(3000);
        valueAnimator.setEvaluator(new IntEvaluator());
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                circle.setDimensions(animatedFraction * radius * 2);
            }
        });

        valueAnimator.start();


        Geocoder geocoder = new Geocoder(this);
        try {
            ArrayList<Address> arrAdr = (ArrayList<Address>) geocoder.getFromLocation(latitude_no[0], longitude_no[0],2);
            dest_address_input.setText( arrAdr.get(0).getAddressLine(0));
            Toast.makeText(this, arrAdr.get(0).getAddressLine(0), Toast.LENGTH_SHORT).show();

            Log.d("Locate", arrAdr.get(0).getAddressLine(0));

        }
        catch (Exception e){
            e.printStackTrace();
        }
        String convert_string_current_lat_maju = new Double(latitude_no[0]).toString();
        String convert_string_current_long_maju = new Double(longitude_no[0]).toString();
        dest_input.setText(convert_string_current_lat_maju + " , " + convert_string_current_long_maju);
        startPoint = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        endPoint = new LatLng(latitude_no[0], longitude_no[0]);
        googleMap.addPolyline(new PolylineOptions().add(startPoint, endPoint).width(7).color(Color.MAGENTA));

        Double distance = SphericalUtil.computeDistanceBetween(latLng, latLng_maju);
        disance_value.setText(String.format("%.2f", distance / 1000) + "Km");

        Toast.makeText(this, "Distance is \n " + String.format("%.2f", distance / 1000) + "km", Toast.LENGTH_LONG).show();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLocation();
                }
                break;
        }
    }
}