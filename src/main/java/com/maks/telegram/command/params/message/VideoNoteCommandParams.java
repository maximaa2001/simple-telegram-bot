package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.VideoNote;

public class VideoNoteCommandParams extends MediaCommandParams {
    private final static String SEND_VIDEO_NOTE = "SEND_VIDEO_NOTE";
    public static final String LENGTH = "LENGTH";

    public VideoNoteCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        VideoNote videoNote = update.getMessage().getVideoNote();
        params.put(FILE_ID, videoNote.getFileId());
        params.put(LENGTH, videoNote.getLength());
        params.put(DURATION, videoNote.getDuration());
        params.put(FILE_SIZE, videoNote.getFileSize());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_VIDEO_NOTE;
    }
}
