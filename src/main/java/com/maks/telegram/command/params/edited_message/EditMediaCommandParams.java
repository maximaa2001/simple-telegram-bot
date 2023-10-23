package com.maks.telegram.command.params.edited_message;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class EditMediaCommandParams extends EditCommandParams {
    public static final String CAPTION = "CAPTION";

    public EditMediaCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Message message = update.getEditedMessage();
        params.put(CAPTION, message.getCaption());
    }
}
