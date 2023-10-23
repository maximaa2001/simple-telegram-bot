package com.maks.telegram.command.response.command.edit;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.util.StringUtils;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaAnimation;

import java.io.File;

public class EditAnimationCommandResponse extends EditMediaCommandResponse {
    private final Integer duration;
    private final Integer width;
    private final Integer height;

    public EditAnimationCommandResponse(Long chatId, Integer messageId, String identifier, File file,
                                        String caption, ReturnInlineKeyboard returnInlineKeyboard,
                                        Integer duration, Integer width, Integer height) {
        super(chatId, messageId, identifier, file, caption, returnInlineKeyboard);
        this.duration = duration;
        this.width = width;
        this.height = height;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createEditMessageMedia());
    }

    @Override
    protected InputMedia createInputMedia(String media, File mediaFile, String mediaName, boolean isNewMedia) {
        return InputMediaAnimation.builder()
                .media(media)
                .newMediaFile(mediaFile)
                .mediaName(mediaName)
                .isNewMedia(isNewMedia)
                .caption(caption)
                .duration(duration)
                .width(width)
                .height(height)
                .build();
    }

    public static class EditAnimationCommandResponseBuilder {
        private final Long chatId;
        private final Integer messageId;
        private String identifier;
        private File file;
        private String caption;
        private Integer duration;
        private Integer width;
        private Integer height;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public EditAnimationCommandResponseBuilder(Long chatId, Integer messageId, String identifier) {
            this.chatId = chatId;
            this.messageId = messageId;
            this.identifier = identifier;
        }

        public EditAnimationCommandResponseBuilder(Long chatId, Integer messageId, File file) {
            this.chatId = chatId;
            this.messageId = messageId;
            this.file = file;
        }

        public EditAnimationCommandResponseBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public EditAnimationCommandResponseBuilder duration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public EditAnimationCommandResponseBuilder width(Integer width) {
            this.width = width;
            return this;
        }

        public EditAnimationCommandResponseBuilder height(Integer height) {
            this.height = height;
            return this;
        }


        public EditAnimationCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public EditAnimationCommandResponse build() {
            return new EditAnimationCommandResponse(chatId, messageId, identifier, file, caption, returnInlineKeyboard, duration, width, height);
        }
    }
}
