package com.maks.telegram.command.params.edited_message;

import org.telegram.telegrambots.meta.api.objects.Update;

public class EditAnimationCommandParams extends EditMediaCommandParams {
    private final static String EDIT_ANIMATION = "EDIT_ANIMATION";
    public static final String NEW_ANIMATION = "NEW_ANIMATION";

    public EditAnimationCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        params.put(NEW_ANIMATION, update.getEditedMessage().getAnimation());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return EDIT_ANIMATION;
    }
}
