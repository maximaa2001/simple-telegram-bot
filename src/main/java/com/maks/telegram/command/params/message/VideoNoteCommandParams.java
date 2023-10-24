package com.maks.telegram.command.params.message;

import com.maks.telegram.command.constant.CommandConstant;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.VideoNote;

public class VideoNoteCommandParams extends MediaCommandParams {
    public VideoNoteCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        VideoNote videoNote = update.getMessage().getVideoNote();
        params.put(VIDEO_NOTE, videoNote);
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return CommandConstant.GET_VIDEO_NOTE;
    }
}
