# Simple Telegram Bot Java Library

This library use [Telegram Bot Java Library](https://github.com/rubenlagus/TelegramBots), you can find more information
following the link.

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

Possible activities in `com.maks.telegram.command.response.command.CommandResponse`

1. send object (Text, Animation, Audio, Contact, Dice, Document, Location, Photo, Poll, Sticker, Video, Video note,
   Voice)
2. edit object (Text, Animation, Audio, Document, Photo, Video)

## Usage

Just add the library to your maven project

```xml

<dependency>
    <groupId>com.maks.telegram</groupId>
    <artifactId>simple-telegram-bot</artifactId>
    <version>1.0.0</version>
</dependency>
```

You just need to create a ` org.telegram.telegrambots.meta.TelegramBotsApi` and register your bot:

```java
import com.maks.telegram.command.factory.DefaultCommandFactory;
import com.maks.telegram.command.factory.DefaultParamsFactory;

public class Main {
   public static void main(String[] args) {
      try {
         TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
         telegramBotsApi.registerBot(new DefaultTelegramLongPollingBot("username", "token",
                 new DefaultCommandFactory(List.of()), new DefaultParamsFactory()));
      } catch (TelegramApiException e) {
         throw new RuntimeException(e);
      }
   }
}
```



