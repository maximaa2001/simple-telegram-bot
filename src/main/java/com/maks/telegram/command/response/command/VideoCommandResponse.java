package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;

public class VideoCommandResponse extends MediaCommandResponse {
    private final Integer duration;

    public VideoCommandResponse(Long chatId, String identifier, File file, String caption,
                                ReturnInlineKeyboard returnInlineKeyboard, Integer duration) {
        super(chatId, identifier, file, caption, returnInlineKeyboard);
        this.duration = duration;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createVideo());
    }

    private SendVideo createVideo() {
        InputFile inputFile = (identifier != null) ? new InputFile(identifier) : new InputFile(file);
        return SendVideo.builder()
                .chatId(chatId)
                .video(inputFile)
                .caption(caption)
                .duration(duration)
                .replyMarkup((returnInlineKeyboard != null) ? returnInlineKeyboard.getNextKeyboard(chatId) : null)
                .build();
    }

    public static class VideoCommandResponseBuilder {
        private final Long chatId;
        private String identifier;
        private File file;
        private String caption;
        private Integer duration;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public VideoCommandResponseBuilder(Long chatId, String identifier) {
            this.chatId = chatId;
            this.identifier = identifier;
        }

        public VideoCommandResponseBuilder(Long chatId, File file) {
            this.chatId = chatId;
            this.file = file;
        }

        public VideoCommandResponseBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public VideoCommandResponseBuilder duration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public VideoCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public VideoCommandResponse build() {
            return new VideoCommandResponse(chatId, identifier, file, caption, returnInlineKeyboard, duration);
        }
    }
}
