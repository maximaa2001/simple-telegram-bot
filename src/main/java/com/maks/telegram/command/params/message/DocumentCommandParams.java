package com.maks.telegram.command.params.message;

import com.maks.telegram.command.constant.CommandConstant;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DocumentCommandParams extends MediaCommandParams {
    public DocumentCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Document document = update.getMessage().getDocument();
        params.put(DOCUMENT, document);
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return CommandConstant.GET_DOCUMENT;
    }
}
