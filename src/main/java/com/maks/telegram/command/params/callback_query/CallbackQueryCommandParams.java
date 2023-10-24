package com.maks.telegram.command.params.callback_query;

import com.maks.telegram.command.params.CommandParams;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CallbackQueryCommandParams extends CommandParams {
    public CallbackQueryCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        params.put(FROM_USER, callbackQuery.getFrom());
        params.put(CHAT_ID, callbackQuery.getFrom().getId());
        params.put(MESSAGE_ID, callbackQuery.getMessage().getMessageId());
        params.put(CALLBACK_DATA, callbackQuery.getData());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return update.getCallbackQuery().getData();
    }
}
