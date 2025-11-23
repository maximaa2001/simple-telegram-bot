package com.maks.telegram.command.params.message;

import com.maks.telegram.command.constant.CommandConstant;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.games.Animation;

public class AnimationCommandParams extends MediaCommandParams {
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
        return CommandConstant.GET_ANIMATION;
    }
}
