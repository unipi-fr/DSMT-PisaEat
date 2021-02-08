package chatMessage;

import jakarta.json.Json;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

public class chatMessageEncoder implements Encoder.Text<OutputChatMessage> {
    @Override
    public String encode(OutputChatMessage outputChatMessage) throws EncodeException {

        return Json.createObjectBuilder().add("message", outputChatMessage.getMessage())
                .add("localDatetime", outputChatMessage.getLocalDatetime())
                .add("username", outputChatMessage.getUsername())
                .add("bookSessionId", outputChatMessage.getBookSessionId()).build().toString();
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
