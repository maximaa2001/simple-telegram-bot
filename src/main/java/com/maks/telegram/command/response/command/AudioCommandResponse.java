package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;

public class AudioCommandResponse extends MediaCommandResponse {
    private final Integer duration;
    private final String performer;
    private final String title;

    public AudioCommandResponse(Long chatId, String identifier, File file, String caption,
                                ReturnInlineKeyboard returnInlineKeyboard, Integer duration, String performer,
                                String title) {
        super(chatId, identifier, file, caption, returnInlineKeyboard);
        this.duration = duration;
        this.performer = performer;
        this.title = title;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createAudio());
    }

    private SendAudio createAudio() {
        InputFile inputFile = (identifier != null) ? new InputFile(identifier) : new InputFile(file);
        return SendAudio.builder()
                .chatId(chatId)
                .audio(inputFile)
                .caption(caption)
                .duration(duration)
                .replyMarkup((returnInlineKeyboard != null) ? returnInlineKeyboard.getNextKeyboard(chatId) : null)
                .performer(performer)
                .title(title)
                .build();
    }

    public static class AudioCommandResponseBuilder {
        private final Long chatId;
        private String identifier;
        private File file;
        private String caption;
        private Integer duration;
        private String performer;
        private String title;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public AudioCommandResponseBuilder(Long chatId, String identifier) {
            this.chatId = chatId;
            this.identifier = identifier;
        }

        public AudioCommandResponseBuilder(Long chatId, File file) {
            this.chatId = chatId;
            this.file = file;
        }

        public AudioCommandResponseBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public AudioCommandResponseBuilder duration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public AudioCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public AudioCommandResponseBuilder performer(String performer) {
            this.performer = performer;
            return this;
        }

        public AudioCommandResponseBuilder title(String title) {
            this.title = title;
            return this;
        }

        public AudioCommandResponse build() {
            return new AudioCommandResponse(chatId, identifier, file, caption, returnInlineKeyboard, duration, performer, title);
        }
    }
}
