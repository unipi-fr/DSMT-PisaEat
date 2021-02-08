package chatMessage;

public class InputChatMessage {

    private String Username;
    private String Message;

    public InputChatMessage() {
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

    @Override
    public String toString() {
        return "InputChatMessage{" +
                "Username='" + Username + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }
}
