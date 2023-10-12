package com.maks.telegram.command.params;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

public abstract class CommandParams {
    public static final String CHAT_ID = "CHAT_ID";
    protected final Map<String, String> params = new HashMap<>();
    @Getter
    protected final String invokedCommand;
    @Getter
    protected final Update update;

    public CommandParams(Update update) {
        this.invokedCommand = getInvokedCommandName(update);
        this.update = update;
        initParams(update);
    }

    public String getParam(String name) {
        return params.get(name);
    }

    public void setDynamicData(String dynamicData) {
        params.put(CallbackQueryCommandParams.DYNAMIC_DATA, dynamicData);
    }

    protected abstract void initParams(Update update);

    protected abstract String getInvokedCommandName(Update update);
}