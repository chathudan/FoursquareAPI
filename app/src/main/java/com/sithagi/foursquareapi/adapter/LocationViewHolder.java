package com.sithagi.foursquareapi.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sithagi.foursquareapi.R;

/**
 * @author Chathura Wijesinghe <cdanasiri@gmail.com> on 5/15/15.
 */
public class LocationViewHolder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView locationName;
    TextView locationPhone;
    ImageView locationPhoto;
    TextView locationAddress;

    LocationViewHolder(View itemView) {
        super(itemView);

        cv = (CardView) itemView.findViewById(R.id.cv);
        locationName = (TextView) itemView.findViewById(R.id.location_name);
        locationPhone = (TextView) itemView.findViewById(R.id.location_phone);
        locationPhoto = (ImageView) itemView.findViewById(R.id.location_photo);
        locationAddress = (TextView) itemView.findViewById(R.id.location_address);
    }

}
