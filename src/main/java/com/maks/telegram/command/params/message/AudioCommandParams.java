package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Audio;
import org.telegram.telegrambots.meta.api.objects.Update;

public class AudioCommandParams extends MediaCommandParams {
    private static final String SEND_AUDIO = "SEND_AUDIO";
    public static final String PERFORMER = "PERFORMER";
    public static final String TITLE = "TITLE";

    public AudioCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Audio audio = update.getMessage().getAudio();
        params.put(FILE_ID, audio.getFileId());
        params.put(DURATION, audio.getDuration());
        params.put(PERFORMER, audio.getPerformer());
        params.put(TITLE, audio.getTitle());
        params.put(FILE_NAME, audio.getFileName());
        params.put(MIME_TYPE, audio.getMimeType());
        params.put(FILE_SIZE, audio.getFileSize());
        params.put(CAPTION, update.getMessage().getCaption());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_AUDIO;
    }
}
