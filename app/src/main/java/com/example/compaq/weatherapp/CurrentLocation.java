package com.example.compaq.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CurrentLocation extends AppCompatActivity {

    TextView state,city,sublocal,landmark,postalcode,country;


    MainActivity mainActivity;
    GetterSetterClass gts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);
        state = (TextView)findViewById(R.id.showstate);
        city = (TextView)findViewById(R.id.showcity);
        sublocal = (TextView)findViewById(R.id.showsublocal);
        landmark = (TextView)findViewById(R.id.showlandmark);
        postalcode = (TextView)findViewById(R.id.postalcode);
        country = (TextView)findViewById(R.id.showcontry);
        state.setText(gts.getState());
        city.setText(gts.getCity());
        sublocal.setText(gts.getSublocal());
        landmark.setText(gts.getLandmark());
        postalcode.setText(gts.getPostalcode());
        country.setText(gts.getCountry());



    }






}
