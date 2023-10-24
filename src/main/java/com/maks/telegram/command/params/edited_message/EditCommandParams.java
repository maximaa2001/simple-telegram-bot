package com.maks.telegram.command.params.edited_message;

import com.maks.telegram.command.params.message.MessageCommandParams;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class EditCommandParams extends MessageCommandParams {

    public EditCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        Message editedMessage = update.getEditedMessage();
        params.put(CHAT_ID, editedMessage.getChatId());
        params.put(MESSAGE_ID, editedMessage.getMessageId());
        params.put(MESSAGE_DATE, editedMessage.getDate());
        params.put(FROM_USER, editedMessage.getFrom());
        params.put(EDIT_DATE, editedMessage.getEditDate());
    }
}
