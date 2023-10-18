package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;

public class PhotoCommandResponse extends MediaCommandResponse {

    public PhotoCommandResponse(Long chatId, String identifier, File file, String caption,
                                ReturnInlineKeyboard returnInlineKeyboard) {
        super(chatId, identifier, file, caption, returnInlineKeyboard);
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createPhoto());
    }

    private SendPhoto createPhoto() {
        InputFile inputFile = (identifier != null) ? new InputFile(identifier) : new InputFile(file);
        return SendPhoto.builder()
                .chatId(chatId)
                .photo(inputFile)
                .caption(caption)
                .replyMarkup((returnInlineKeyboard != null) ? returnInlineKeyboard.getNextKeyboard(chatId) : null)
                .build();
    }

    public static class PhotoCommandResponseBuilder {
        private final Long chatId;
        private String identifier;
        private File file;
        private String caption;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public PhotoCommandResponseBuilder(Long chatId, String identifier) {
            this.chatId = chatId;
            this.identifier = identifier;
        }

        public PhotoCommandResponseBuilder(Long chatId, File file) {
            this.chatId = chatId;
            this.file = file;
        }

        public PhotoCommandResponseBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public PhotoCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public PhotoCommandResponse build() {
            return new PhotoCommandResponse(chatId, identifier, file, caption, returnInlineKeyboard);
        }
    }
}
