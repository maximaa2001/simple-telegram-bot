package com.maks.telegram.command.params.edited_message;

import com.maks.telegram.command.constant.CommandConstant;
import org.telegram.telegrambots.meta.api.objects.Update;

public class EditAudioCommandParams extends EditMediaCommandParams {
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
        return CommandConstant.EDIT_AUDIO;
    }
}
