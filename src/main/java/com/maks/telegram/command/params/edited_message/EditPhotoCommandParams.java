package com.maks.telegram.command.params.edited_message;

import org.telegram.telegrambots.meta.api.objects.Update;

public class EditPhotoCommandParams extends EditMediaCommandParams {

    private final static String EDIT_PHOTO = "EDIT_PHOTO";
    public static final String NEW_PHOTO = "NEW_PHOTO";

    public EditPhotoCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        params.put(NEW_PHOTO, update.getEditedMessage().getPhoto());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return EDIT_PHOTO;
    }
}
