package com.maks.telegram.command.response.command.edit;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaDocument;

import java.io.File;

public class EditDocumentCommandResponse extends EditMediaCommandResponse {
    public EditDocumentCommandResponse(Long chatId, Integer messageId, String identifier, File file, String caption, ReturnInlineKeyboard returnInlineKeyboard) {
        super(chatId, messageId, identifier, file, caption, returnInlineKeyboard);
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createEditMessageMedia());
    }

    @Override
    protected InputMedia createInputMedia(String media, File mediaFile, String mediaName, boolean isNewMedia) {
        return InputMediaDocument.builder()
                .media(media)
                .newMediaFile(mediaFile)
                .mediaName(mediaName)
                .isNewMedia(isNewMedia)
                .caption(caption)
                .build();
    }

    public static class EditDocumentCommandResponseBuilder {
        private final Long chatId;
        private final Integer messageId;
        private String identifier;
        private File file;
        private String caption;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public EditDocumentCommandResponseBuilder(Long chatId, Integer messageId, String identifier) {
            this.chatId = chatId;
            this.messageId = messageId;
            this.identifier = identifier;
        }

        public EditDocumentCommandResponseBuilder(Long chatId, Integer messageId, File file) {
            this.chatId = chatId;
            this.messageId = messageId;
            this.file = file;
        }

        public EditDocumentCommandResponseBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public EditDocumentCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public EditDocumentCommandResponse build() {
            return new EditDocumentCommandResponse(chatId, messageId, identifier, file, caption, returnInlineKeyboard);
        }
    }
}
