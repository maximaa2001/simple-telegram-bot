package com.maks.telegram.command.params.message;

import com.maks.telegram.command.constant.CommandConstant;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Video;

public class VideoCommandParams extends MediaCommandParams {
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
        return CommandConstant.GET_VIDEO;
    }
}
