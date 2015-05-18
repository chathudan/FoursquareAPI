package com.sithagi.foursquareapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Chathura Wijesinghe <cdanasiri@gmail.com> on 5/15/15.
 *
 *  "location":{
        "address":"Marine Dr",
        "lat":6.905155798398304,
        "lng":79.85009893048249,
        "distance":8332,
        "postalCode":"00300",
        "cc":"LK",
        "city":"Colombo",
        "state":"Western",
        "country":"Sri Lanka",
        "formattedAddress":[
            "Marine Dr",
            "Colombo 00300",
            "Sri Lanka"
        ]
    }

 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location{

    private String formattedAddress[];
    private String lat;
    private String lan;
    private String distance;
    private String address ;

    public String getDistance() {
        return distance;
    }
    @JsonProperty("distance")
    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLan() {
        return lan;
    }

    @JsonProperty("lng")
    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getFormattedAddress() {
        address = "";
        for(String s : formattedAddress)
            address +=s ;

        return  address;
//        return  Arrays.toString(formattedAddress);
    }
    @JsonProperty("formattedAddress")
    public void setFormattedAddress(String formattedAddress[]) {
        this.formattedAddress = formattedAddress;
    }

}
