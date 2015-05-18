package com.sithagi.foursquareapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Chathura Wijesinghe <cdanasiri@gmail.com> on 5/15/15.
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    public Venue getResponse() {
        return response;
    }

    @JsonProperty("response")
    public void setResponse(Venue response) {
        this.response = response;
    }

    private Venue response;

//    private List<Group> groupList;
//
//    public List<Group> getGroupList() {
//        return groupList;
//    }
//    @JsonProperty("response")
//    public void setGroupList(List<Group> groupList) {
//        this.groupList = groupList;
//    }

//    private Group group;
//
//    public Group getGroup() {
//        return group;
//    }
//
//    @JsonProperty("response")
//    public void setGroup(Group group) {
//        this.group = group;
//    }
}
