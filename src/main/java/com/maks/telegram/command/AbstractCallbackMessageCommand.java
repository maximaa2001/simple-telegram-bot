package com.maks.telegram.command;

import java.util.List;

public abstract class AbstractCallbackMessageCommand extends AbstractCommand {

    public AbstractCallbackMessageCommand(String NAME) {
        super(NAME);
    }

    public List<String> getNames() {
        return List.of(getName());
    }
}
