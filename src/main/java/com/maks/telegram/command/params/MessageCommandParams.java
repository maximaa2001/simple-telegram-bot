package com.maks.telegram.command.params;

import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageCommandParams extends CommandParams {
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String USERNAME = "USERNAME";

    public MessageCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        params.put(CHAT_ID, String.valueOf(update.getMessage().getFrom().getId()));
        params.put(FIRST_NAME, update.getMessage().getFrom().getFirstName());
        params.put(LAST_NAME, update.getMessage().getFrom().getLastName());
        params.put(USERNAME, update.getMessage().getFrom().getUserName());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return update.getMessage().getText();
    }
}
