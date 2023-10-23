package com.maks.telegram.command.params.edited_message;

import org.telegram.telegrambots.meta.api.objects.Update;

public class EditAudioCommandParams extends EditMediaCommandParams {
    private final static String EDIT_AUDIO = "EDIT_AUDIO";
    public static final String NEW_AUDIO = "NEW_AUDIO";

    public EditAudioCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        params.put(NEW_AUDIO, update.getEditedMessage().getAudio());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return EDIT_AUDIO;
    }
}
