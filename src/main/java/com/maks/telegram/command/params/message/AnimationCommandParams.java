package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.games.Animation;

public class AnimationCommandParams extends MediaCommandParams {
    private final static String SEND_ANIMATION = "SEND_ANIMATION";
    private static final String WIDTH = "WIDTH";
    private static final String HEIGHT = "HEIGHT";

    public AnimationCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Animation animation = update.getMessage().getAnimation();
        params.put(FILE_ID, animation.getFileId());
        params.put(WIDTH, animation.getWidth());
        params.put(HEIGHT, animation.getHeight());
        params.put(DURATION, animation.getDuration());
        params.put(FILE_NAME, animation.getFileName());
        params.put(MIME_TYPE, animation.getMimetype());
        params.put(FILE_SIZE, animation.getFileSize());
        params.put(CAPTION, update.getMessage().getCaption());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_ANIMATION;
    }

}
