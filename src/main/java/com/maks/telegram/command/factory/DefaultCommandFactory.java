package com.maks.telegram.command.factory;

import com.maks.telegram.command.AbstractCallbackMessageCommand;
import com.maks.telegram.command.AbstractMenuCommand;
import com.maks.telegram.command.Command;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultCommandFactory implements CommandFactory {
    private final Map<String, Command> commands = new HashMap<>();

    public DefaultCommandFactory(List<Command> commandsList) {
        if (commandsList != null) {
            for (Command command : commandsList) {
                if (command instanceof AbstractCallbackMessageCommand abstractCallbackMessageCommand) {
                    abstractCallbackMessageCommand.getNames().forEach(e -> commands.put(e, command));
                } else {
                    commands.put(command.getName(), command);
                }
            }
        }
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    public Collection<AbstractMenuCommand> getAllMenuCommands() {
        return getAllCommands().stream()
                .filter(e -> e instanceof AbstractMenuCommand)
                .map(e -> (AbstractMenuCommand) e)
                .toList();
    }

    private Collection<Command> getAllCommands() {
        return commands.values();
    }
}
