package com.sithagi.foursquareapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Chathura Wijesinghe <cdanasiri@gmail.com> on 5/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Venue {
    private List<VenueDetails> venueDetails;


    public List<VenueDetails> getVenueDetailses() {
        return venueDetails;
    }

    @JsonProperty("venues")
    public void setVenueDetails(List<VenueDetails> venueDetailses) {
        this.venueDetails = venueDetailses;
    }
}
