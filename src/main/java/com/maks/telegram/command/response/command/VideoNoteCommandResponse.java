package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import org.telegram.telegrambots.meta.api.methods.send.SendVideoNote;

import java.io.File;

public class VideoNoteCommandResponse extends MediaCommandResponse {
    private final Integer duration;
    private final Integer length;

    public VideoNoteCommandResponse(Long chatId, String identifier, File file,
                                    ReturnInlineKeyboard returnInlineKeyboard, Integer duration, Integer length) {
        super(chatId, identifier, file, returnInlineKeyboard);
        this.duration = duration;
        this.length = length;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createVideoNote());
    }

    private SendVideoNote createVideoNote() {
        return SendVideoNote.builder()
                .chatId(chatId)
                .videoNote(defaultInputMedia())
                .duration(duration)
                .length(length)
                .replyMarkup((returnInlineKeyboard != null) ? returnInlineKeyboard.getNextKeyboard(chatId) : null)
                .build();
    }

    public static class VideoNoteCommandResponseBuilder {
        private final Long chatId;
        private String identifier;
        private File file;
        private Integer duration;
        private Integer length;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public VideoNoteCommandResponseBuilder(Long chatId, String identifier) {
            this.chatId = chatId;
            this.identifier = identifier;
        }

        public VideoNoteCommandResponseBuilder(Long chatId, File file) {
            this.chatId = chatId;
            this.file = file;
        }

        public VideoNoteCommandResponseBuilder duration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public VideoNoteCommandResponseBuilder length(Integer length) {
            this.length = length;
            return this;
        }

        public VideoNoteCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public VideoNoteCommandResponse build() {
            return new VideoNoteCommandResponse(chatId, identifier, file, returnInlineKeyboard, duration, length);
        }
    }
}
