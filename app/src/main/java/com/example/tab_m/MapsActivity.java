    package com.example.tab_m;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.FragmentActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

    public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

        private String TAG="raw";
        private Button nextPage;




        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
nextPage=findViewById(R.id.nextPage);



nextPage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(MapsActivity.this,DisplayActivity.class));
    }
});

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        }


        private void loadNearByPlaces()
        {
            String type = "hospital";

            StringBuilder googlePlacesUrl =
                    new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
            googlePlacesUrl.append("location=").append("28.7041").append(",").append("77.1025");
            googlePlacesUrl.append("&radius=").append("10000");
            googlePlacesUrl.append("&types=").append(type);
            googlePlacesUrl.append("&sensor=true");
            googlePlacesUrl.append("&key=" + "AIzaSyBekfvCkgtCxFfOdW78pByXkEf-9XscPm0");





            RequestQueue requestQueue =  Volley.newRequestQueue(MapsActivity.this);

            // Initialize a new JsonObjectRequest instance
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    googlePlacesUrl.toString(),
                    null,
                    new com.android.volley.Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {

                                // location.setText(response.get());
                                Log.i(TAG, "onResponse: Result= " + response.toString());
                                //parseLocationResult(response);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

            requestQueue.add(jsonObjectRequest);
        }





    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Noida and move the camera
        LatLng noida = new LatLng(28.5971, 77.3482);
        mMap.addMarker(new MarkerOptions().position(noida).title("Marker in noida 22"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(noida));
        loadNearByPlaces();

    }
    }
