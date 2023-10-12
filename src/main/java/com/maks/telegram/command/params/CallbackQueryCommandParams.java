package com.maks.telegram.command.params;

import org.telegram.telegrambots.meta.api.objects.Update;

public class CallbackQueryCommandParams extends CommandParams {
    public static final String MESSAGE_ID = "MESSAGE_ID";
    public static final String CALLBACK_DATA = "CALLBACK_DATA";
    public static final String DYNAMIC_DATA = "DYNAMIC_DATA";

    public CallbackQueryCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        params.put(CHAT_ID, String.valueOf(update.getCallbackQuery().getFrom().getId()));
        params.put(MESSAGE_ID, String.valueOf(update.getCallbackQuery().getMessage().getMessageId()));
        params.put(CALLBACK_DATA, update.getCallbackQuery().getData());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return update.getCallbackQuery().getData();
    }
}
