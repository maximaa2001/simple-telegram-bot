package com.maks.telegram.command.params.message;

import com.maks.telegram.command.constant.CommandConstant;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;

public class LocationCommandParams extends MessageCommandParams {
    public LocationCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Location location = update.getMessage().getLocation();
        params.put(LOCATION, location);
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return CommandConstant.GET_LOCATION;
    }
}
