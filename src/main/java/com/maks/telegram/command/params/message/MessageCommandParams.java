package com.maks.telegram.command.params.message;

import com.maks.telegram.command.params.CommandParams;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageCommandParams extends CommandParams {

    public MessageCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        Message message = update.getMessage();
        params.put(CHAT_ID, message.getChatId());
        params.put(CHAT, message.getChat());
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
