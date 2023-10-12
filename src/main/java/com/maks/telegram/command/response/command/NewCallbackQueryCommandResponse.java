package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.params.CommandParams;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.command.response.user.UserResponseType;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class NewCallbackQueryCommandResponse extends CallbackQueryCommandResponse {
    public NewCallbackQueryCommandResponse(CommandParams commandParams, String text, ReturnInlineKeyboard returnInlineKeyboard) {
        super(commandParams, text, returnInlineKeyboard);
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createMessage(), UserResponseType.SEND_MESSAGE);
    }

    private SendMessage createMessage() {
        SendMessage message = new SendMessage(String.valueOf(chatId), text);
        message.setReplyMarkup(returnInlineKeyboard.getNextKeyboard(chatId));
        return message;
    }
}
