package com.sithagi.foursquareapi.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Chathura Wijesinghe <cdanasiri@gmail.com> on 5/15/15.
 *
 * {
"id":"4c0771cb3cbed13a7af40bc0",
"name":"Coco Veranda",
"contact":{
"phone":"+94117635635",
"formattedPhone":"+94 11 7 635635",
"facebookUsername":"CocoVeranda",
"facebookName":"Coco Veranda"
},
"location":{
"address":"32, Ward Pl.",
"lat":6.916862569577828,
"lng":79.86691474914551,
"distance":8019,
"postalCode":"00700",
"cc":"LK",
"city":"Colombo",
"state":"Western",
"country":"Sri Lanka",
"formattedAddress":[
"32, Ward Pl.",
"Colombo 00700",
"Sri Lanka"
]
},
"categories":[
{
"id":"4bf58dd8d48988d16d941735",
"name":"Café",
"pluralName":"Cafés",
"shortName":"Café",
"icon":{
"prefix":"https:\/\/ss3.4sqi.net\/img\/categories_v2\/food\/cafe_",
"suffix":".png"
},
"primary":true
}
],
"verified":true,
"stats":{
"checkinsCount":9744,
"usersCount":2252,
"tipCount":272
},
"url":"http:\/\/www.cocoveranda.com",
"specials":{
"count":0,
"items":[

]
},
"hereNow":{
"count":2,
"summary":"2 people are here",
"groups":[
{
"type":"others",
"name":"Other people here",
"count":2,
"items":[

]
}
]
},
"storeId":"",
"referralId":"v-1431882275"
}
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class VenueDetails {
    private String id;
    private String name;
    private String type;
    private Location location;
    private Contact contact;

    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    @JsonProperty("contact")
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }


}


