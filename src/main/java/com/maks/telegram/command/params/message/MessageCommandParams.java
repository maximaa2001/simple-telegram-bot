package com.maks.telegram.command.params.message;

import com.maks.telegram.command.params.CommandParams;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public class MessageCommandParams extends CommandParams {
    public static final String MESSAGE_ID = "MESSAGE_ID";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String USERNAME = "USERNAME";

    public MessageCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        Message message = update.getMessage();
        User from = update.getMessage().getFrom();
        params.put(CHAT_ID, message.getChatId());
        params.put(MESSAGE_ID, message.getMessageId());
        params.put(FIRST_NAME, from.getFirstName());
        params.put(LAST_NAME, from.getLastName());
        params.put(USERNAME, from.getUserName());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return update.getMessage().getText();
    }
}
