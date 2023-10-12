package com.maks.telegram.command.factory;

import com.maks.telegram.command.AbstractCallbackMessageCommand;
import com.maks.telegram.command.AbstractMenuCommand;
import com.maks.telegram.command.Command;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandFactory(List<Command> commandsList) {
        for (Command command : commandsList) {
            if (command instanceof AbstractCallbackMessageCommand abstractCallbackMessageCommand) {
                abstractCallbackMessageCommand.getNames().forEach(e -> commands.put(e, command));
            } else {
                commands.put(command.getName(), command);
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

    public Collection<Command> getAllCommands() {
        return commands.values();
    }
}
