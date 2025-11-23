package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.meta.CallbackNameValidator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class MessageCommandResponse extends CommandResponse implements CallbackNameValidator {
    private final String text;
    private final ReturnInlineKeyboard returnInlineKeyboard;

    public MessageCommandResponse(Long chatId, String text, ReturnInlineKeyboard returnInlineKeyboard) {
        super(chatId);
        this.text = text;
        if (returnInlineKeyboard != null) {
            validate(returnInlineKeyboard.getNextCommandCallbacks(chatId));
        }
        this.returnInlineKeyboard = returnInlineKeyboard;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createMessage());
    }

    private SendMessage createMessage() {
        return SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .replyMarkup((returnInlineKeyboard != null) ? returnInlineKeyboard.getNextKeyboard(chatId) : null)
                .build();
    }

    public static class MessageCommandResponseBuilder {
        private final Long chatId;
        private final String text;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public MessageCommandResponseBuilder(Long chatId, String text) {
            this.chatId = chatId;
            this.text = text;
        }

        public MessageCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public MessageCommandResponse build() {
            return new MessageCommandResponse(chatId, text, returnInlineKeyboard);
        }
    }
}
