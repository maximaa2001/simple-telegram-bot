package com.maks.telegram.meta;

public enum DiceType {

    DICE("\uD83C\uDFB2", 6),
    DARTS("\uD83C\uDFAF", 6),
    BOWLING("\uD83C\uDFB3", 6),
    BASKETBALL("\uD83C\uDFC0", 5),
    FOOTBALL("⚽\uFE0F", 5),
    SLOT("\uD83C\uDFB0", 64);

    private final String emoji;
    private final Integer maxValue;

    public Integer getMinValue() {
        return 1;
    }

    DiceType(String emoji, Integer maxValue) {
        this.emoji = emoji;
        this.maxValue = maxValue;
    }

    public String getEmoji() {
        return emoji;
    }

    public Integer getMaxValue() {
        return maxValue;
    }
}
