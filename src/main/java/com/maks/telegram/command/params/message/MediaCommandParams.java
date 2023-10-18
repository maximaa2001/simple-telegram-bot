package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class MediaCommandParams extends MessageCommandParams {
    public static final String FILE_ID = "FILE_ID";
    public static final String CAPTION = "CAPTION";
    public static final String DURATION = "DURATION";
    public static final String FILE_NAME = "FILE_NAME";
    public static final String MIME_TYPE = "MIME_TYPE";
    public static final String FILE_SIZE = "FILE_SIZE";

    public MediaCommandParams(Update update) {
        super(update);
    }

}
