package com.maks.telegram.command;


import com.maks.telegram.command.params.CommandParams;
import com.maks.telegram.command.response.user.UserResponse;

public abstract class DynamicCommand extends AbstractCallbackMessageCommand {
    public static final String DELIMITER = "_";
    public static final Integer MAX_BYTES_FOR_CALLBACK_BUTTON = 64;


    public DynamicCommand(String NAME) {
        super(NAME);
    }

    @Override
    public UserResponse execute(CommandParams commandParams) {
        resolveDynamicData(commandParams);
        return super.execute(commandParams);
    }

    private void resolveDynamicData(CommandParams commandParams) {
        String dynamicData = commandParams.getInvokedCommand().split(DELIMITER)[1];
        commandParams.setDynamicData(dynamicData);
    }

}
