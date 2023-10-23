package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Video;

public class VideoCommandParams extends MediaCommandParams {
    private final static String SEND_VIDEO = "SEND_VIDEO";
    public final static String VIDEO = "VIDEO";

    public VideoCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Video video = update.getMessage().getVideo();
        params.put(VIDEO, video);
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_VIDEO;
    }
}
