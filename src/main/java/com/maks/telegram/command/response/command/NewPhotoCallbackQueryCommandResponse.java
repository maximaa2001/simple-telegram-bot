package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.params.CommandParams;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.command.response.user.UserResponseType;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;

public class NewPhotoCallbackQueryCommandResponse extends PhotoCallbackQueryCommandResponse {
    public NewPhotoCallbackQueryCommandResponse(String imageUrl, CommandParams commandParams, String text, ReturnInlineKeyboard returnInlineKeyboard) {
        super(imageUrl, commandParams, text, returnInlineKeyboard);
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createDeleteMessage(), createPhoto(), UserResponseType.SEND_PHOTO);
    }

    private DeleteMessage createDeleteMessage() {
        return new DeleteMessage(String.valueOf(chatId), update.getCallbackQuery().getMessage().getMessageId());
    }

    private SendPhoto createPhoto() {
        return SendPhoto.builder()
                .chatId(chatId)
                .photo(new InputFile(imageUrl))
                .caption(text)
                .replyMarkup(returnInlineKeyboard.getNextKeyboard(chatId))
                .build();
    }
}
