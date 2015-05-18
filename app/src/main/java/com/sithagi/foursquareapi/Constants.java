package com.sithagi.foursquareapi;

/**
 * Created by Chathura Wijesinghe <cdanasiri@gmail.com> on 4/22/15.
 */
public final class Constants {
    public static final String TAG = "Constants";

    /* Client secret  */
    public static final String VAL_CLIENT_SECRET = "YZCKUYJ1WHUV2QICBXUBEILZI1DMPUIDP5SHV043O04FKBHL";
    /* Client ID  */
    public static final String VAL_CLIENTID = "ACAO2JPKM1MXHQJCK45IIFKRFR2ZVL0QASMCBCG5NPJQWF2G";
    /* Category ID of Coffee shops */
    public static final String VAL_CATERGORYID_COFEE = "4bf58dd8d48988d1e0931735";
    /* Number for shops at a time */
    public static final int VAL_QUERY_LIMIT = 20;
    /* default Lat and Lon */
    public static final String VAL_LAT_LON = "6.85783,79.908508";

    public static final String ANDSIGN = "&";
    public static final String PARAM_CAT = "cat=";
    public static final String PARAM_LATLON = "ll=";
    public static final String PARAM_CSECRET = "client_secret=";
    public static final String PARAM_CID = "client_id=";

    public static final String TERMS_EXPLOR = "venues/search";//"venues/explore?";

    public static final String URL_API = "https://api.foursquare.com/v2/";

    public static final String URL_VENUE =

// URL_API+ "venues/explore?client_id="+VAL_CID+"&client_secret="+VAL_CSECRET+"&v=20130815%20&cat="+VAL_CATERGORY+"&ll=6.85783,79.908508";

            "https://api.foursquare.com/v2/venues/search?v=20131016&categoryId=" + VAL_CATERGORYID_COFEE + "&client_id=" + VAL_CLIENTID + "&client_secret=" + VAL_CLIENT_SECRET+ "&limit=" + VAL_QUERY_LIMIT + "&ll=";

//    https://api.foursquare.com/v2/venues/search?client_id=CLIENT_ID&client_secret=CLIENT_SECRET&v=20130815%20&ll=40.7463956,-73.9852992
}