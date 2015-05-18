package com.sithagi.foursquareapi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sithagi.foursquareapi.R;
import com.sithagi.foursquareapi.pojo.VenueDetails;

import java.util.ArrayList;
import java.util.List;

public class FSAdapter extends RecyclerView.Adapter<LocationViewHolder> {


    private List<VenueDetails> venus;
    private List<VenueDetails> visibleObjects;
    private List<VenueDetails> allObjects;

    private List<OnAdapterItemClickListener> adapterItemClickListenerArrayList = new ArrayList<OnAdapterItemClickListener>();

    public FSAdapter(List<VenueDetails> venusdDetailses) {
        this.allObjects = venusdDetailses;
        this.venus = venusdDetailses;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        LocationViewHolder pvh = new LocationViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(LocationViewHolder locationViewHolder, final int i) {
        locationViewHolder.locationName.setText(venus.get(i).getName());
        locationViewHolder.locationPhone.setText(venus.get(i).getContact().getFormattedPhone());
        String ss = venus.get(i).getLocation().getFormattedAddress();
        locationViewHolder.locationAddress.setText(ss);

//        TODO Set Image , its better to have the library Picaso
//        locationViewHolder.locationPhoto.setImageResource(persons.get(i).photoId);

        locationViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (OnAdapterItemClickListener l : adapterItemClickListenerArrayList) {
                    l.onItemClick(venus.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return venus.size();
    }

    /**
     * Clear search
     *
     */
    public void clearSearchLocation() {
        this.venus = this.allObjects;
        notifyDataSetChanged();
    }

    /**
     * To search location
     * @param queryText
     */
    public void searchLocation(String queryText) {

        queryText = queryText.toLowerCase();
        visibleObjects = new ArrayList<VenueDetails>();
        for (VenueDetails venueDetails : venus) {
            String n = venueDetails.getName();

            if (n.toLowerCase().contains(queryText))
                visibleObjects.add(venueDetails);
        }
        venus = visibleObjects;
        notifyDataSetChanged();
    }

    public void setAdapterItemClickListener(OnAdapterItemClickListener listener) {
        adapterItemClickListenerArrayList.add(listener);
    }

    public void removeAdapterItemClickListener(OnAdapterItemClickListener listener) {
        adapterItemClickListenerArrayList.remove(listener);
    }

    public interface OnAdapterItemClickListener {
        public void onItemClick(VenueDetails venueDetails);
    }
}
