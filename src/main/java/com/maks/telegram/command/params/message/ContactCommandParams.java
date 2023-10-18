package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ContactCommandParams extends MessageCommandParams {
    private static final String SEND_CONTACT = "SEND_CONTACT";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String CONTACT_FIRST_NAME = "CONTACT_FIRST_NAME";
    public static final String CONTACT_LAST_NAME = "CONTACT_LAST_NAME";
    public static final String CONTACT_USER_ID = "CONTACT_USER_ID";
    public static final String VCARD = "VCARD";

    public ContactCommandParams(Update update) {
        super(update);
    }


    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Contact contact = update.getMessage().getContact();
        params.put(PHONE_NUMBER, contact.getPhoneNumber());
        params.put(CONTACT_FIRST_NAME, contact.getFirstName());
        params.put(CONTACT_LAST_NAME, contact.getLastName());
        params.put(CONTACT_USER_ID, contact.getUserId());
        params.put(VCARD, contact.getVCard());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_CONTACT;
    }
}
