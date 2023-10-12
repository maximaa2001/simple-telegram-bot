package com.maks.telegram.command.factory;

import com.maks.telegram.command.params.CallbackQueryCommandParams;
import com.maks.telegram.command.params.CommandParams;
import com.maks.telegram.command.params.MessageCommandParams;
import com.maks.telegram.exception.UnknownParamsException;
import com.maks.telegram.util.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ParamsFactory {
    public CommandParams convertToParams(Update update) throws UnknownParamsException {
        if (update.hasMessage()) {
            return new MessageCommandParams(update);
        } else if (update.hasCallbackQuery()) {
            return new CallbackQueryCommandParams(update);
        }
        throw new UnknownParamsException(StringUtils.createString("Unknown params ", update.toString()));
    }
}
