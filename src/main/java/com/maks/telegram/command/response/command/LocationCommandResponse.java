package com.maks.telegram.command.response.command;

import com.maks.telegram.command.ReturnInlineKeyboard;
import com.maks.telegram.command.response.user.UserResponse;
import com.maks.telegram.meta.CallbackNameValidator;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;

public class LocationCommandResponse extends CommandResponse implements CallbackNameValidator {
    private final Double latitude;
    private final Double longitude;
    private final Double horizontalAccuracy;
    private final Integer livePeriod;
    private final Integer heading;
    private final Integer proximityAlertRadius;
    private final ReturnInlineKeyboard returnInlineKeyboard;

    public LocationCommandResponse(Long chatId, Double latitude, Double longitude, Double horizontalAccuracy,
                                   Integer livePeriod, Integer heading, Integer proximityAlertRadius,
                                   ReturnInlineKeyboard returnInlineKeyboard) {
        super(chatId);
        this.latitude = latitude;
        this.longitude = longitude;
        this.horizontalAccuracy = horizontalAccuracy;
        this.livePeriod = livePeriod;
        this.heading = heading;
        this.proximityAlertRadius = proximityAlertRadius;
        if (returnInlineKeyboard != null) {
            validate(returnInlineKeyboard.getNextCommandCallbacks(chatId));
        }
        this.returnInlineKeyboard = returnInlineKeyboard;
    }

    @Override
    public UserResponse generateUserResponse() {
        return new UserResponse(createLocation());
    }

    private SendLocation createLocation() {
        return SendLocation.builder()
                .chatId(chatId)
                .latitude(latitude)
                .longitude(longitude)
                .horizontalAccuracy(horizontalAccuracy)
                .livePeriod(livePeriod)
                .heading(heading)
                .proximityAlertRadius(proximityAlertRadius)
                .replyMarkup((returnInlineKeyboard != null) ? returnInlineKeyboard.getNextKeyboard(chatId) : null)
                .build();
    }

    public static class LocationCommandResponseBuilder {
        private final Long chatId;
        private final Double latitude;
        private final Double longitude;
        private Double horizontalAccuracy;
        private Integer livePeriod;
        private Integer heading;
        private Integer proximityAlertRadius;
        private ReturnInlineKeyboard returnInlineKeyboard;

        public LocationCommandResponseBuilder(Long chatId, Double latitude, Double longitude) {
            this.chatId = chatId;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public LocationCommandResponseBuilder horizontalAccuracy(Double horizontalAccuracy) {
            this.horizontalAccuracy = horizontalAccuracy;
            return this;
        }

        public LocationCommandResponseBuilder livePeriod(Integer livePeriod) {
            this.livePeriod = livePeriod;
            return this;
        }

        public LocationCommandResponseBuilder heading(Integer heading) {
            this.heading = heading;
            return this;
        }

        public LocationCommandResponseBuilder proximityAlertRadius(Integer proximityAlertRadius) {
            this.proximityAlertRadius = proximityAlertRadius;
            return this;
        }

        public LocationCommandResponseBuilder returnInlineKeyboard(ReturnInlineKeyboard returnInlineKeyboard) {
            this.returnInlineKeyboard = returnInlineKeyboard;
            return this;
        }

        public LocationCommandResponse build() {
            return new LocationCommandResponse(chatId, latitude, longitude, horizontalAccuracy, livePeriod, heading, proximityAlertRadius, returnInlineKeyboard);
        }
    }
}
