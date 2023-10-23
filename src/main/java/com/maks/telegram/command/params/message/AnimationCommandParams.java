package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.games.Animation;

public class AnimationCommandParams extends MediaCommandParams {
    private final static String SEND_ANIMATION = "SEND_ANIMATION";
    public final static String ANIMATION = "ANIMATION";

    public AnimationCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Animation animation = update.getMessage().getAnimation();
        params.put(ANIMATION, animation);
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_ANIMATION;
    }

}
