package com.maks.telegram.command.response.command.edit;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.util.StringUtils;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

import java.io.File;

public class EditPhotoCommandResponse extends EditMediaCommandResponse {
    public EditPhotoCommandResponse(Long chatId, Integer messageId, String identifier, File file, String caption, ReturnInlineKeyboard returnInlineKeyboard) {
        super(chatId, messageId, identifier, file, caption, returnInlineKeyboard);
    }


    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createEditMessageMedia());
    }

    @Override
    protected InputMedia createInputMedia() {
        String media = null;
        File mediaFile = null;
        String mediaName = null;
        boolean isNewMedia = false;
        if (identifier != null) {
            media = identifier;
        } else {
            media = StringUtils.createString("attach://", file.getName());
            mediaFile = file;
            mediaName = file.getName();
            isNewMedia = true;
        }
        return InputMediaPhoto.builder()
                .media(media)
                .newMediaFile(mediaFile)
                .mediaName(mediaName)
                .isNewMedia(isNewMedia)
                .caption(caption)
                .build();
    }


    public static class EditPhotoCommandResponseBuilder {
        private final Long chatId;
        private final Integer messageId;
        private String identifier;
        private File file;
        private String caption;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public EditPhotoCommandResponseBuilder(Long chatId, Integer messageId, String identifier) {
            this.chatId = chatId;
            this.messageId = messageId;
            this.identifier = identifier;
        }

        public EditPhotoCommandResponseBuilder(Long chatId, Integer messageId, File file) {
            this.chatId = chatId;
            this.messageId = messageId;
            this.file = file;
        }

        public EditPhotoCommandResponseBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public EditPhotoCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public EditPhotoCommandResponse build() {
            return new EditPhotoCommandResponse(chatId, messageId, identifier, file, caption, returnInlineKeyboard);
        }
    }
}
