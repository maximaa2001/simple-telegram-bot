package com.maks.telegram.command.factory;

import com.maks.telegram.command.params.CommandParams;
import com.maks.telegram.exception.UnknownParamsException;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ParamsFactory {
    CommandParams convertToParams(Update update) throws UnknownParamsException;
}
