package com.maks.telegram.command.response.command;

import com.maks.telegram.command.response.user.UserResponse;


public abstract class CommandResponse {
    protected final Long chatId;

    public CommandResponse(Long chatId) {
        this.chatId = chatId;
    }

    public abstract UserResponse generateUserResponse();
}
