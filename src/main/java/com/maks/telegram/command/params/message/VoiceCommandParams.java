package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Voice;

public class VoiceCommandParams extends MediaCommandParams {
    private final static String SEND_VOICE = "SEND_VOICE";
    public final static String VOICE = "VOICE";


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
        return SEND_VOICE;
    }
}
