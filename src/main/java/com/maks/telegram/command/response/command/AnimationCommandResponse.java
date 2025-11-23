package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;

public class AnimationCommandResponse extends MediaCommandResponse {
    private final Integer duration;
    private final Integer width;
    private final Integer height;

    public AnimationCommandResponse(Long chatId, String identifier, File file, String caption, ReturnInlineKeyboard returnInlineKeyboard,
                                    Integer duration, Integer width, Integer height) {
        super(chatId, identifier, file, caption, returnInlineKeyboard);
        this.duration = duration;
        this.width = width;
        this.height = height;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createAnimation());
    }

    private SendAnimation createAnimation() {
        InputFile inputFile = (identifier != null) ? new InputFile(identifier) : new InputFile(file);
        return SendAnimation.builder()
                .chatId(chatId)
                .animation(inputFile)
                .duration(duration)
                .width(width)
                .height(height)
                .caption(caption)
                .replyMarkup((returnInlineKeyboard != null) ? returnInlineKeyboard.getNextKeyboard(chatId) : null)
                .build();
    }

    public static class AnimationCommandResponseBuilder {
        private final Long chatId;
        private String identifier;
        private File file;
        private String caption;
        private Integer duration;
        private Integer width;
        private Integer height;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public AnimationCommandResponseBuilder(Long chatId, String identifier) {
            this.chatId = chatId;
            this.identifier = identifier;
        }

        public AnimationCommandResponseBuilder(Long chatId, File file) {
            this.chatId = chatId;
            this.file = file;
        }

        public AnimationCommandResponseBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public AnimationCommandResponseBuilder duration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public AnimationCommandResponseBuilder width(Integer width) {
            this.width = width;
            return this;
        }

        public AnimationCommandResponseBuilder height(Integer height) {
            this.height = height;
            return this;
        }

        public AnimationCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public AnimationCommandResponse build() {
            return new AnimationCommandResponse(chatId, identifier, file, caption, returnInlineKeyboard, duration, width, height);
        }
    }
}
