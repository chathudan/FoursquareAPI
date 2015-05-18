package com.sithagi.foursquareapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Chathura Wijesinghe <cdanasiri@gmail.com> on 5/15/15.
 *
 * "contact":{
    "phone":"+94112497382",
    "formattedPhone":"+94 11 2 497382",
    "facebook":"178252572263328",
    "facebookUsername":"CinnamonGrandC",
    "facebookName":"Cinnamon Grand Colombo"
    }
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact{
    private String phone;
    private String formattedPhone;
    private String twitter;


    public String getFormattedPhone() {
        return formattedPhone;
    }

    @JsonProperty("formattedPhone")
    public void setFormattedPhone(String formattedPhone) {
        this.formattedPhone = formattedPhone;
    }

    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTwitter() {
        return twitter;
    }

    @JsonProperty("twitter")
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}