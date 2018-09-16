package com.villa.deimer.hackathon.models.services.events;

import com.squareup.otto.Bus;

public class StationBus {

    private static Bus bus = new Bus();
    public static Bus getBus() {
        return bus;
    }

}
