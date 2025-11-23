package com.maks.telegram.command.params.message;

import com.maks.telegram.command.constant.CommandConstant;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Voice;

public class VoiceCommandParams extends MediaCommandParams {

    public VoiceCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Voice voice = update.getMessage().getVoice();
        params.put(VOICE, voice);
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return CommandConstant.GET_VOICE;
    }
}
