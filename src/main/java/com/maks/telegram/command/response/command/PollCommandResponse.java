package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.meta.CallbackNameValidator;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;

import java.util.List;

public class PollCommandResponse extends CommandResponse implements CallbackNameValidator {
    private final String question;
    private final List<String> options;
    private final Boolean isAnonymous;
    private final String type;
    private final Boolean allowsMultipleAnswers;
    private final Integer correctOptionId;
    private final String explanation;
    private final Integer openPeriod;
    private final Integer closeDate;
    private final Boolean isClosed;
    private final ReturnInlineKeyboard returnInlineKeyboard;

    public PollCommandResponse(Long chatId, String question, List<String> options, Boolean isAnonymous,
                               String type, Boolean allowsMultipleAnswers, Integer correctOptionId,
                               String explanation, Integer openPeriod, Integer closeDate, Boolean isClosed,
                               ReturnInlineKeyboard returnInlineKeyboard) {
        super(chatId);
        this.question = question;
        this.options = options;
        this.isAnonymous = isAnonymous;
        this.type = type;
        this.allowsMultipleAnswers = allowsMultipleAnswers;
        this.correctOptionId = correctOptionId;
        this.explanation = explanation;
        this.openPeriod = openPeriod;
        this.closeDate = closeDate;
        this.isClosed = isClosed;
        if (returnInlineKeyboard != null) {
            validate(returnInlineKeyboard.getNextCommandCallbacks(chatId));
        }
        this.returnInlineKeyboard = returnInlineKeyboard;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createPoll());
    }

    private SendPoll createPoll() {
        return SendPoll.builder()
                .chatId(chatId)
                .question(question)
                .options(options)
                .isAnonymous(isAnonymous)
                .type(type)
                .allowMultipleAnswers(allowsMultipleAnswers)
                .correctOptionId(correctOptionId)
                .explanation(explanation)
                .openPeriod(openPeriod)
                .closeDate(closeDate)
                .isClosed(isClosed)
                .replyMarkup((returnInlineKeyboard != null) ? returnInlineKeyboard.getNextKeyboard(chatId) : null)
                .build();
    }

    public static class PollCommandResponseBuilder {
        private final Long chatId;
        private final String question;
        private final List<String> options;
        private Boolean isAnonymous;
        private String type;
        private Boolean allowsMultipleAnswers;
        private Integer correctOptionId;
        private String explanation;
        private Integer openPeriod;
        private Integer closeDate;
        private Boolean isClosed;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public PollCommandResponseBuilder(Long chatId, String question, List<String> options) {
            this.chatId = chatId;
            this.question = question;
            this.options = options;
        }

        public PollCommandResponseBuilder isAnonymous(Boolean isAnonymous) {
            this.isAnonymous = isAnonymous;
            return this;
        }

        public PollCommandResponseBuilder type(String type) {
            this.type = type;
            return this;
        }

        public PollCommandResponseBuilder allowsMultipleAnswers(Boolean allowsMultipleAnswers) {
            this.allowsMultipleAnswers = allowsMultipleAnswers;
            return this;
        }

        public PollCommandResponseBuilder correctOptionId(Integer correctOptionId) {
            this.correctOptionId = correctOptionId;
            return this;
        }

        public PollCommandResponseBuilder explanation(String explanation) {
            this.explanation = explanation;
            return this;
        }

        public PollCommandResponseBuilder openPeriod(Integer openPeriod) {
            this.openPeriod = openPeriod;
            return this;
        }

        public PollCommandResponseBuilder closeDate(Integer closeDate) {
            this.closeDate = closeDate;
            return this;
        }

        public PollCommandResponseBuilder isClosed(Boolean isClosed) {
            this.isClosed = isClosed;
            return this;
        }

        public PollCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public PollCommandResponse build() {
            return new PollCommandResponse(chatId, question, options, isAnonymous, type, allowsMultipleAnswers, correctOptionId, explanation,
                    openPeriod, closeDate, isClosed, returnInlineKeyboard);
        }
    }
}
