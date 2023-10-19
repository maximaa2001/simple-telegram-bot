package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;

public class LocationCommandParams extends MessageCommandParams {
    private static final String SEND_LOCATION = "SEND_LOCATION";
    public static final String LONGITUDE = "LONGITUDE";
    public static final String LATITUDE = "LATITUDE";
    public static final String HORIZONTAL_ACCURACY = "HORIZONTAL_ACCURACY";
    public static final String LIVE_PERIOD = "LIVE_PERIOD";
    public static final String HEADING = "HEADING";
    public static final String PROXIMITY_ALERT_RADIUS = "PROXIMITY_ALERT_RADIUS";

    public LocationCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Location location = update.getMessage().getLocation();
        params.put(LONGITUDE, location.getLongitude());
        params.put(LATITUDE, location.getLatitude());
        params.put(HORIZONTAL_ACCURACY, location.getHorizontalAccuracy());
        params.put(LIVE_PERIOD, location.getLivePeriod());
        params.put(HEADING, location.getHeading());
        params.put(PROXIMITY_ALERT_RADIUS, location.getProximityAlertRadius());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_LOCATION;
    }
}
