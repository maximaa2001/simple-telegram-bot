package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.meta.CallbackNameValidator;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;

public class ContactCommandResponse extends CommandResponse implements CallbackNameValidator {
    private final String phoneNumber;
    private final String firstName;
    private final String lastName;
    private final String vcard;
    private final ReturnInlineKeyboard returnInlineKeyboard;

    public ContactCommandResponse(Long chatId, String phoneNumber, String firstName, String lastName,
                                  String vcard, ReturnInlineKeyboard returnInlineKeyboard) {
        super(chatId);
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.vcard = vcard;
        if (returnInlineKeyboard != null) {
            validate(returnInlineKeyboard.getNextCommandCallbacks(chatId));
        }
        this.returnInlineKeyboard = returnInlineKeyboard;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createContact());
    }

    private SendContact createContact() {
        return SendContact.builder()
                .chatId(chatId)
                .phoneNumber(phoneNumber)
                .firstName(firstName)
                .lastName(lastName)
                .vCard(vcard)
                .replyMarkup((returnInlineKeyboard != null) ? returnInlineKeyboard.getNextKeyboard(chatId) : null)
                .build();
    }

    public static class ContactCommandResponseBuilder {
        private final Long chatId;
        private final String phoneNumber;
        private final String firstName;
        private String lastName;
        private String vcard;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public ContactCommandResponseBuilder(Long chatId, String phoneNumber, String firstName) {
            this.chatId = chatId;
            this.phoneNumber = phoneNumber;
            this.firstName = firstName;
        }

        public ContactCommandResponseBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ContactCommandResponseBuilder vcard(String vcard) {
            this.vcard = vcard;
            return this;
        }

        public ContactCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public ContactCommandResponse build() {
            return new ContactCommandResponse(chatId, phoneNumber, firstName, lastName, vcard, returnInlineKeyboard);
        }
    }
}
