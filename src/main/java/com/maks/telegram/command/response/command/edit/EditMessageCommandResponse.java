package com.maks.telegram.command.response.command.edit;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.command.response.user.UserResponseType;
import com.maks.telegram.meta.CallbackNameValidator;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class EditMessageCommandResponse extends EditCommandResponse implements CallbackNameValidator {
    private final String text;
    private final ReturnInlineKeyboard returnInlineKeyboard;

    public EditMessageCommandResponse(Long chatId, Integer messageId, String text, ReturnInlineKeyboard returnInlineKeyboard) {
        super(chatId, messageId);
        this.text = text;
        if (returnInlineKeyboard != null) {
            validate(returnInlineKeyboard.getNextCommandCallbacks(chatId));
        }
        this.returnInlineKeyboard = returnInlineKeyboard;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createEditMessageText());
    }

    private EditMessageText createEditMessageText() {
        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .text(text)
                .replyMarkup((returnInlineKeyboard != null) ? returnInlineKeyboard.getNextKeyboard(chatId) : null)
                .build();
    }

    public static class EditMessageCommandResponseBuilder {
        private final Long chatId;
        private final Integer messageId;
        private final String text;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public EditMessageCommandResponseBuilder(Long chatId, Integer messageId, String text) {
            this.chatId = chatId;
            this.messageId = messageId;
            this.text = text;
        }

        public EditMessageCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public EditMessageCommandResponse build() {
            return new EditMessageCommandResponse(chatId, messageId, text, returnInlineKeyboard);
        }
    }
}
