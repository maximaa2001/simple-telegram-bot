package com.maks.telegram.command.response.command;


import com.maks.telegram.command.params.CommandParams;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.command.response.user.UserResponseType;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class MessageCommandResponse extends CommandResponse {
    private final String text;

    public MessageCommandResponse(CommandParams commandParams, String text) {
        super(commandParams);
        this.text = text;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createMessage(), UserResponseType.SEND_MESSAGE);
    }

    private SendMessage createMessage() {
        return new SendMessage(String.valueOf(chatId), text);
    }
}
