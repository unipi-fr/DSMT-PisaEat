package chatMessage;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;

import java.io.StringReader;

public class chatMessageDecoder implements Decoder.Text<InputChatMessage> {


    @Override
    public InputChatMessage decode(String s) throws DecodeException {
        InputChatMessage chatMessage = new InputChatMessage();
        JsonObject jsonObject = Json.createReader(new StringReader(s)).readObject();

        chatMessage.setMessage(jsonObject.getString("message"));
        chatMessage.setUsername(jsonObject.getString("username"));

        return chatMessage;
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
