package com.example.tab_m;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DisplayActivity extends AppCompatActivity  {
    private TextView  location1 ,senser,type ,radius,all;
    private String TAG = "raw";
    private ImageView img;
    private EditText areaType;
    private Button search;
    private  String temp_area;
    ArrayList<HashMap<String, String>> location;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        init();


       search.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DataExtrate();
               Log.i(TAG, "onClick: "+temp_area);
           }
       });



    }
private void init(){
location1=findViewById(R.id.location);
senser=findViewById(R.id.senser);
type=findViewById(R.id.type);
radius=findViewById(R.id.radius);
all=findViewById(R.id.all);
img=findViewById(R.id.img);
areaType=findViewById(R.id.area_type);
search=findViewById(R.id.btn_area_type);
}
private void DataExtrate(){
     temp_area=areaType.getText().toString();


    StringBuilder googlePlacesUrl =
            new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
    googlePlacesUrl.append("location=").append("28.7041").append(",").append("77.1025");
    googlePlacesUrl.append("&radius=").append("10000");
    googlePlacesUrl.append("&types=").append(temp_area);
    googlePlacesUrl.append("&sensor=true");
    googlePlacesUrl.append("&key=" + "AIzaSyBekfvCkgtCxFfOdW78pByXkEf-9XscPm0");
//        try {
//            JSONObject jObject = new JSONObject("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
//            String jj = jObject.getString("status");
//
//            JSONArray data = jObject.getJSONArray("results");
//
//            ArrayList<HashMap<String, String>> location = new ArrayList<HashMap<String, String>>();
//            HashMap<String, String> map;
//
//            for(int i = 0; i < data.length(); i++){
//                JSONObject c = data.getJSONObject(i);
//                map = new HashMap<String, String>();
//
//                JSONObject geometry = c.getJSONObject("geometry");
//                JSONObject name = c.getJSONObject("name");
//
//                JSONObject photos = c.getJSONObject("photos");
//                JSONObject locate = c.getJSONObject("location");
//                String lat = c.getString("lat");
//                String lng = c.getString("lng");
//                Log.i("Data", lat+"--"+lng);
//
//                location1.setText(name.toString());
//                img.setImageIcon(Icon.createWithContentUri(photos.toString()));
//                map.put("LocationID", c.getString("id"));
//                map.put("placeId",c.getString("place_id"));
//                location.add(map);
//
//
//            }
//
//        } catch (JSONException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    final RequestQueue requestQueue =  Volley.newRequestQueue(DisplayActivity.this);
    // Initialize a new JsonObjectRequest instance
    final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.GET,
            googlePlacesUrl.toString(),
            null,
            new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    all.setText(response.toString());
                    for(int i = 0; i < response.length(); i++){
                        try {
                            JSONArray data=response.getJSONArray("Result");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
//                        try {
//                            location1.setText(response.getString("location"));
//                            senser.setText(response.getString("type"));
//                            all.setText(response.toString());
//                            Log.i(TAG, "onResponse: Types= " + response.getLong("type"));
//                            Log.i(TAG, "onResponse: Result= " + response.toString());
//                            //parseLocationResult(response);
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
                }
            },
            new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
    requestQueue.add(jsonObjectRequest);
}

}
