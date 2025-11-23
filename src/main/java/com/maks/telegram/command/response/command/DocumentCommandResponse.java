package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;

import java.io.File;

public class DocumentCommandResponse extends MediaCommandResponse {
    public DocumentCommandResponse(Long chatId, String identifier, File file, String caption, ReturnInlineKeyboard returnInlineKeyboard) {
        super(chatId, identifier, file, caption, returnInlineKeyboard);
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createDocument());
    }

    private SendDocument createDocument() {
        return SendDocument.builder()
                .chatId(chatId)
                .document(defaultInputMedia())
                .caption(caption)
                .replyMarkup((returnInlineKeyboard != null) ? returnInlineKeyboard.getNextKeyboard(chatId) : null)
                .build();
    }

    public static class DocumentCommandResponseBuilder {
        private final Long chatId;
        private String identifier;
        private File file;
        private String caption;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public DocumentCommandResponseBuilder(Long chatId, String identifier) {
            this.chatId = chatId;
            this.identifier = identifier;
        }

        public DocumentCommandResponseBuilder(Long chatId, File file) {
            this.chatId = chatId;
            this.file = file;
        }

        public DocumentCommandResponseBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public DocumentCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public DocumentCommandResponse build() {
            return new DocumentCommandResponse(chatId, identifier, file, caption, returnInlineKeyboard);
        }
    }
}
