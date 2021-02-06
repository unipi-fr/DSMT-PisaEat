package endpoint;


import chatMessage.InputChatMessage;
import chatMessage.OutputChatMessage;
import chatMessage.chatMessageDecoder;
import chatMessage.chatMessageEncoder;
import com.sun.org.apache.xml.internal.serializer.OutputPropertyUtils;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.logging.Logger;

@ServerEndpoint(value="/chat/{bookSessionId}", encoders= chatMessageEncoder.class, decoders = chatMessageDecoder.class)
public class ChatEndpoint {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @OnOpen
    public void open(final Session session, @PathParam("bookSessionId") final String bookSessionId){
        logger.info("[DEBUG] session open to bookSessionId: " + bookSessionId);
        session.getUserProperties().put("bookSessionId", bookSessionId);
    }

    @OnMessage
    public void onMessage(final Session session, final InputChatMessage inputChatMessage){
        logger.info("[DEBUG] Messaggio ricevuto: " + inputChatMessage.toString());

        String bookSessionId = (String) session.getUserProperties().get("bookSessionId");
        for(Session s: session.getOpenSessions()){

            if(s.isOpen() && bookSessionId.equals(s.getUserProperties().get("bookSessionId"))){

                OutputChatMessage outputChatMessage = new OutputChatMessage();

                try {
                    s.getBasicRemote().sendObject(outputChatMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EncodeException e) {
                    e.printStackTrace();
                }
            }

        }

    }




}
