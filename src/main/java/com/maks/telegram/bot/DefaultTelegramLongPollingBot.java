package com.maks.telegram.bot;

import com.maks.telegram.command.AbstractMenuCommand;
import com.maks.telegram.command.Command;
import com.maks.telegram.command.DynamicCommand;
import com.maks.telegram.command.factory.CommandFactory;
import com.maks.telegram.command.factory.ParamsFactory;
import com.maks.telegram.command.params.CommandParams;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.exception.UnknownParamsException;
import com.maks.telegram.meta.Sender;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collection;
import java.util.List;

@Slf4j
public class DefaultTelegramLongPollingBot extends TelegramLongPollingBot {
    private final String username;
    private final CommandFactory commandFactory;
    private final ParamsFactory paramsFactory;

    public DefaultTelegramLongPollingBot(String username, String token, CommandFactory commandFactory,
                                         ParamsFactory paramsFactory) {
        super(token);
        this.username = username;
        this.commandFactory = commandFactory;
        this.paramsFactory = paramsFactory;
        List<BotCommand> menuCommands = createMenuCommands(commandFactory.getAllMenuCommands());
        try {
            execute(new SetMyCommands(menuCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            CommandParams params = paramsFactory.convertToParams(update);
            Command command = commandFactory.getCommand(params.getInvokedCommand());
            if (command == null) {
                command = commandFactory.getCommand(params.getInvokedCommand().split(DynamicCommand.DELIMITER)[0]);
            }
            if (command != null) {
                log.debug("command {}", command.getClass());
                UserResponse userResponse = command.execute(params);
                if (userResponse.getDeleteMessage() != null) {
                    sendMessage(() -> execute(userResponse.getDeleteMessage()));
                }
                switch (userResponse.getType()) {
                    case SEND_MESSAGE -> sendMessage(() -> execute(userResponse.getSendMessage()));
                    case EDIT_MESSAGE_TEXT -> sendMessage(() -> execute(userResponse.getEditMessageText()));
                    case SEND_PHOTO -> sendMessage(() -> execute(userResponse.getSendPhoto()));
                    case EDIT_MESSAGE_MEDIA -> sendMessage(() -> execute(userResponse.getEditMessageMedia()));
                    default -> log.warn("type {}", userResponse.getType());
                }
            } else {
                log.warn("command {} is not exists", params.getInvokedCommand());
            }

        } catch (UnknownParamsException e) {
            log.warn(e.getMessage());
        }
    }

    public void sendMessage(Sender sender) {
        try {
            sender.send();
        } catch (TelegramApiException e) {
            log.error("error to send message", e);
            throw new RuntimeException(e);
        }
    }

    private List<BotCommand> createMenuCommands(Collection<AbstractMenuCommand> commands) {
        return commands
                .stream()
                .map(e -> new BotCommand(e.getName(), e.getDescription()))
                .toList();
    }

    @Override
    public String getBotUsername() {
        return username;
    }
}
