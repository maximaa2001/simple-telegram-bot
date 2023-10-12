package com.maks.telegram.command.response.command;

import com.maks.telegram.command.params.CommandParams;
import com.maks.telegram.command.response.user.UserResponse;
import org.telegram.telegrambots.meta.api.objects.Update;


public abstract class CommandResponse {
    protected final Update update;
    protected final Long chatId;

    public CommandResponse(CommandParams commandParams) {
        this.update = commandParams.getUpdate();
        this.chatId = Long.parseLong(commandParams.getParam(CommandParams.CHAT_ID));
    }

    public abstract UserResponse generateUserResponse();
}
