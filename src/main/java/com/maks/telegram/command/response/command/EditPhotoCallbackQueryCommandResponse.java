package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.params.CommandParams;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.command.response.user.UserResponseType;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

public class EditPhotoCallbackQueryCommandResponse extends PhotoCallbackQueryCommandResponse {

    public EditPhotoCallbackQueryCommandResponse(String imageUrl, CommandParams commandParams, String text, ReturnInlineKeyboard returnInlineKeyboard) {
        super(imageUrl, commandParams, text, returnInlineKeyboard);
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createEditMessageMedia(), UserResponseType.EDIT_MESSAGE_MEDIA);
    }

    private EditMessageMedia createEditMessageMedia() {
        return EditMessageMedia.builder()
                .chatId(chatId)
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(returnInlineKeyboard.getNextKeyboard(chatId))
                .media(InputMediaPhoto.builder()
                        .media(imageUrl)
                        .caption(text)
                        .build())
                .build();
    }
}
