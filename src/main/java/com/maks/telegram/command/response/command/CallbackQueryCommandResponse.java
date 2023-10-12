package com.maks.telegram.command.response.command;


import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.params.CommandParams;
import com.maks.telegram.exception.CallbackNameValidationException;
import com.maks.telegram.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class CallbackQueryCommandResponse extends CommandResponse {
    private static final Integer MAX_BYTES_FOR_CALLBACK_BUTTON = 64;

    protected final String text;
    protected final ReturnInlineKeyboard returnInlineKeyboard;

    public CallbackQueryCommandResponse(CommandParams commandParams, String text, ReturnInlineKeyboard returnInlineKeyboard) {
        super(commandParams);
        this.text = text;
        this.returnInlineKeyboard = validate(returnInlineKeyboard);
    }

    private ReturnInlineKeyboard validate(ReturnInlineKeyboard returnInlineKeyboard) throws CallbackNameValidationException {
        List<String> callbackNames = returnInlineKeyboard.getNextCommandCallbacks(chatId);
        callbackNames.forEach(e -> {
            byte[] bytes = e.getBytes(StandardCharsets.UTF_8);
            if (bytes.length > MAX_BYTES_FOR_CALLBACK_BUTTON) {
                throw new CallbackNameValidationException(StringUtils.createString("Callback name must not have more than ",
                        String.valueOf(MAX_BYTES_FOR_CALLBACK_BUTTON), " bytes"));
            }
        });
        return returnInlineKeyboard;
    }
}
