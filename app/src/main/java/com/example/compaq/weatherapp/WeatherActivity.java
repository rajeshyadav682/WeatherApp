package com.example.compaq.weatherapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WeatherActivity extends AppCompatActivity  {
    private TextView date,city,sunny,temp,max,wind,tmtem,tmicn,tmsts,tmwr,tnxtdate,ldate;
    private static final String DEBUG_TAG = "NetworkStatusExample";
    ImageView im ,tmimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemLists();
        findWeather();



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
                    tmimg.setImageResource(R.drawable.n50n);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Calendar c = Calendar.getInstance();
                    String dt =sdf.format(c.getTime());  // Start date
                    System.out.println("Today date"+dt);
                    date.setText(dt);
                    c.add(Calendar.DATE, 2);  // number of days to add
                    dt = sdf.format(c.getTime());  //
                    System.out.println(dt);
                    System.out.println("next date"+dt);
                    tnxtdate.setText(dt);
                    c.add(Calendar.DATE, 1);  // number of days to add
                    dt = sdf.format(c.getTime());  //
                    System.out.println(dt);
                    System.out.println("next of next date"+dt);
                    ldate.setText(dt);


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


               //     JSONObject dayforcast = response.getJSONObject("list.main");
                    JSONArray jsonArrayforecast = response.getJSONArray("list.Weather");
                    JSONObject jobj = jsonArrayforecast.getJSONObject(9);
                    JSONArray jso = response.getJSONArray("weather");
                    String tomotemp= forecastObject.getString("temp");
                    String tomodest= dayforcast.getString("description");
                    tmtem.setText(tomotemp);
                    tmsts.setText(tomodest);




                } catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WeatherActivity.this, "You Are Offline", Toast.LENGTH_SHORT).show();

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
       /* tmtem=(TextView)findViewById(R.id.tmtemp);
        tmsts=(TextView)findViewById(R.id.showStatus);
        tmimg =(ImageView)findViewById(R.id.imageView2);
        tmwr =(TextView) findViewById(R.id.tmwr);
        tnxtdate=(TextView)findViewById(R.id.tnxtdate) ;
        ldate=(TextView)findViewById(R.id.ldate) ;*/


    }



}
