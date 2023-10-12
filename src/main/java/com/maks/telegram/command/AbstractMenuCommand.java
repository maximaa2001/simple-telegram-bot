package com.maks.telegram.command;


import com.maks.telegram.meta.Descriptionable;

public abstract class AbstractMenuCommand extends AbstractCommand implements Descriptionable {
    private final String DESCRIPTION;

    public AbstractMenuCommand(String NAME, String DESCRIPTION) {
        super(NAME);
        this.DESCRIPTION = DESCRIPTION;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

}
