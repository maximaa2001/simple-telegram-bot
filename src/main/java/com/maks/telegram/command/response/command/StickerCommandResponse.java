package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.meta.CallbackNameValidator;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;

public class StickerCommandResponse extends CommandResponse implements CallbackNameValidator {
    private final String identifier;
    private final File file;
    private final String emoji;
    private final ReturnInlineKeyboard returnInlineKeyboard;

    public StickerCommandResponse(Long chatId, String identifier, File file, String emoji,
                                  ReturnInlineKeyboard returnInlineKeyboard) {
        super(chatId);
        this.identifier = identifier;
        this.file = file;
        this.emoji = emoji;
        if (returnInlineKeyboard != null) {
            validate(returnInlineKeyboard.getNextCommandCallbacks(chatId));
        }
        this.returnInlineKeyboard = returnInlineKeyboard;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createSticker());
    }

    private SendSticker createSticker() {
        InputFile inputFile = (identifier != null) ? new InputFile(identifier) : new InputFile(file);
        return SendSticker.builder()
                .chatId(chatId)
                .sticker(inputFile)
                .emoji(emoji)
                .replyMarkup((returnInlineKeyboard != null) ? returnInlineKeyboard.getNextKeyboard(chatId) : null)
                .build();
    }

    public static class StickerCommandResponseBuilder {
        private final Long chatId;
        private String identifier;
        private File file;
        private String emoji;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public StickerCommandResponseBuilder(Long chatId, String identifier) {
            this.chatId = chatId;
            this.identifier = identifier;
        }

        public StickerCommandResponseBuilder(Long chatId, File file) {
            this.chatId = chatId;
            this.file = file;
        }

        public StickerCommandResponseBuilder emoji(String emoji) {
            this.emoji = emoji;
            return this;
        }

        public StickerCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public StickerCommandResponse build() {
            return new StickerCommandResponse(chatId, identifier, file, emoji, returnInlineKeyboard);
        }
    }
}
