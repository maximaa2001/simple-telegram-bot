package com.maks.telegram.command.response.command;


import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.params.CommandParams;

public abstract class PhotoCallbackQueryCommandResponse extends CallbackQueryCommandResponse {
    protected final String imageUrl;

    public PhotoCallbackQueryCommandResponse(String imageUrl, CommandParams commandParams,
                                             String text, ReturnInlineKeyboard returnInlineKeyboard) {
        super(commandParams, text, returnInlineKeyboard);
        this.imageUrl = imageUrl;
    }
}
