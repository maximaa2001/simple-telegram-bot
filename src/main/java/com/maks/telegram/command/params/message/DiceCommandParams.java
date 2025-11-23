package com.maks.telegram.command.params.message;

import com.maks.telegram.command.constant.CommandConstant;
import org.telegram.telegrambots.meta.api.objects.Dice;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DiceCommandParams extends MessageCommandParams {
    public DiceCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        Dice dice = update.getMessage().getDice();
        params.put(DICE, dice);
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return CommandConstant.GET_DICE;
    }
}
