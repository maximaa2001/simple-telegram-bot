package com.maks.telegram.command.params.edited_message;

import com.maks.telegram.command.constant.CommandConstant;
import org.telegram.telegrambots.meta.api.objects.Update;

public class EditMessageCommandParams extends EditCommandParams {

    public EditMessageCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        params.put(NEW_TEXT, update.getEditedMessage().getText());
    }


    @Override
    protected String getInvokedCommandName(Update update) {
        return CommandConstant.EDIT_TEXT;
    }
}
