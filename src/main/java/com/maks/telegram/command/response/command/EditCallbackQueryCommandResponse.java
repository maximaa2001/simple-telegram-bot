package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.params.CommandParams;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.command.response.user.UserResponseType;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class EditCallbackQueryCommandResponse extends CallbackQueryCommandResponse {

    public EditCallbackQueryCommandResponse(CommandParams commandParams, String text, ReturnInlineKeyboard returnInlineKeyboard) {
        super(commandParams, text, returnInlineKeyboard);
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createEditMessageText(), UserResponseType.EDIT_MESSAGE_TEXT);
    }

    private EditMessageText createEditMessageText() {
        EditMessageText messageText = new EditMessageText();
        messageText.setChatId(chatId);
        messageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        messageText.setText(text);
        messageText.setReplyMarkup(returnInlineKeyboard.getNextKeyboard(chatId));
        return messageText;
    }
}
