package com.maks.telegram.command.factory;

import com.maks.telegram.command.params.CallbackQueryCommandParams;
import com.maks.telegram.command.params.CommandParams;
import com.maks.telegram.command.params.message.*;
import com.maks.telegram.exception.UnknownParamsException;
import com.maks.telegram.util.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ParamsFactory {
    public CommandParams convertToParams(Update update) throws UnknownParamsException {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.getPhoto() != null) {
                return new PhotoCommandParams(update);
            } else if (message.getVideo() != null) {
                return new VideoCommandParams(update);
            } else if (message.getAudio() != null) {
                return new AudioCommandParams(update);
            } else if (message.getSticker() != null) {
                return new StickerCommandParams(update);
            } else if (message.getVoice() != null) {
                return new VoiceCommandParams(update);
            } else if (message.getAnimation() != null) {
                return new AnimationCommandParams(update);
            } else if (message.getDocument() != null) {
                return new DocumentCommandParams(update);
            } else if (message.getVideoNote() != null) {
                return new VideoNoteCommandParams(update);
            } else if (message.getContact() != null) {
                return new ContactCommandParams(update);
            } else if (message.getDice() != null) {
                return new DiceCommandParams(update);
            } else if (message.getText() != null) {
                return new MessageCommandParams(update);
            }
        } else if (update.hasCallbackQuery()) {
            return new CallbackQueryCommandParams(update);
        }
        throw new UnknownParamsException(StringUtils.createString("Unknown params ", update.toString()));
    }
}
