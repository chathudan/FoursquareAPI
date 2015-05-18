package com.sithagi.foursquareapi.util;

import android.content.Context;
import android.location.Location;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpRequest;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.ResponseHandlerInterface;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

/**
 * @author  Chathura Wijesinghe <cdanasiri@gmail.com> on 4/23/15.
 */
public interface HttpInterface {

    String getDefaultURL(Location loc);

    AsyncHttpRequest getHttpRequest(DefaultHttpClient client, HttpContext httpContext, HttpUriRequest uriRequest, String contentType, ResponseHandlerInterface responseHandler, Context context);

    HttpEntity getRequestEntity(String requestBody);

    AsyncHttpClient getAsyncHttpClient();

    ResponseHandlerInterface getResponseHandler();

    void setAsyncHttpClient(AsyncHttpClient client);

    RequestHandle executeRequest(AsyncHttpClient client, String URL, Header[] headers, HttpEntity entity, ResponseHandlerInterface responseHandler);


}
