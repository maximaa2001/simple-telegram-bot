package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.meta.CallbackNameValidator;
import com.maks.telegram.meta.DiceType;
import org.telegram.telegrambots.meta.api.methods.send.SendDice;

public class DiceCommandResponse extends CommandResponse implements CallbackNameValidator {
    private final String emoji;
    private final ReturnInlineKeyboard returnInlineKeyboard;

    public DiceCommandResponse(Long chatId, DiceType diceType) {
        this(chatId, diceType.getEmoji(), null);
    }

    public DiceCommandResponse(Long chatId, DiceType diceType, ReturnInlineKeyboard returnInlineKeyboard) {
        this(chatId, diceType.getEmoji(), returnInlineKeyboard);
    }

    public DiceCommandResponse(Long chatId, String emoji, ReturnInlineKeyboard returnInlineKeyboard) {
        super(chatId);
        this.emoji = emoji;
        if (returnInlineKeyboard != null) {
            validate(returnInlineKeyboard.getNextCommandCallbacks(chatId));
        }
        this.returnInlineKeyboard = returnInlineKeyboard;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createDice());
    }

    private SendDice createDice() {
        return SendDice.builder()
                .chatId(chatId)
                .emoji(emoji)
                .replyMarkup((returnInlineKeyboard != null) ? returnInlineKeyboard.getNextKeyboard(chatId) : null)
                .build();
    }
}
