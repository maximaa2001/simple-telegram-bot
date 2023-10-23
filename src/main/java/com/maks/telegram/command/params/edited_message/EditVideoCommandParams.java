package com.maks.telegram.command.params.edited_message;

import org.telegram.telegrambots.meta.api.objects.Update;

public class EditVideoCommandParams extends EditMediaCommandParams {

    private final static String EDIT_VIDEO = "EDIT_VIDEO";
    public static final String NEW_VIDEO = "NEW_VIDEO";

    public EditVideoCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        params.put(NEW_VIDEO, update.getEditedMessage().getVideo());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return EDIT_VIDEO;
    }
}
