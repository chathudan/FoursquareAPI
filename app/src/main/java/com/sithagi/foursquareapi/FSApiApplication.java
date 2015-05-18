package com.sithagi.foursquareapi;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sithagi.foursquareapi.service.LocationService;

import java.net.InetAddress;

/**
 * @author Chathura Wijesinghe <cdanasiri@gmail.com> on 5/18/15.
 */
public class FSApiApplication extends Application {

    private static boolean towerEnabled;
    private static boolean gpsEnabled;
    private static boolean isStarted;
    private static boolean isUsingGps;

    private static long startTimeStamp;
    private static long latestTimeStamp;

    private static Location currentLocationInfo;
    private static Location previousLocationInfo;

    private static FSApiApplication fsApiApplication;

    private static FSApiApplication getInstance() {
        return fsApiApplication;
    }

    /**
     * @return whether GPS (tower) is enabled
     */
    public static boolean isTowerEnabled() {
        return towerEnabled;
    }


    // ---------------------------------------------------

    /**
     * @param towerEnabled set whether GPS (tower) is enabled
     */
    public static void setTowerEnabled(boolean towerEnabled) {
        FSApiApplication.towerEnabled = towerEnabled;
    }

    /**
     * @return whether GPS (satellite) is enabled
     */
    public static boolean isGpsEnabled() {
        return gpsEnabled;
    }

    /**
     * @param gpsEnabled set whether GPS (satellite) is enabled
     */
    public static void setGpsEnabled(boolean gpsEnabled) {
        FSApiApplication.gpsEnabled = gpsEnabled;
    }

    /**
     * @return whether logging has started
     */
    public static boolean isStarted() {
        return isStarted;
    }

    /**
     * @param isStarted set whether logging has started
     */
    public static void setStarted(boolean isStarted) {
        FSApiApplication.isStarted = isStarted;
        if (isStarted) {
            FSApiApplication.startTimeStamp = System.currentTimeMillis();
        }
    }

    /**
     * @return the isUsingGps
     */
    public static boolean isUsingGps() {
        return isUsingGps;
    }

    /**
     * @param isUsingGps the isUsingGps to set
     */
    public static void setUsingGps(boolean isUsingGps) {
        FSApiApplication.isUsingGps = isUsingGps;
    }

    /**
     * @return the Location class containing latest lat-long information
     */
    public static Location getCurrentLocationInfo() {
        return currentLocationInfo;
    }

    /**
     * @param currentLocationInfo the latest Location class
     */
    public static void setCurrentLocationInfo(Location currentLocationInfo) {
        FSApiApplication.currentLocationInfo = currentLocationInfo;
    }

    public static Location getPreviousLocationInfo() {
        return previousLocationInfo;
    }

    public static void setPreviousLocationInfo(Location previousLocationInfo) {
        FSApiApplication.previousLocationInfo = previousLocationInfo;
    }

    /**
     * Determines whether a valid location is available
     */
    public static boolean hasValidLocation() {
        return (getCurrentLocationInfo() != null && getCurrentLatitude() != 0 && getCurrentLongitude() != 0);
    }

    /**
     * @return the currentLongitude
     */
    public static double getCurrentLongitude() {
        if (getCurrentLocationInfo() != null) {
            return getCurrentLocationInfo().getLongitude();
        } else {
            return 0;
        }
    }

    /**
     * @return the currentLatitude
     */
    public static double getCurrentLatitude() {
        if (getCurrentLocationInfo() != null) {
            return getCurrentLocationInfo().getLatitude();
        } else {
            return 0;
        }
    }

    public static boolean isInternetAvailable() {

        return getInstance().isNetworkConnected();

//        TODO check the proper internet connection , this shout not invoke on Main thread
//        if (getInstance().isNetworkConnected()) {
//            try {
//                InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name
//                if (ipAddr.equals("")) {
//                    return false;
//                } else {
//                    return true;
//                }
//            } catch (Exception e) {
//                return false;
//            }
//        } else
//            return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.fsApiApplication = this;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }
}
