package com.maks.telegram.command.response.command.edit;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.meta.CallbackNameValidator;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;

import java.io.File;

public abstract class EditMediaCommandResponse extends EditCommandResponse implements CallbackNameValidator {
    protected final String identifier;
    protected final File file;
    protected final String caption;
    protected final ReturnInlineKeyboard returnInlineKeyboard;

    public EditMediaCommandResponse(Long chatId, Integer messageId, String identifier, File file,
                                    String caption, ReturnInlineKeyboard returnInlineKeyboard) {
        super(chatId, messageId);
        this.identifier = identifier;
        this.caption = caption;
        this.file = file;
        if (returnInlineKeyboard != null) {
            validate(returnInlineKeyboard.getNextCommandCallbacks(chatId));
        }
        this.returnInlineKeyboard = returnInlineKeyboard;
    }

    protected EditMessageMedia createEditMessageMedia() {
        return EditMessageMedia.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup((returnInlineKeyboard != null) ? returnInlineKeyboard.getNextKeyboard(chatId) : null)
                .media(createInputMedia())
                .build();
    }

    abstract protected InputMedia createInputMedia();
}
