package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Audio;
import org.telegram.telegrambots.meta.api.objects.Update;

public class AudioCommandParams extends MediaCommandParams {
    private static final String SEND_AUDIO = "SEND_AUDIO";
    public static final String AUDIO = "AUDIO";

    public AudioCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Audio audio = update.getMessage().getAudio();
        params.put(AUDIO, audio);
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_AUDIO;
    }
}
