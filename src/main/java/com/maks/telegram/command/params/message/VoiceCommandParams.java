package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Voice;

public class VoiceCommandParams extends MediaCommandParams {
    private final static String SEND_VOICE = "SEND_VOICE";


    public VoiceCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Voice voice = update.getMessage().getVoice();
        params.put(FILE_ID, voice.getFileId());
        params.put(DURATION, voice.getDuration());
        params.put(MIME_TYPE, voice.getMimeType());
        params.put(FILE_SIZE, voice.getFileSize());
        params.put(CAPTION, update.getMessage().getCaption());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_VOICE;
    }
}
