package com.maks.telegram.command.factory;

import com.maks.telegram.command.AbstractMenuCommand;
import com.maks.telegram.command.Command;

import java.util.Collection;

public interface CommandFactory {
    Command getCommand(String commandName);
    Collection<AbstractMenuCommand> getAllMenuCommands();
}
