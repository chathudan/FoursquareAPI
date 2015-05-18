package com.sithagi.foursquareapi.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.sithagi.foursquareapi.FSApiApplication;
import com.sithagi.foursquareapi.gps.GeneralLocationListener;
import com.sithagi.foursquareapi.util.BusProvider;


/**
 * @author Chathura Wijesinghe <cdanasiri@gmail.com> on 5/18/15.
 */
public class LocationService extends Service {

    private static String LOG_TAG = LocationService.class.getName();
    Binder binder = new LocationServiceBinder();

    private GeneralLocationListener gpsLocationListener;
    private GeneralLocationListener towerLocationListener;
    public LocationManager towerLocationManager;
    public LocationManager gpsLocationManager;


    @Override
    public void onCreate() {
        BusProvider.getInstance().register(this);
//      Start me in better place rather than here , this is for testing only
        StartGpsManager();
    }

    @Override
    public void onDestroy() {
//      Stop me in better place rather than here , this is for testing only
        StopGpsManager();
        BusProvider.getInstance().unregister(this);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    class LocationServiceBinder extends Binder {
        LocationService getService() {
            return LocationService.this;
        }
    }

    public void onLocationChange(Location loc) {
        System.out.print("Long : " + loc.getLongitude() +"  "+loc.getLatitude());
    }

    private void StartGpsManager() {

        if (gpsLocationListener == null) {
            gpsLocationListener = new GeneralLocationListener(this,"GPS");
        }

        if (towerLocationListener == null) {
            towerLocationListener = new GeneralLocationListener(this,"CELL");
        }

        gpsLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        towerLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        CheckTowerAndGpsStatus();

        if(FSApiApplication.isGpsEnabled()){
            gpsLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,                  1000, 0,
                    gpsLocationListener);
            gpsLocationManager.addGpsStatusListener(gpsLocationListener);

        }else if(FSApiApplication.isTowerEnabled()){
            towerLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1000, 0,
                    towerLocationListener);
        }

    }


   public void OnLocationChanged(Location loc) {
       long currentTimeStamp = System.currentTimeMillis();

       if (FSApiApplication.hasValidLocation()){
           BusProvider.getInstance().post(loc);

           Log.e(LOG_TAG, "OnLocationChanged: hasValidLocation " + loc.toString());
       }

       FSApiApplication.setCurrentLocationInfo(loc);

       Log.e(LOG_TAG, "OnLocationChanged: " +loc.toString());

   }

   public void RestartGpsManagers(){
       StopGpsManager();
       StartGpsManager();
   }

    /**
     * This method is called periodically to determine whether the cell tower /
     * gps providers have been enabled, and sets class level variables to those
     * values.
     */
    private void CheckTowerAndGpsStatus() {
        FSApiApplication.setTowerEnabled(towerLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER));
        FSApiApplication.setGpsEnabled(gpsLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER));
    }

    /**
     * Stops the location managers
     */
    private void StopGpsManager() {

        if (towerLocationListener != null) {

            towerLocationManager.removeUpdates(towerLocationListener);
        }

        if (gpsLocationListener != null) {

            gpsLocationManager.removeUpdates(gpsLocationListener);
            gpsLocationManager.removeGpsStatusListener(gpsLocationListener);
        }

//        FSApiApplication.setWaitingForLocation(false);
//        BusProvider.getInstance().post(new ServiceEvents.WaitingForLocation(false));

    }
}
