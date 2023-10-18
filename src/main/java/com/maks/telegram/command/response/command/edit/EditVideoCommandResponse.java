package com.maks.telegram.command.response.command.edit;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaVideo;

import java.io.File;

public class EditVideoCommandResponse extends EditMediaCommandResponse {
    private final Integer duration;

    public EditVideoCommandResponse(Long chatId, Integer messageId, String identifier, File file,
                                    String caption, ReturnInlineKeyboard returnInlineKeyboard, Integer duration) {
        super(chatId, messageId, identifier, file, caption, returnInlineKeyboard);
        this.duration = duration;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createEditMessageMedia());
    }

    @Override
    protected InputMedia createInputMedia() {
        return InputMediaVideo.builder()
                .media(identifier)
                .newMediaFile(file)
                .caption(caption)
                .duration(duration)
                .build();
    }

    public static class EditVideoCommandResponseBuilder {
        private final Long chatId;
        private final Integer messageId;
        private String identifier;
        private File file;
        private String caption;
        private Integer duration;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public EditVideoCommandResponseBuilder(Long chatId, Integer messageId, String identifier) {
            this.chatId = chatId;
            this.messageId = messageId;
            this.identifier = identifier;
        }

        public EditVideoCommandResponseBuilder(Long chatId, Integer messageId, File file) {
            this.chatId = chatId;
            this.messageId = messageId;
            this.file = file;
        }

        public EditVideoCommandResponseBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public EditVideoCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public EditVideoCommandResponseBuilder duration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public EditVideoCommandResponse build() {
            return new EditVideoCommandResponse(chatId, messageId, identifier, file, caption, returnInlineKeyboard, duration);
        }
    }
}
