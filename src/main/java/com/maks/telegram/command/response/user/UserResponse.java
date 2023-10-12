package com.maks.telegram.command.response.user;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Getter
public class UserResponse {
    private SendMessage sendMessage;
    private EditMessageText editMessageText;
    private SendPhoto sendPhoto;
    private EditMessageMedia editMessageMedia;
    private DeleteMessage deleteMessage;
    private final UserResponseType type;

    public UserResponse(SendMessage sendMessage, UserResponseType type) {
        this(type);
        this.sendMessage = sendMessage;
    }

    public UserResponse(EditMessageText editMessageText, UserResponseType type) {
        this(type);
        this.editMessageText = editMessageText;
    }

    public UserResponse(DeleteMessage deleteMessage, SendPhoto sendPhoto, UserResponseType type) {
        this(type);
        this.deleteMessage = deleteMessage;
        this.sendPhoto = sendPhoto;
    }

    public UserResponse(EditMessageMedia editMessageMedia, UserResponseType type) {
        this(type);
        this.editMessageMedia = editMessageMedia;
    }

    private UserResponse(UserResponseType type) {
        this.type = type;
    }
}
