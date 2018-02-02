package com.example.compaq.weatherapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView date,city,sunny,temp,max,wind,tmtem,tmicn,tmsts,tmwr,tnxtdate,ldate;
    private static final String DEBUG_TAG = "NetworkStatusExample";
    ImageView im ,tmimg;
    private GoogleApiClient googleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemLists();
        findWeather();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                    PERMISSION_ACCESS_COARSE_LOCATION);
        }
        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            switch (requestCode) {
                case PERMISSION_ACCESS_COARSE_LOCATION:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        // All good!
                    } else {
                        Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        }


    }
    void findWeather(){
        String cit ="Mumbai";
        String urlGn = "http://api.openweathermap.org/data/2.5/weather?q=Gurgaon,in&appid=bb4c5a0a85e796ba65d38e21a6ada565&units=metric";
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, urlGn, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray jsonArray = response.getJSONArray("weather");
                    JSONObject object  = jsonArray.getJSONObject(0);
                    String temp1= main_object.getString("temp");
                    String dest= object.getString("description");
                    String city1=response.getString("name");
                    String tempMax=String.valueOf(main_object.getDouble("temp_max"));
                    JSONObject main_objectw = response.getJSONObject("wind");
                    String winD = main_objectw.getString("speed");
                    wind.setText("Wind :"+winD+"k/h");
                    temp.setText(temp1);
                    city.setText(city1);
                    //    max.setText(String.valueOf(tempMax));
                    sunny.setText(dest);
                    temp.setText(temp1);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Calendar c = Calendar.getInstance();
                    String dt =sdf.format(c.getTime());  // Start date
                    System.out.println("Today date"+dt);
                    date.setText(dt);


                    String icn = object.getString("icon");
                    switch (icn){
                        case "01d":  im.setImageResource(R.drawable.d01d); break;
                        case "02d":  im.setImageResource(R.drawable.d02d); break;
                        case "03d":  im.setImageResource(R.drawable.d03d); break;
                        case "04d":  im.setImageResource(R.drawable.d04d); break;
                        case "09d":  im.setImageResource(R.drawable.d09d); break;
                        case "10d":  im.setImageResource(R.drawable.d10d); break;
                        case "11d":  im.setImageResource(R.drawable.d11d); break;
                        case "13d":  im.setImageResource(R.drawable.d13d); break;
                        case "50d":  im.setImageResource(R.drawable.d50d); break;
                        case "01n":  im.setImageResource(R.drawable.n01n); break;
                        case "02n":  im.setImageResource(R.drawable.n02n); break;
                        case "03n":  im.setImageResource(R.drawable.n03n); break;
                        case "04n":  im.setImageResource(R.drawable.n04n); break;
                        case "09n":  im.setImageResource(R.drawable.n09n); break;
                        case "10n":  im.setImageResource(R.drawable.n10n); break;
                        case "11n":  im.setImageResource(R.drawable.n11n); break;
                        case "13n":  im.setImageResource(R.drawable.n13n); break;
                        case "50n":  im.setImageResource(R.drawable.n50n); break;

                    }
 } catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "You Are Offline", Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jor);


    }




    void itemLists(){
        date=(TextView)findViewById(R.id.showdate);
        city=(TextView)findViewById(R.id.showcity);
        sunny=(TextView)findViewById(R.id.wstatus);
        temp=(TextView)findViewById(R.id.showtemp);
        wind=(TextView)findViewById(R.id.showwind);
        im=(ImageView) findViewById(R.id.imageView) ;



    }
}
