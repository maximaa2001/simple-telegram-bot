package com.maks.telegram.command.params.message;

import com.maks.telegram.command.params.CommandParams;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public class MessageCommandParams extends CommandParams {
    public static final String MESSAGE_ID = "MESSAGE_ID";
    public static final String DATE = "DATE";
    public static final String FROM_FIRST_NAME = "FROM_FIRST_NAME";
    public static final String FROM_LAST_NAME = "FROM_LAST_NAME";
    public static final String FROM_USERNAME = "USERNAME";

    public MessageCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        Message message = update.getMessage();
        User from = update.getMessage().getFrom();
        params.put(CHAT_ID, message.getChatId());
        params.put(MESSAGE_ID, message.getMessageId());
        params.put(DATE, message.getDate());
        params.put(FROM_FIRST_NAME, from.getFirstName());
        params.put(FROM_LAST_NAME, from.getLastName());
        params.put(FROM_USERNAME, from.getUserName());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return update.getMessage().getText();
    }

    protected void resolveForwardMessage(Message message) {
        User forwardFrom = message.getForwardFrom();
        if(forwardFrom != null) {

        }
    }
}
