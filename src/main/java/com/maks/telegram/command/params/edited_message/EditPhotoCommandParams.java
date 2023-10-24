package com.maks.telegram.command.params.edited_message;

import com.maks.telegram.command.constant.CommandConstant;
import org.telegram.telegrambots.meta.api.objects.Update;

public class EditPhotoCommandParams extends EditMediaCommandParams {
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
        return CommandConstant.EDIT_PHOTO;
    }
}
