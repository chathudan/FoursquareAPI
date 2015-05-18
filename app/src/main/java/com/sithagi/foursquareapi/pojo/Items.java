package com.sithagi.foursquareapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Chathura Wijesinghe <cdanasiri@gmail.com> on 5/16/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {
//    public Venue getResponse() {
//        return response;
//    }
//
//    @JsonProperty("venue")
//    public void setResponse(Venue response) {
//        this.response = response;
//    }
//
//    private Venue response;

    private List<VenueDetails> venueDetailses;

    public List<VenueDetails> getVenueDetailses() {
        return venueDetailses;
    }
    @JsonProperty("items")
    public void setVenueDetailses(List<VenueDetails> venueDetailses) {
        this.venueDetailses = venueDetailses;
    }
}
