package com.sithagi.foursquareapi.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sithagi.foursquareapi.pojo.Person;
import com.squareup.otto.Subscribe;
import com.sithagi.foursquareapi.R;

public class CardViewActivity extends Activity {

    TextView personName;
    TextView personAge;
    ImageView personPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cardview_activity);
        personName = (TextView)findViewById(R.id.location_name);
        personAge = (TextView)findViewById(R.id.person_age);
        personPhoto = (ImageView)findViewById(R.id.location_photo);

        personName.setText("Emma Wilson");
        personAge.setText("23 years old");
        personPhoto.setImageResource(R.drawable.emma);
    }



    @Subscribe
    public void getMessage(Person p) {
        Toast.makeText(this, p.name, Toast.LENGTH_SHORT).show();
    }
}
