# Simple Telegram Bot Java Library

This library use [Telegram Bot Java Library](https://github.com/rubenlagus/TelegramBots), you can find more information
following the link.

## See also
You can use Spring Boot starter for this lib
[Spring-boot-starter-telegram-bot](https://github.com/maximaa2001/spring-boot-starter-telegram-bot)

## Webhooks vs Long Polling

Support only long pulling method at now

## Description

Library based on command pattern. It consists of 6 main entities

1. `com.maks.telegram.command.params.CommandParams`. It is abstraction
   around `org.telegram.telegrambots.meta.api.objects.Update` from
   the [library](https://github.com/rubenlagus/TelegramBots), that extracts needed data.
2. `com.maks.telegram.command.response.command.CommandResponse`. It stores activities, that bot must execute.
3. Implementations of `com.maks.telegram.command.Command`.There are entities, that
   accept `com.maks.telegram.command.params.CommandParams`, implements business logic and
   return `com.maks.telegram.command.response.command.CommandResponse`
4. `com.maks.telegram.command.factory.CommandFactory`. It stores all implementations
   of `com.maks.telegram.command.Command` with their names. Recommended to
   use `com.maks.telegram.command.factory.DefaultCommandFactory`.
5. `com.maks.telegram.command.factory.ParamsFactory`. It convert `org.telegram.telegrambots.meta.api.objects.Update`
   entity from the [library](https://github.com/rubenlagus/TelegramBots) to subclass
   of `com.maks.telegram.command.params.CommandParams`. Recommended to
   use `com.maks.telegram.command.factory.DefaultParamsFactory`.
6. `org.telegram.telegrambots.bots.TelegramLongPollingBot`.This is the essence of the telegram bot. Recommended to
   use `com.maks.telegram.bot.DefaultTelegramLongPollingBot`.

Possible `com.maks.telegram.command.params.CommandParams`:

1. message (Text, Animation, Audio, Contact, Dice, Document, Location, Photo, Poll, Sticker, Video, Video note, Voice)
2. edited_message (Text, Animation, Audio, Document, Photo, Video)
3. callback_query
4. my_chat_member

Possible activities in `com.maks.telegram.command.response.command.CommandResponse`

1. send object (Text, Animation, Audio, Contact, Dice, Document, Location, Photo, Poll, Sticker, Video, Video note,
   Voice)
2. edit object (Text, Animation, Audio, Document, Photo, Video)

## Usage

In the first you need to add the library to your maven project

```xml
<dependency>
    <groupId>com.maks.telegram</groupId>
    <artifactId>simple-telegram-bot</artifactId>
    <version>1.1.0</version>
</dependency>
```

In the next step you need to implement `com.maks.telegram.command.Command` with your business logic

### Examples of command

In the next example if the bot get string the command "/message", it will send the "hello" message

```java
public class HelloCommand extends AbstractCommand {
    public HelloCommand() {
        super("/message");
    }

    @Override
    protected CommandResponse transformToCommandResponse(CommandParams commandParams) {
        return new MessageCommandResponse.MessageCommandResponseBuilder(
                commandParams.getParam(CommandParams.CHAT_ID, Long.class),
                "hello"
        ).build();
    }
}
```

In the next example user will see the command "/help" in menu, and if the bot get string the command "/help", it will
send the message with information

```java
public class HelpCommand extends AbstractMenuCommand {
    public HelpCommand() {
        super("/help", "information about bot");
    }

    @Override
    protected CommandResponse transformToCommandResponse(CommandParams commandParams) {
        return new MessageCommandResponse.MessageCommandResponseBuilder(
                commandParams.getParam(CommandParams.CHAT_ID, Long.class),
                "information"
        ).build();
    }
}
```

If you want to handle not string messages (for example photo, video, voice and etc) you should use command names
from `com.maks.telegram.command.constant.CommandConstant`

In the next example if the bot the get some photo from user, it will send another photo

```java
public class PhotoCommand extends AbstractCommand {
    public PhotoCommand() {
        super(CommandConstant.GET_PHOTO);
    }

    @Override
    protected CommandResponse transformToCommandResponse(CommandParams commandParams) {
        return new PhotoCommandResponse.PhotoCommandResponseBuilder(
                commandParams.getParam(CommandParams.CHAT_ID, Long.class),
                new File("/path/to/photo")
        ).caption("caption").build();
    }
}
```

In the next example if the bot get string the command "/question", it will send message with InlineKeyboardMarkup
from [Telegram Bot Java Library](https://github.com/rubenlagus/TelegramBots)

```java
public class QuestionCommand extends AbstractCommand {
    public QuestionCommand() {
        super("/question");
    }

    @Override
    protected CommandResponse transformToCommandResponse(CommandParams commandParams) {
        Long chatId = commandParams.getParam(CommandParams.CHAT_ID, Long.class);
        return new PhotoCommandResponse.PhotoCommandResponseBuilder(
                chatId,
                new File("/path/to/logo/linkedin")
        ).caption("Whose is it logotype?")
                .returnInlineKeyboard(new ReturnInlineKeyboard() {
                    @Override
                    public InlineKeyboardMarkup getNextKeyboard(Long chatId) {
                        return createSimpleKeyboard(3, 1, chatId);
                    }

                    @Override
                    public List<String> getNextCommandTexts(Long chatId) {
                        return List.of("Telegram", "Linkedin", "Vk");
                    }

                    @Override
                    public List<String> getNextCommandCallbacks(Long chatId) {
                        return List.of("Wrong", "Correct", "Wrong");
                    }
                }).build();
    }
}
```
Result of execution command above:

![result of execution command above](https://github.com/maximaa2001/simple-telegram-bot/blob/media/media1.png?raw=true)

Handler of above question is below

```java
public class CorrectQuestionCommand extends AbstractCommand {
    public CorrectQuestionCommand() {
        super("Correct");
    }

    @Override
    protected CommandResponse transformToCommandResponse(CommandParams commandParams) {
        return new MessageCommandResponse.MessageCommandResponseBuilder(
                commandParams.getParam(CommandParams.CHAT_ID, Long.class),
                "Correct answer"
        ).build();
    }
}

```

Result of execution command above:

![result of execution command above](https://github.com/maximaa2001/simple-telegram-bot/blob/media/media2.png?raw=true)

In the next step you need to create a `org.telegram.telegrambots.meta.TelegramBotsApi`, register your bot and all
your `com.maks.telegram.command.Command`

```java
public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new DefaultTelegramLongPollingBot("username", "token",
                    new DefaultCommandFactory(List.of(new HelloCommand(), new HelpCommand(),
                            new PhotoCommand(), new QuestionCommand(), new CorrectQuestionCommand())),
                    new DefaultParamsFactory()));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
```