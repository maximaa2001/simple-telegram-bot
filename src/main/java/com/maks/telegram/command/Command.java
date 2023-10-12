package com.maks.telegram.command;

import com.maks.telegram.command.params.CommandParams;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.meta.Nameable;


public interface Command extends Nameable {
    UserResponse execute(CommandParams commandParams);
}
