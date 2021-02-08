package endpoint;


import chatMessage.InputChatMessage;
import chatMessage.OutputChatMessage;
import chatMessage.chatMessageDecoder;
import chatMessage.chatMessageEncoder;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import webServiceClient.ChatWebClient;
import webServiceClient.entities.BookSessionMessage;

import java.io.IOException;
import java.util.logging.Logger;

@ServerEndpoint(value = "/chat/{bookSessionId}", encoders = chatMessageEncoder.class, decoders = chatMessageDecoder.class)
public class ChatEndpoint {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @OnOpen
    public void open(final Session session, @PathParam("bookSessionId") final String bookSessionId) {
        logger.info("[DEBUG] session open to bookSessionId: " + bookSessionId);
        session.getUserProperties().put("bookSessionId", bookSessionId);
    }

    @OnMessage
    public void onMessage(final Session session, final InputChatMessage inputChatMessage) {
        logger.info("[DEBUG] Messaggio ricevuto: " + inputChatMessage.toString());

        String bookSessionId = (String) session.getUserProperties().get("bookSessionId");
        for (Session s : session.getOpenSessions()) {

            if (s.isOpen() && bookSessionId.equals(s.getUserProperties().get("bookSessionId"))) {

                OutputChatMessage outputChatMessage = new OutputChatMessage();
                BookSessionMessage bookSessionMessage = ChatWebClient.sendMessage(bookSessionId, inputChatMessage.getUsername(), inputChatMessage.getMessage());

                outputChatMessage.setMessage(bookSessionMessage.Message);
                outputChatMessage.setUsername(bookSessionMessage.Username);
                outputChatMessage.setLocalDatetime(bookSessionMessage.LocalDatetime);
                outputChatMessage.setBookSessionId(bookSessionMessage.BookSessionId);

                try {
                    s.getBasicRemote().sendObject(outputChatMessage);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
