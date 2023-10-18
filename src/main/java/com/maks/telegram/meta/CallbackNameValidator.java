package com.maks.telegram.meta;

import com.maks.telegram.exception.CallbackNameValidationException;
import com.maks.telegram.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

public interface CallbackNameValidator {
    Integer MAX_BYTES_FOR_CALLBACK_BUTTON = 64;

    default void validate(List<String> callbackNames) throws CallbackNameValidationException {
        callbackNames.forEach(e -> {
            byte[] bytes = e.getBytes(StandardCharsets.UTF_8);
            if (bytes.length > MAX_BYTES_FOR_CALLBACK_BUTTON) {
                throw new CallbackNameValidationException(StringUtils.createString("Callback name must not have more than ",
                        String.valueOf(MAX_BYTES_FOR_CALLBACK_BUTTON), " bytes"));
            }
        });
    }
}
