package com.sithagi.foursquareapi.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpRequest;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.ResponseHandlerInterface;
import com.sithagi.foursquareapi.Constants;
import com.sithagi.foursquareapi.FSApiApplication;
import com.sithagi.foursquareapi.service.LocationService;
import com.squareup.otto.Subscribe;
import com.sithagi.foursquareapi.R;
import com.sithagi.foursquareapi.adapter.FSAdapter;

import com.sithagi.foursquareapi.pojo.Person;
import com.sithagi.foursquareapi.pojo.Response;
import com.sithagi.foursquareapi.pojo.Venue;
import com.sithagi.foursquareapi.pojo.VenueDetails;
import com.sithagi.foursquareapi.util.BusProvider;
import com.sithagi.foursquareapi.util.HttpInterface;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chathura Wijesinghe <cdanasiri@gmail.com> on 5/15/15.
 */
public class FetchData extends ActionBarActivity implements HttpInterface, FSAdapter.OnAdapterItemClickListener,SearchView.OnQueryTextListener {

    public static final String LOG_TAG = "FetchData";
    FSAdapter mAdapter;
    private ObjectMapper jsonMapper = new ObjectMapper();
    private List<Person> persons;
    private RecyclerView rv;
    private Handler mHandler = new Handler();
    private Toolbar toolbar;
    private boolean mRequestMade = false;
    private AsyncHttpClient asyncHttpClient = new AsyncHttpClient() {
        @Override
        protected AsyncHttpRequest newAsyncHttpRequest(DefaultHttpClient client, HttpContext httpContext, HttpUriRequest uriRequest, String contentType, ResponseHandlerInterface responseHandler, Context context) {
            AsyncHttpRequest httpRequest = getHttpRequest(client, httpContext, uriRequest, contentType, responseHandler, context);
            return httpRequest == null
                    ? super.newAsyncHttpRequest(client, httpContext, uriRequest, contentType, responseHandler, context)
                    : httpRequest;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_activity);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

        rv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        handleIntent(getIntent());

        BusProvider.getInstance().register(this);

        if (FSApiApplication.isInternetAvailable())
        executeRequest(getAsyncHttpClient(),
                getDefaultURL(null),
                getRequestHeaders(),
                getRequestEntity(null),
                getResponseHandler());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSearchLocation();
            }
        });
    }

    @Override
    public String getDefaultURL(Location loc) {
        String location;
        Log.d(LOG_TAG, Constants.URL_VENUE);
        if (loc != null)
            location = loc.getLatitude() + "," + loc.getLongitude();
        else
            location = Constants.VAL_LAT_LON;


        return Constants.URL_VENUE + location;

    }

    @Override
    public AsyncHttpRequest getHttpRequest(DefaultHttpClient client, HttpContext httpContext, HttpUriRequest uriRequest, String contentType, ResponseHandlerInterface responseHandler, Context context) {
        return null;
    }

    @Override
    public HttpEntity getRequestEntity(String requestBody) {
        if (requestBody != null) {
            try {
                return new StringEntity(requestBody);
            } catch (UnsupportedEncodingException e) {
                Log.e(LOG_TAG, "cannot create String entity", e);
            }
        }
        return null;
    }

    @Override
    public AsyncHttpClient getAsyncHttpClient() {
        return this.asyncHttpClient;
    }

    @Override
    public void setAsyncHttpClient(AsyncHttpClient client) {
        this.asyncHttpClient = client;
    }

    @Override
    public ResponseHandlerInterface getResponseHandler() {
        return new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                Log.d(LOG_TAG, new String(response));
                try {

                    String jsonString = new String(response);

                    Response response1 = jsonMapper.readValue(jsonString, Response.class);

                    Venue venue = response1.getResponse();

                    BusProvider.getInstance().post(venue);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //            TODO handle me please
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                Log.e(LOG_TAG,""+statusCode+ e.getMessage());
                if (errorResponse != null) {
                    BusProvider.getInstance().post(new String(errorResponse));
                }
            }
        };
    }

    @Override
    public RequestHandle executeRequest(AsyncHttpClient client, String URL, Header[] headers, HttpEntity entity, ResponseHandlerInterface responseHandler) {
//        return client.get(this, URL, headers, entity, null, responseHandler);
        return client.get(this, URL, headers, null, responseHandler);
    }


    public Header[] getRequestHeaders() {
        List<Header> headers = getRequestHeadersList();
        return headers.toArray(new Header[headers.size()]);
    }

    public List<Header> getRequestHeadersList() {
        List<Header> headers = new ArrayList<Header>();
//        TODO ADD Http Headers if it nessosary
        String headersRaw = null;
        if (headersRaw != null && headersRaw.length() > 3) {
            String[] lines = headersRaw.split("\\r?\\n");
            for (String line : lines) {
                try {
                    int equalSignPos = line.indexOf('=');
                    if (1 > equalSignPos) {
                        throw new IllegalArgumentException("Wrong header format, may be 'Key=Value' only");
                    }
                    String headerName = line.substring(0, equalSignPos).trim();
                    String headerValue = line.substring(1 + equalSignPos).trim();
//                    Log.d(LOG_TAG, String.format("Added header: [%s:%s]", headerName, headerValue));
                    headers.add(new BasicHeader(headerName, headerValue));
                } catch (Throwable t) {
//                    Log.e(LOG_TAG, "Not a valid header line: " + line, t);
                }
            }
        }
        return headers;
    }


    //    TODO Show Location data on detailed view
    @Override
    public void onItemClick(VenueDetails venueDetails) {
        Toast.makeText(this, venueDetails.getName() + "\n" + venueDetails.getLocation().getFormattedAddress()
                + "\n" + venueDetails.getContact().getFormattedPhone()
                + "\n Lat: " + venueDetails.getLocation().getLat() + " Lon: " + venueDetails.getLocation().getLan()
                + "\n Distance " + venueDetails.getLocation().getDistance(), Toast.LENGTH_LONG).show();

    }

    private void searchLocation(String filter) {
        if (mAdapter != null)
            mAdapter.searchLocation(filter);
    }

    private void clearSearchLocation() {
        if (mAdapter != null)
            mAdapter.clearSearchLocation();
    }

    @Override
    protected void onDestroy() {
        // This is not the best place to stop the service , I'll handle this later
        Intent serviceIntent = new Intent(getApplicationContext(), LocationService.class);
        stopService(serviceIntent);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
//      TODO Handle me , START in better place
//      Hint use StartGpsManager @LocationService
        Intent serviceIntent = new Intent(getApplicationContext(), LocationService.class);
        startService(serviceIntent);
        mRequestMade = false;
        super.onResume();

        if (!FSApiApplication.isInternetAvailable()){
            Toast.makeText(getApplicationContext(), "No Internet - please check your conectivity ", Toast.LENGTH_LONG).show();
        }
        else if (!FSApiApplication.isGpsEnabled()) {
            Toast.makeText(getApplicationContext(), "GPS not available - waiting for valid GPS location... ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {

//      TODO Handle me , STOP in better place
//      Hint use StopGpsManager @LocationService
        Intent serviceIntent = new Intent(getApplicationContext(), LocationService.class);
        stopService(serviceIntent);
        mRequestMade = false;
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    @Subscribe
    public void getMessage(String message) {
        Toast.makeText(this, "Message : " + message, Toast.LENGTH_LONG).show();
    }

    @Subscribe
    public void getMessage(final Venue venue) {

        if (venue != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter = new FSAdapter(venue.getVenueDetailses());
                    mAdapter.setAdapterItemClickListener(FetchData.this);
                    rv.setAdapter(mAdapter);
                }
            });
        }
    }

    @Subscribe
    public void getMessage(Location loc) {
        Log.d(LOG_TAG, "FetchData : hasValidLocation " + loc.toString());

        if (!mRequestMade) {
            Toast.makeText(this, "GPS Data : LAT " +loc.getLatitude()+ " LON "+loc.getLongitude(), Toast.LENGTH_LONG).show();
            mRequestMade = true;
            executeRequest(getAsyncHttpClient(),
                    getDefaultURL(loc),
                    getRequestHeaders(),
                    getRequestEntity(null),
                    getResponseHandler());
        }
    }


    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchLocation(query);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        searchLocation(s);
        return false;
    }
}
