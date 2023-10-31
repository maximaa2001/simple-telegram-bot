package com.maks.telegram.command.params;

import com.maks.telegram.command.params.callback_query.CallbackQueryCommandParams;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

public abstract class CommandParams {
    public static final String CHAT_ID = "CHAT_ID";
    public static final String CHAT = "CHAT";
    public static final String MESSAGE_ID = "MESSAGE_ID";
    public static final String MESSAGE_DATE = "MESSAGE_DATE";
    public static final String FROM_USER = "FROM_USER";
    public static final String FORWARD_DATE = "FORWARD_DATE";
    public static final String FORWARD_FROM_USER = "FORWARD_FROM_USER";
    public static final String FORWARD_FROM_CHAT = "FORWARD_FROM_CHAT";
    public static final String FORWARD_FROM_MESSAGE_ID = "FORWARD_FROM_MESSAGE_ID";
    public final static String ANIMATION = "ANIMATION";
    public static final String AUDIO = "AUDIO";
    public static final String CONTACT = "CONTACT";
    public static final String DICE = "DICE";
    public final static String DOCUMENT = "DOCUMENT";
    public static final String LOCATION = "LOCATION";
    public static final String CAPTION = "CAPTION";
    public final static String PHOTO = "PHOTO";
    public static final String POLL = "POLL";
    public final static String STICKER = "STICKER";
    public final static String VIDEO = "VIDEO";
    public static final String VIDEO_NOTE = "VIDEO_NOTE";
    public final static String VOICE = "VOICE";
    public static final String EDIT_DATE = "EDIT_DATE";
    public static final String NEW_ANIMATION = "NEW_ANIMATION";
    public static final String NEW_AUDIO = "NEW_AUDIO";
    public static final String NEW_DOCUMENT = "NEW_DOCUMENT";
    public static final String NEW_TEXT = "NEW_TEXT";
    public static final String NEW_PHOTO = "NEW_PHOTO";
    public static final String NEW_VIDEO = "NEW_VIDEO";
    public static final String CALLBACK_DATA = "CALLBACK_DATA";
    public static final String DYNAMIC_DATA = "DYNAMIC_DATA";
    public static final String OLD_CHAT_MEMBER = "OLD_CHAT_MEMBER";
    public static final String NEW_CHAT_MEMBER = "NEW_CHAT_MEMBER";
    public static final String DATE = "DATE";
    public static final String INVITE_LINK = "INVITE_LINK";


    protected final Map<String, Object> params = new HashMap<>();
    @Getter
    protected final String invokedCommand;
    @Getter
    protected final Update update;

    public CommandParams(Update update) {
        this.invokedCommand = getInvokedCommandName(update);
        this.update = update;
        initParams(update);
    }

    @SuppressWarnings("unchecked")
    public <T> T getParam(String name, Class<T> clazz) {
        return (T) params.get(name);
    }

    public void setDynamicData(String dynamicData) {
        params.put(CallbackQueryCommandParams.DYNAMIC_DATA, dynamicData);
    }

    protected abstract void initParams(Update update);

    protected abstract String getInvokedCommandName(Update update);
}
