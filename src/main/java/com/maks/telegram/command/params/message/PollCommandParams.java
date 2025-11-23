package com.maks.telegram.command.params.message;

import com.maks.telegram.command.constant.CommandConstant;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;

public class PollCommandParams extends MessageCommandParams {

    public PollCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Poll poll = update.getMessage().getPoll();
        params.put(POLL, poll);
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return CommandConstant.GET_POLL;
    }
}
