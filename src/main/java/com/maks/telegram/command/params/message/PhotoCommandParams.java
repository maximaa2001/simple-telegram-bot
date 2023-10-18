package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;

public class PhotoCommandParams extends MediaCommandParams {
    private final static String SEND_PHOTO = "SEND_PHOTO";


    public PhotoCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        PhotoSize photoSize = update.getMessage().getPhoto().get(0);
        params.put(FILE_ID, photoSize.getFileId());
        params.put(FILE_SIZE, photoSize.getFileSize());
        params.put(CAPTION, update.getMessage().getCaption());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_PHOTO;
    }
}
