package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;

public class VoiceCommandResponse extends MediaCommandResponse {
    private final Integer duration;

    public VoiceCommandResponse(Long chatId, String identifier, File file, String caption,
                                ReturnInlineKeyboard returnInlineKeyboard, Integer duration) {
        super(chatId, identifier, file, caption, returnInlineKeyboard);
        this.duration = duration;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createVoice());
    }

    private SendVoice createVoice() {
        InputFile inputFile = (identifier != null) ? new InputFile(identifier) : new InputFile(file);
        return SendVoice.builder()
                .chatId(chatId)
                .voice(inputFile)
                .caption(caption)
                .duration(duration)
                .replyMarkup((returnInlineKeyboard != null) ? returnInlineKeyboard.getNextKeyboard(chatId) : null)
                .build();
    }

    public static class VoiceCommandResponseBuilder {
        private final Long chatId;
        private String identifier;
        private File file;
        private String caption;
        private Integer duration;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public VoiceCommandResponseBuilder(Long chatId, String identifier) {
            this.chatId = chatId;
            this.identifier = identifier;
        }

        public VoiceCommandResponseBuilder(Long chatId, File file) {
            this.chatId = chatId;
            this.file = file;
        }

        public VoiceCommandResponseBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public VoiceCommandResponseBuilder duration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public VoiceCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public VoiceCommandResponse build() {
            return new VoiceCommandResponse(chatId, identifier, file, caption, returnInlineKeyboard, duration);
        }
    }
}
