package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;

public class PollCommandParams extends MessageCommandParams {
    private static final String SEND_POLL = "SEND_POLL";
    private static final String POLL = "POLL";


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
        return SEND_POLL;
    }
}
