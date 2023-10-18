package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Video;

public class VideoCommandParams extends MediaCommandParams {
    private final static String SEND_VIDEO = "SEND_VIDEO";

    public VideoCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Video video = update.getMessage().getVideo();
        params.put(FILE_ID, video.getFileId());
        params.put(DURATION, video.getDuration());
        params.put(FILE_NAME, video.getFileName());
        params.put(MIME_TYPE, video.getMimeType());
        params.put(FILE_SIZE, video.getFileSize());
        params.put(CAPTION, update.getMessage().getCaption());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_VIDEO;
    }
}
