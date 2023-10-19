package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;

public class PollCommandParams extends MessageCommandParams {
    private static final String SEND_POLL = "SEND_POLL";
    public static final String POLL_ID = "POLL_ID";
    public static final String QUESTION = "QUESTION";
    public static final String OPTIONS = "OPTIONS";
    public static final String TOTAL_VOTER_COUNT = "TOTAL_VOTER_COUNT";
    public static final String IS_CLOSED = "IS_CLOSED";
    public static final String IS_ANONYMOUS = "IS_ANONYMOUS";
    public static final String TYPE = "TYPE";
    public static final String ALLOWS_MULTIPLE_ANSWERS = "ALLOWS_MULTIPLE_ANSWERS";
    public static final String CORRECT_OPTION_ID = "CORRECT_OPTION_ID";
    public static final String EXPLANATION = "EXPLANATION";
    public static final String OPEN_PERIOD = "OPEN_PERIOD";
    public static final String CLOSE_DATE = "CLOSE_DATE";

    public PollCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Poll poll = update.getMessage().getPoll();
        params.put(POLL_ID, poll.getId());
        params.put(QUESTION, poll.getQuestion());
        params.put(OPTIONS, poll.getOptions());
        params.put(TOTAL_VOTER_COUNT, poll.getTotalVoterCount());
        params.put(IS_CLOSED, poll.getIsClosed());
        params.put(IS_ANONYMOUS, poll.getIsAnonymous());
        params.put(TYPE, poll.getType());
        params.put(ALLOWS_MULTIPLE_ANSWERS, poll.getAllowMultipleAnswers());
        params.put(CORRECT_OPTION_ID, poll.getCorrectOptionId());
        params.put(EXPLANATION, poll.getExplanation());
        params.put(OPEN_PERIOD, poll.getOpenPeriod());
        params.put(CLOSE_DATE, poll.getCloseDate());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_POLL;
    }
}
