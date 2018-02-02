package com.example.compaq.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements LocationListener {
    private TextView date, city, sunny, temp, max, wind, add, tmtem, tmicn, tmsts, tmwr, tnxtdate, ldate;
    ImageView im, tmimg;
    LocationManager locationManager;
    GetterSetterClass gts;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.Relativelayout);
        getLocation();
        itemLists();
        findWeather();
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, "Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            Toast.makeText(this, "\n" + addresses.get(0).getAddressLine(0) + ", " +
                    addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2), Toast.LENGTH_SHORT).show();

            gts.setState(addresses.get(0).getAdminArea());
            gts.setCity(addresses.get(0).getLocality());
            gts.setSublocal(addresses.get(0).getSubLocality());
            gts.setLandmark(addresses.get(0).getThoroughfare());
            gts.setPostalcode(addresses.get(0).getPostalCode());

            add.setText(gts.getState());

        } catch (Exception e) {

        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.refresh:
                onRestart();
                return true;

            case R.id.showloc:
                startActivity(new Intent(getApplicationContext(), CurrentLocation.class));
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    protected void onRestart() {
        super.onRestart();
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
                        case "50d":  im.setImageResource(R.drawable.d50d);
                       //     relativeLayout.setBackgroundResource(R.drawable.haze);
                              break;
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
        add=(TextView)findViewById(R.id.address);



    }


}
