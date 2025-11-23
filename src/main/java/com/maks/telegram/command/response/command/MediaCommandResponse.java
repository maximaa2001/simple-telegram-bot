package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.meta.CallbackNameValidator;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;

public abstract class MediaCommandResponse extends CommandResponse implements CallbackNameValidator {
    protected final String identifier;
    protected final File file;
    protected final String caption;
    protected final ReturnInlineKeyboard returnInlineKeyboard;

    public MediaCommandResponse(Long chatId, String identifier, File file, ReturnInlineKeyboard returnInlineKeyboard) {
        this(chatId, identifier, file, null, returnInlineKeyboard);
    }

    public MediaCommandResponse(Long chatId, String identifier, File file, String caption, ReturnInlineKeyboard returnInlineKeyboard) {
        super(chatId);
        this.identifier = identifier;
        this.caption = caption;
        this.file = file;
        if (returnInlineKeyboard != null) {
            validate(returnInlineKeyboard.getNextCommandCallbacks(chatId));
        }
        this.returnInlineKeyboard = returnInlineKeyboard;
    }

    protected InputFile defaultInputMedia() {
        return (identifier != null) ? new InputFile(identifier) : new InputFile(file);
    }
}
