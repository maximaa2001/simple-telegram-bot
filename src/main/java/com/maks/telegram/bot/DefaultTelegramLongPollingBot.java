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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collection;
import java.util.List;

public class DefaultTelegramLongPollingBot extends TelegramLongPollingBot {
    private final String username;
    private final CommandFactory commandFactory;
    private final ParamsFactory paramsFactory;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public DefaultTelegramLongPollingBot(String username, String token, CommandFactory commandFactory,
                                         ParamsFactory paramsFactory) {
        super(token);
        this.username = username;
        this.commandFactory = commandFactory;
        this.paramsFactory = paramsFactory;
        List<BotCommand> menuCommands = createMenuCommands(commandFactory.getAllMenuCommands());
        if (menuCommands != null && !menuCommands.isEmpty()) {
            try {
                execute(new SetMyCommands(menuCommands, new BotCommandScopeDefault(), null));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
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
                logger.debug("command {}", command.getClass());
                UserResponse userResponse = command.execute(params);
                switch (userResponse.getType()) {
                    case SEND_MESSAGE -> sendMessage(() -> execute(userResponse.getSendMessage()));
                    case EDIT_MESSAGE_TEXT -> sendMessage(() -> execute(userResponse.getEditMessageText()));
                    case SEND_PHOTO -> sendMessage(() -> execute(userResponse.getSendPhoto()));
                    case SEND_VIDEO -> sendMessage(() -> execute(userResponse.getSendVideo()));
                    case SEND_AUDIO -> sendMessage(() -> execute(userResponse.getSendAudio()));
                    case SEND_STICKER -> sendMessage(() -> execute(userResponse.getSendSticker()));
                    case SEND_VOICE -> sendMessage(() -> execute(userResponse.getSendVoice()));
                    case SEND_ANIMATION -> sendMessage(() -> execute(userResponse.getSendAnimation()));
                    case SEND_DOCUMENT -> sendMessage(() -> execute(userResponse.getSendDocument()));
                    case SEND_VIDEO_NOTE -> sendMessage(() -> execute(userResponse.getSendVideoNote()));
                    case SEND_CONTACT -> sendMessage(() -> execute(userResponse.getSendContact()));
                    case SEND_DICE -> sendMessage(() -> execute(userResponse.getSendDice()));
                    case SEND_POLL -> sendMessage(() -> execute(userResponse.getSendPoll()));
                    case SEND_LOCATION -> sendMessage(() -> execute(userResponse.getSendLocation()));
                    case EDIT_MESSAGE_MEDIA -> sendMessage(() -> execute(userResponse.getEditMessageMedia()));
                    default -> logger.warn("type {}", userResponse.getType());
                }
            } else {
                logger.warn("command {} is not exists", params.getInvokedCommand());
            }

        } catch (UnknownParamsException e) {
            logger.warn(e.getMessage());
        }
    }

    public void sendMessage(Sender sender) {
        try {
            sender.send();
        } catch (TelegramApiException e) {
            logger.error("error to send message", e);
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
