package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ContactCommandParams extends MessageCommandParams {
    private static final String SEND_CONTACT = "SEND_CONTACT";
    public static final String CONTACT = "CONTACT";

    public ContactCommandParams(Update update) {
        super(update);
    }


    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Contact contact = update.getMessage().getContact();
        params.put(CONTACT, contact);
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_CONTACT;
    }
}
