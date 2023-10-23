package com.maks.telegram.command.response.command.edit;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.meta.CallbackNameValidator;
import com.maks.telegram.util.StringUtils;
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
                .media(resolverMedia())
                .build();
    }

    private InputMedia resolverMedia() {
        String media = null;
        File mediaFile = null;
        String mediaName = null;
        boolean isNewMedia = false;
        if (identifier != null) {
            media = identifier;
        } else {
            media = StringUtils.createString("attach://", file.getName());
            mediaFile = file;
            mediaName = file.getName();
            isNewMedia = true;
        }
        return createInputMedia(media, mediaFile, mediaName, isNewMedia);
    }

    abstract protected InputMedia createInputMedia(String media, File mediaFile, String mediaName, boolean isNewMedia);
}
