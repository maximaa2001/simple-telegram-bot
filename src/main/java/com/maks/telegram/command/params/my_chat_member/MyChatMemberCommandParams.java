package com.maks.telegram.command.params.my_chat_member;

import com.maks.telegram.command.constant.CommandConstant;
import com.maks.telegram.command.params.CommandParams;
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyChatMemberCommandParams extends CommandParams {
    public MyChatMemberCommandParams(Update update) {
        super(update);
    }

    @Override
    protected void initParams(Update update) {
        ChatMemberUpdated myChatMember = update.getMyChatMember();
        params.put(CHAT_ID, myChatMember.getChat().getId());
        params.put(CHAT, myChatMember.getChat());
        params.put(FROM_USER, myChatMember.getFrom());
        params.put(DATE, myChatMember.getDate());
        params.put(OLD_CHAT_MEMBER, myChatMember.getOldChatMember());
        params.put(NEW_CHAT_MEMBER, myChatMember.getNewChatMember());
        params.put(INVITE_LINK, myChatMember.getInviteLink());
    }

    @Override
    protected String getInvokedCommandName(Update update) {
        return CommandConstant.GET_MY_CHAT_MEMBER;
    }
}
