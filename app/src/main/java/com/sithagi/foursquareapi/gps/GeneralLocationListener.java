package com.sithagi.foursquareapi.gps;


import com.sithagi.foursquareapi.R;
import com.sithagi.foursquareapi.service.LocationService;

import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;

import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;

import java.util.Iterator;

public class GeneralLocationListener implements LocationListener, GpsStatus.Listener  {

    private static String listenerName;
    private static LocationService loggingService;

    protected String latestHdop;
    protected String latestPdop;
    protected String latestVdop;
    protected String geoIdHeight;
    protected String ageOfDgpsData;
    protected String dgpsId;

    public GeneralLocationListener(LocationService activity, String name) {
        loggingService = activity;
        listenerName = name;
    }

    /**
     * Event raised when a new fix is received.
     */
    public void onLocationChanged(Location loc) {

        try {
            if (loc != null) {
                Bundle b = new Bundle();
                b.putString("HDOP", this.latestHdop);
                b.putString("PDOP", this.latestPdop);
                b.putString("VDOP", this.latestVdop);
                b.putString("GEOIDHEIGHT", this.geoIdHeight);
                b.putString("AGEOFDGPSDATA", this.ageOfDgpsData);
                b.putString("DGPSID", this.dgpsId);

                b.putBoolean("PASSIVE", listenerName.equalsIgnoreCase("PASSIVE"));
                b.putString("LISTENER", listenerName);

                loc.setExtras(b);
                loggingService.OnLocationChanged(loc);

                this.latestHdop = "";
                this.latestPdop = "";
                this.latestVdop = "";
            }

        } catch (Exception ex) {

        }

    }

    public void onProviderDisabled(String provider) {

        loggingService.RestartGpsManagers();
    }

    public void onProviderEnabled(String provider) {


        loggingService.RestartGpsManagers();
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        if (status == LocationProvider.OUT_OF_SERVICE) {

//            loggingService.StopManagerAndResetAlarm();
        }

        if (status == LocationProvider.AVAILABLE) {

        }

        if (status == LocationProvider.TEMPORARILY_UNAVAILABLE) {

//            loggingService.StopManagerAndResetAlarm();
        }
    }

    public void onGpsStatusChanged(int event) {

        switch (event) {
            case GpsStatus.GPS_EVENT_FIRST_FIX:

//                loggingService.SetStatus(loggingService.getString(R.string.fix_obtained));
                break;

            case GpsStatus.GPS_EVENT_SATELLITE_STATUS:

//                GpsStatus status = loggingService.gpsLocationManager.getGpsStatus(null);
//
//                int maxSatellites = status.getMaxSatellites();
//
//                Iterator<GpsSatellite> it = status.getSatellites().iterator();
//                int count = 0;
//
//                while (it.hasNext() && count <= maxSatellites) {
//                    it.next();
//                    count++;
//                }

//                loggingService.SetSatelliteInfo(count);
                break;

            case GpsStatus.GPS_EVENT_STARTED:

//                loggingService.SetStatus(loggingService.getString(R.string.started_waiting));
                break;

            case GpsStatus.GPS_EVENT_STOPPED:
//                loggingService.SetStatus(loggingService.getString(R.string.gps_stopped));
                break;

        }
    }


}