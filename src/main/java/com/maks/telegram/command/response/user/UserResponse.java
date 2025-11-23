package com.maks.telegram.command.response.user;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import static com.maks.telegram.command.response.user.UserResponseType.*;

@Getter
public class UserResponse {
    private SendMessage sendMessage;
    private EditMessageText editMessageText;
    private SendPhoto sendPhoto;
    private SendVideo sendVideo;
    private SendVideoNote sendVideoNote;
    private SendAudio sendAudio;
    private SendSticker sendSticker;
    private SendVoice sendVoice;
    private SendAnimation sendAnimation;
    private SendDocument sendDocument;
    private SendContact sendContact;
    private SendDice sendDice;
    private SendPoll sendPoll;
    private SendLocation sendLocation;
    private EditMessageMedia editMessageMedia;
    private final UserResponseType type;

    public UserResponse(SendMessage sendMessage) {
        this(SEND_MESSAGE);
        this.sendMessage = sendMessage;
    }

    public UserResponse(EditMessageText editMessageText) {
        this(EDIT_MESSAGE_TEXT);
        this.editMessageText = editMessageText;
    }

    public UserResponse(SendPhoto sendPhoto) {
        this(SEND_PHOTO);
        this.sendPhoto = sendPhoto;
    }

    public UserResponse(SendVideo sendVideo) {
        this(SEND_VIDEO);
        this.sendVideo = sendVideo;
    }

    public UserResponse(SendVideoNote sendVideoNote) {
        this(SEND_VIDEO_NOTE);
        this.sendVideoNote = sendVideoNote;
    }

    public UserResponse(SendSticker sendSticker) {
        this(SEND_STICKER);
        this.sendSticker = sendSticker;
    }

    public UserResponse(SendVoice sendVoice) {
        this(SEND_VOICE);
        this.sendVoice = sendVoice;
    }

    public UserResponse(SendAnimation sendAnimation) {
        this(SEND_ANIMATION);
        this.sendAnimation = sendAnimation;
    }

    public UserResponse(SendDocument sendDocument) {
        this(SEND_DOCUMENT);
        this.sendDocument = sendDocument;
    }

    public UserResponse(SendContact sendContact) {
        this(SEND_CONTACT);
        this.sendContact = sendContact;
    }

    public UserResponse(SendDice sendDice) {
        this(SEND_DICE);
        this.sendDice = sendDice;
    }

    public UserResponse(SendPoll sendPoll) {
        this(SEND_POLL);
        this.sendPoll = sendPoll;
    }

    public UserResponse(SendLocation sendLocation) {
        this(SEND_LOCATION);
        this.sendLocation = sendLocation;
    }

    public UserResponse(SendAudio sendAudio) {
        this(SEND_AUDIO);
        this.sendAudio = sendAudio;
    }

    public UserResponse(EditMessageMedia editMessageMedia) {
        this(EDIT_MESSAGE_MEDIA);
        this.editMessageMedia = editMessageMedia;
    }

    private UserResponse(UserResponseType type) {
        this.type = type;
    }
}
