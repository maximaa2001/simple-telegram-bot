package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Dice;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DiceCommandParams extends MessageCommandParams {
    private static final String SEND_DICE = "SEND_DICE";
    public static final String EMOJI = "EMOJI";
    public static final String VALUE = "VALUE";

    public DiceCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        Dice dice = update.getMessage().getDice();
        params.put(EMOJI, dice.getEmoji());
        params.put(VALUE, dice.getValue());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_DICE;
    }
}
