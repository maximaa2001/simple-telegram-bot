package com.maks.telegram.command.response.command;


import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.params.CommandParams;

public abstract class CallbackQueryCommandResponse extends CommandResponse {
    protected final String text;
    protected final ReturnInlineKeyboard returnInlineKeyboard;

    public CallbackQueryCommandResponse(CommandParams commandParams, String text, ReturnInlineKeyboard returnInlineKeyboard) {
        super(commandParams);
        this.text = text;
        this.returnInlineKeyboard = returnInlineKeyboard;
    }
}
