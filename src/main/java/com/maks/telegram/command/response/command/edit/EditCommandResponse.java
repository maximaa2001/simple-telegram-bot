package com.maks.telegram.command.response.command.edit;

import com.maks.telegram.command.response.command.CommandResponse;

public abstract class EditCommandResponse extends CommandResponse {
    protected Integer messageId;

    public EditCommandResponse(Long chatId, Integer messageId) {
        super(chatId);
        this.messageId = messageId;
    }
}
