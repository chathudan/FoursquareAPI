package com.sithagi.foursquareapi.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sithagi.foursquareapi.adapter.FSAdapter;
import com.sithagi.foursquareapi.pojo.VenueDetails;
import com.squareup.otto.Subscribe;
import com.sithagi.foursquareapi.R;

import java.util.List;

public class RecyclerViewActivity extends Activity implements FSAdapter.OnAdapterItemClickListener {

    private List<VenueDetails> venues;
    private RecyclerView rv;

    FSAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_activity);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

//        rv.addOnItemTouchListener(
//                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        // TODO Handle item click
//
//                        if (view instanceof CardView) {
//                            TextView person_name = (TextView) view.findViewById(R.id.person_name);
//                            Toast.makeText(RecyclerViewActivity.this, person_name.getText().toString(), Toast.LENGTH_SHORT).show();
//
//                            mAdapter.setFilter("W");
//                            if(person_name.getText().toString().contains("Lil"))
//                                mAdapter.flushFilter();
//                        }
//                    }
//                })
//        );

    }


    private void initializeAdapter(){
        mAdapter = new FSAdapter(venues);
        mAdapter.setAdapterItemClickListener(this);
        rv.setAdapter(mAdapter);
    }


    @Subscribe
    public void getMessage(String p) {
        Toast.makeText(this, p, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(VenueDetails venueDetails) {
//        Toast.makeText(this, venueDetails.getName() + " Formated Adress "+venueDetails.getLocation().getFormattedAddress() +" Phone "+venueDetails.getContact().getFormattedPhone() , Toast.LENGTH_SHORT).show();
    }

}
