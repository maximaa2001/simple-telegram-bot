package com.maks.telegram.command.params.message;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;

public class StickerCommandParams extends MediaCommandParams {
    private final static String SEND_VIDEO = "SEND_STICKER";
    private static final String TYPE = "TYPE";
    private static final String WIDTH = "WIDTH";
    private static final String HEIGHT = "HEIGHT";
    private static final String IS_ANIMATED = "IS_ANIMATED";
    private static final String IS_VIDEO = "IS_VIDEO";
    private static final String SET_NAME = "SET_NAME";
    private static final String PREMIUM_ANIMATION = "PREMIUM_ANIMATION";
    private static final String MASK_POSITION = "MASK_POSITION";
    private static final String EMOJI = "EMOJI";
    private static final String CUSTOM_EMOJI_ID = "CUSTOM_EMOJI_ID";

    public StickerCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Sticker sticker = update.getMessage().getSticker();
        params.put(FILE_ID, sticker.getFileId());
        params.put(TYPE, sticker.getType());
        params.put(WIDTH, sticker.getWidth());
        params.put(HEIGHT, sticker.getHeight());
        params.put(IS_ANIMATED, sticker.getIsAnimated());
        params.put(IS_VIDEO, sticker.getIsVideo());
        params.put(SET_NAME, sticker.getSetName());
        params.put(PREMIUM_ANIMATION, sticker.getPremiumAnimation());
        params.put(MASK_POSITION, sticker.getMaskPosition());
        params.put(EMOJI, sticker.getEmoji());
        params.put(CUSTOM_EMOJI_ID, sticker.getCustomEmojiId());
        params.put(FILE_SIZE, sticker.getFileSize());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return SEND_VIDEO;
    }
}
