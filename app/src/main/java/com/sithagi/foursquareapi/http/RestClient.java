package com.sithagi.foursquareapi.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author Chathura Wijesinghe <cdanasiri@gmail.com> on 5/15/15.
 */
public class RestClient {
    private static final String BASE_URL = "http://rest.em.voipem.com";

    private static AsyncHttpClient client = new AsyncHttpClient();

    /*  200 OK */
    public static final int S200 = 200;
    /*  201 Created */
    public static final int S201 = 201;
    /*  202 Accepted */
    public static final int S202 = 202;
    /*  204 No Content */
    public static final int S203 = 204;

    /*  400 Bad Request */
    public static final int E400 = 400;
    /*  401 Unauthorized */
    public static final int E401 = 401;
    /*  403 Forbidden */
    public static final int E403 = 403;
    /*  404 Not Found */
    public static final int E404 = 404;
    /*  406 Not Acceptable */
    public static final int E406 = 406;
    /*  408 Request Timeout */
    public static final int E408= 408;

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(BASE_URL, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


}
