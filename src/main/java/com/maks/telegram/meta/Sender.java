package com.maks.telegram.meta;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Sender {
    void send() throws TelegramApiException;
}
