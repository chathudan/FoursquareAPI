package com.sithagi.foursquareapi.util;

import com.squareup.otto.Bus;

/**
 * @author Chathura Wijesinghe <cdanasiri@gmail.com> on 4/24/15.
 */
public final class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }

}

