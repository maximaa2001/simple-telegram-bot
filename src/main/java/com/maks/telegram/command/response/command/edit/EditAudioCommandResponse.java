package com.maks.telegram.command.response.command.edit;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.command.response.user.UserResponseType;
import com.maks.telegram.util.StringUtils;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaAudio;

import java.io.File;

public class EditAudioCommandResponse extends EditMediaCommandResponse {
    private final String performer;
    private final String title;
    private final Integer duration;


    public EditAudioCommandResponse(Long chatId, Integer messageId, String identifier, File file,
                                    String caption, ReturnInlineKeyboard returnInlineKeyboard, String performer, String title,
                                    Integer duration) {
        super(chatId, messageId, identifier, file, caption, returnInlineKeyboard);
        this.performer = performer;
        this.title = title;
        this.duration = duration;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createEditMessageMedia());
    }

    @Override
    protected InputMedia createInputMedia(String media, File mediaFile, String mediaName, boolean isNewMedia) {
        return InputMediaAudio.builder()
                .media(media)
                .newMediaFile(mediaFile)
                .mediaName(mediaName)
                .isNewMedia(isNewMedia)
                .caption(caption)
                .performer(performer)
                .title(title)
                .duration(duration)
                .build();
    }

    public static class EditAudioCommandResponseBuilder {
        private final Long chatId;
        private final Integer messageId;
        private String identifier;
        private File file;
        private String caption;
        private String performer;
        private String title;
        private Integer duration;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public EditAudioCommandResponseBuilder(Long chatId, Integer messageId, String identifier) {
            this.chatId = chatId;
            this.messageId = messageId;
            this.identifier = identifier;
        }

        public EditAudioCommandResponseBuilder(Long chatId, Integer messageId, File file) {
            this.chatId = chatId;
            this.messageId = messageId;
            this.file = file;
        }

        public EditAudioCommandResponseBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public EditAudioCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public EditAudioCommandResponseBuilder duration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public EditAudioCommandResponseBuilder performer(String performer) {
            this.performer = performer;
            return this;
        }

        public EditAudioCommandResponseBuilder title(String title) {
            this.title = title;
            return this;
        }

        public EditAudioCommandResponse build() {
            return new EditAudioCommandResponse(chatId, messageId, identifier, file, caption,
                    returnInlineKeyboard, performer, title, duration);
        }
    }
}
