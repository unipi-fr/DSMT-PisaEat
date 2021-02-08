package chatMessage;

public class OutputChatMessage {
    private String bookSessionId;
    private String username;
    private String message;
    private String datetimeUTC;
    private String localDatetime;

    public OutputChatMessage() {
    }

    public OutputChatMessage(String bookSessionId, String username, String message, String datetimeUTC, String localDatetime) {
        this.bookSessionId = bookSessionId;
        this.username = username;
        this.message = message;
        this.datetimeUTC = datetimeUTC;
        this.localDatetime = localDatetime;
    }

    public String getBookSessionId() {
        return bookSessionId;
    }

    public void setBookSessionId(String bookSessionId) {
        this.bookSessionId = bookSessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDatetimeUTC() {
        return datetimeUTC;
    }

    public void setDatetimeUTC(String datetimeUTC) {
        this.datetimeUTC = datetimeUTC;
    }

    public String getLocalDatetime() {
        return localDatetime;
    }

    public void setLocalDatetime(String localDatetime) {
        this.localDatetime = localDatetime;
    }
}
