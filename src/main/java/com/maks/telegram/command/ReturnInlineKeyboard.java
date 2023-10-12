package com.maks.telegram.command;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public interface ReturnInlineKeyboard {

    default InlineKeyboardMarkup createSimpleKeyboard(int rows, int buttonsInRow, Long chatId) {
        List<String> texts = getNextCommandTexts(chatId);
        List<String> callbacks = getNextCommandCallbacks(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            for (int j = 0; j < buttonsInRow; j++) {
                InlineKeyboardButton button = new InlineKeyboardButton();
                int number = i * buttonsInRow + j;
                button.setText(texts.get(number));
                button.setCallbackData(callbacks.get(number));
                row.add(button);
            }
            rowsInLine.add(row);
        }
        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        return inlineKeyboardMarkup;
    }

    InlineKeyboardMarkup getNextKeyboard(Long chatId);
    List<String> getNextCommandTexts(Long chatId);
    List<String> getNextCommandCallbacks(Long chatId);
}
