package com.maks.telegram.command.params.edited_message;

import org.telegram.telegrambots.meta.api.objects.Update;

public class EditMessageCommandParams extends EditCommandParams {
    private final static String EDIT_TEXT = "EDIT_TEXT";
    public static final String NEW_TEXT = "NEW_TEXT";

    public EditMessageCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        params.put(NEW_TEXT, update.getEditedMessage().getText());
    }


    @Override
    protected String getInvokedCommandName(Update update) {
        return EDIT_TEXT;
    }
}
