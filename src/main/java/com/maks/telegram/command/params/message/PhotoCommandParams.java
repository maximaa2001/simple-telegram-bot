package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class PhotoCommandParams extends MediaCommandParams {
    private final static String SEND_PHOTO = "SEND_PHOTO";
    public final static String PHOTO = "PHOTO";


    public PhotoCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        List<PhotoSize> photos = update.getMessage().getPhoto();
        params.put(PHOTO, photos.get(0));
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_PHOTO;
    }
}
