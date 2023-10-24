package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class MediaCommandParams extends MessageCommandParams {
    public MediaCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Message message = update.getMessage();
        params.put(CAPTION, message.getCaption());
    }

}
