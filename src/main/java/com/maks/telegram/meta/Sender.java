package com.maks.telegram.meta;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@FunctionalInterface
public interface Sender {
    void send() throws TelegramApiException;
}
