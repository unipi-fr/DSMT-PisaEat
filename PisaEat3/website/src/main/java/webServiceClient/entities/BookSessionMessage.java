package webServiceClient.entities;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/*

{
    "bookSessionId": "1",
    "username": "tizio",
    "message": "Lo chef mi sta sulle palle!",
    "datetimeUTC": "2021-02-05T15:30:22Z"
}
*/
public class BookSessionMessage {
    public String BookSessionId;
    public String Username;
    public String Message;
    public String DatetimeUTC;
    public String LocalDatetime;

    public BookSessionMessage() {
    }

    @JsonbCreator
    public BookSessionMessage(
            @JsonbProperty("bookSessionId") String bookSessionId,
            @JsonbProperty("username") String username,
            @JsonbProperty("message") String message,
            @JsonbProperty("datetimeUTC") String datetimeUTC) {

        this.BookSessionId = bookSessionId;
        this.Username = username;
        this.Message = message;
        this.DatetimeUTC = datetimeUTC;
        this.LocalDatetime = Instant.parse(datetimeUTC)
                .atZone(ZoneId.systemDefault())
                .format(
                        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
                                .withLocale(Locale.getDefault())
                );
    }

    public String getBookSessionId() {
        return BookSessionId;
    }

    public void setBookSessionId(String bookSessionId) {
        BookSessionId = bookSessionId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDatetimeUTC() {
        return DatetimeUTC;
    }

    public void setDatetimeUTC(String datetimeUTC) {
        DatetimeUTC = datetimeUTC;
    }

    public String getLocalDatetime() {
        return LocalDatetime;
    }

    public void setLocalDatetime(String localDatetime) {
        LocalDatetime = localDatetime;
    }

    @Override
    public String toString() {
        return "BookSessionMessage{" +
                "BookSessionId='" + BookSessionId + '\'' +
                ", Username='" + Username + '\'' +
                ", Message='" + Message + '\'' +
                ", DatetimeUTC='" + DatetimeUTC + '\'' +
                '}';
    }
}
