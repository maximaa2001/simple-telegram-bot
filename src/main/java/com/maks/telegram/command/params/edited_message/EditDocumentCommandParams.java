package com.maks.telegram.command.params.edited_message;

import org.telegram.telegrambots.meta.api.objects.Update;

public class EditDocumentCommandParams extends EditMediaCommandParams {

    private final static String EDIT_DOCUMENT = "EDIT_DOCUMENT";
    public static final String NEW_DOCUMENT = "NEW_DOCUMENT";

    public EditDocumentCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        params.put(NEW_DOCUMENT, update.getEditedMessage().getDocument());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return EDIT_DOCUMENT;
    }
}
