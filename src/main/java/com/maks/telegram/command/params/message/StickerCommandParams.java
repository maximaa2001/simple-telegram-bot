package com.maks.telegram.command.params.message;

import com.maks.telegram.command.constant.CommandConstant;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;

public class StickerCommandParams extends MediaCommandParams {
    public StickerCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        super.initParams(update);
        Sticker sticker = update.getMessage().getSticker();
        params.put(STICKER, sticker);
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return CommandConstant.GET_STICKER;
    }
}
