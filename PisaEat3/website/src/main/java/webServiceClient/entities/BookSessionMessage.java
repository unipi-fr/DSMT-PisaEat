package webServiceClient.entities;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

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
