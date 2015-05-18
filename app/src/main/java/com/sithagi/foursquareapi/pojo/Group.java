package com.sithagi.foursquareapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Chathura Wijesinghe <cdanasiri@gmail.com> on 5/16/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Group {
    private List<Items> itemsList;


    public List<Items> getItemsList() {
        return itemsList;
    }
    @JsonProperty("groups")
    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }
}
