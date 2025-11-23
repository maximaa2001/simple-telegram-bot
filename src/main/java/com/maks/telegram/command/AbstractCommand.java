package com.maks.telegram.command;

import com.maks.telegram.command.params.CommandParams;
import com.maks.telegram.command.response.command.CommandResponse;
import com.maks.telegram.command.response.user.UserResponse;

public abstract class AbstractCommand implements Command {
    private final String NAME;

    public AbstractCommand(String NAME) {
        this.NAME = NAME;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public UserResponse execute(CommandParams commandParams) {
        CommandResponse commandResponse = transformToCommandResponse(commandParams);
        return commandResponse.generateUserResponse();
    }

    protected abstract CommandResponse transformToCommandResponse(CommandParams commandParams);
}
