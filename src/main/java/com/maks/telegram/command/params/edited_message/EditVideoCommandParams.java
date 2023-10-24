package com.maks.telegram.command.params.edited_message;

import com.maks.telegram.command.constant.CommandConstant;
import org.telegram.telegrambots.meta.api.objects.Update;

public class EditVideoCommandParams extends EditMediaCommandParams {
    public EditVideoCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        params.put(NEW_VIDEO, update.getEditedMessage().getVideo());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return CommandConstant.EDIT_VIDEO;
    }
}
