package com.maks.telegram.command.params.message;

import com.maks.telegram.command.params.CommandParams;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageCommandParams extends CommandParams {
    public static final String MESSAGE_ID = "MESSAGE_ID";
    public static final String MESSAGE_DATE = "MESSAGE_DATE";
    public static final String FROM_USER = "FROM_USER";
    public static final String FORWARD_DATE = "FORWARD_DATE";
    public static final String FORWARD_FROM_USER = "FORWARD_FROM_USER";
    public static final String FORWARD_FROM_CHAT = "FORWARD_FROM_CHAT";
    public static final String FORWARD_FROM_MESSAGE_ID = "FORWARD_FROM_MESSAGE_ID";


    public MessageCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        Message message = update.getMessage();
        params.put(CHAT_ID, message.getChatId());
        params.put(MESSAGE_ID, message.getMessageId());
        params.put(MESSAGE_DATE, message.getDate());
        params.put(FROM_USER, message.getFrom());
        resolveForwardMessage(message);
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return update.getMessage().getText();
    }

    protected void resolveForwardMessage(Message message) {
        Integer forwardDate = message.getForwardDate();
        if (forwardDate != null) {
            params.put(FORWARD_DATE, forwardDate);
            params.put(FORWARD_FROM_USER, message.getForwardFrom());
            params.put(FORWARD_FROM_CHAT, message.getForwardFromChat());
            params.put(FORWARD_FROM_MESSAGE_ID, message.getForwardFromMessageId());
        }
    }
}
